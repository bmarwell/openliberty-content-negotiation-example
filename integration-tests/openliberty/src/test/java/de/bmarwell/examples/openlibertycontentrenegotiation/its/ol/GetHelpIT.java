package de.bmarwell.examples.openlibertycontentrenegotiation.its.ol;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GetHelpIT extends AbstraceRestIT {

    private static Stream<Arguments> ua_locale_provider() {
        return Stream.of(
                Arguments.of("curl/7.37.0", "de", List.of("Willkommen", "curl ")),
                Arguments.of("HTTPie/2.0.0", "de", List.of("Willkommen", "http ")),
                Arguments.of("curl/7.37.0", "en", List.of("Welcome", "curl ")),
                Arguments.of("HTTPie/1.0.0", "en", List.of("Welcome", "http ")));
    }

    @ParameterizedTest
    @MethodSource("ua_locale_provider")
    public void get_help_ua_locale(String userAgent, String locale, List<String> expectedResponseSnippets) {
        // given
        final WebTarget webTarget = client.target(getBaseUri()).path("/");

        // when
        final Response response = webTarget
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header(HttpHeaders.USER_AGENT, userAgent)
                .header(HttpHeaders.ACCEPT_LANGUAGE, locale)
                .get();

        // then
        assertThat(response)
                .matches(
                        response1 -> response1.getStatus() == 200,
                        "expected rc 200 but got: " + response.getStatus() + ".");

        final String responseString = response.readEntity(String.class);

        final AbstractStringAssert<?> abstractStringAssert = assertThat(responseString);
        for (String expectedResponseSnippet : expectedResponseSnippets) {
            abstractStringAssert.contains(expectedResponseSnippet);
        }
    }
}
