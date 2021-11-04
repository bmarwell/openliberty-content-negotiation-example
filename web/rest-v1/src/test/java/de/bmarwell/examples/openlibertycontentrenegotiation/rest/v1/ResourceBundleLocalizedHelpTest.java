package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Locale;
import javax.ws.rs.core.UriInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ResourceBundleLocalizedHelpTest {

  private ResourceBundleLocalizedHelp help;

  private final URI defaultUri = URI.create("http://localhost/");

  private final UriInfo uriInfo = mock(UriInfo.class);

  @BeforeEach
  public void initializeHelpClass() {
    this.help = new ResourceBundleLocalizedHelp();
    help.setMessageProvider(new ResourceBundleMessageProvider());

    // init uriinfo
    when(uriInfo.getBaseUri()).then(args -> defaultUri);
  }

  @Test
  public void get_help_useragent_curl_locale_english() {
    //  given
    help.setUserAgent(Agents.CURL);

    // when
    final String helpString = help.getHelp(uriInfo, Locale.ENGLISH);

    // then
    Assertions.assertThat(helpString)
        .isNotNull()
        .contains("Welcome to the Dice Parser")
        .contains("curl " + defaultUri + "roll?dice=2d6")
    ;
  }

  @Test
  public void get_help_useragent_curl_locale_german() {
    // given
    help.setUserAgent(Agents.CURL);

    // when
    final String helpString = help.getHelp(uriInfo, Locale.GERMAN);

    // then
    Assertions.assertThat(helpString)
        .isNotNull()
        .contains("Willkommen beim Würfelparser")
        .contains("curl " + defaultUri + "roll?dice=2d6")
    ;
  }

}
