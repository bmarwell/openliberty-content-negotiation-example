package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Locale;
import javax.ws.rs.core.UriInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ResourceBundleLocalizedHelpTest {

  @Test
  public void get_help_ua() {
    final ResourceBundleLocalizedHelp help = new ResourceBundleLocalizedHelp();
    help.setUserAgent(Agents.CURL);

    UriInfo uriInfo = mock(UriInfo.class);
    when(uriInfo.getBaseUri()).then(args -> URI.create("http://localhost/roll"));

    // when
    final String helpString = help.getHelp(uriInfo, Locale.GERMAN);

    // then
    Assertions.assertThat(helpString)
        .isNotNull();
  }

}
