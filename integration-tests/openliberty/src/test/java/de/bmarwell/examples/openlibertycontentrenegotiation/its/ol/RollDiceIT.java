package de.bmarwell.examples.openlibertycontentrenegotiation.its.ol;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.AbstractStringAssert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RollDiceIT extends AbstraceRestIT {

    private static Stream<Arguments> ua_locale_provider() {
        return Stream.of(
                Arguments.of(
                        "2d6",
                        MediaType.APPLICATION_JSON_TYPE,
                        List.of("\"number_of_dice\": 2,", "\"number_of_faces\": 6")),
                Arguments.of("2d6", MediaType.TEXT_PLAIN_TYPE, List.of("2d6 = ", "--d6 =")));
    }

    @ParameterizedTest
    @MethodSource("ua_locale_provider")
    public void roll_dice_accept(String diceExpression, MediaType acceptType, List<String> expectedResponseSnippets) {
        // given
        final WebTarget webTarget = client.target(getBaseUri()).path("/roll");

        // when
        final Response response =
                webTarget.queryParam("dice", diceExpression).request(acceptType).get();

        // then
        assertThat(response)
                .matches(
                        response1 -> response1.getStatus() == 200,
                        "expected rc 200 but got: " + response.getStatus() + ".")
                .matches(response1 ->
                        response1.getHeaderString(HttpHeaders.CONTENT_TYPE).startsWith(acceptType.toString()));

        final String responseString = response.readEntity(String.class);

        final AbstractStringAssert<?> abstractStringAssert = assertThat(responseString);
        for (String expectedResponseSnippet : expectedResponseSnippets) {
            abstractStringAssert.contains(expectedResponseSnippet);
        }
    }
}
