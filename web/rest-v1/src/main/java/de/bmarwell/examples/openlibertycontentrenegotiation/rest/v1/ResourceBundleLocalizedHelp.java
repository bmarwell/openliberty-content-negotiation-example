package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriInfo;

/**
 * See:
 * <a href="https://github.com/oktadev/quarkus-content-negotiation-example/blob/acd946789646d19b2bfae657ec74a6cba049e9de/src/main/java/com/example/Help.java">
 * oktadev/quarkus-content-negotiation-example/[…]/ResourceBundleLocalizedHelp.java
 * </a>
 */
@RequestScoped
public class ResourceBundleLocalizedHelp implements LocalizedHelp {

  @Inject
  private Agent userAgent;

  private static final Map<Agent, String> AGENT_HELP_MAP = Map.of(
      Agents.CURL, "help.curl",
      Agents.HTTPIE, "help.httpie",
      Agents.GENERIC, "help.generic"
  );

  @Override
  public String getHelp(UriInfo uriInfo, Locale locale) {

    String url = uriInfo.getBaseUri().resolve("/").toString();

    // look up the help key
    String helpKey = AGENT_HELP_MAP.getOrDefault(userAgent, "help.generic");

    // Resource Bundle lookup/formatting
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages", locale);
    MessageFormat formatter = new MessageFormat(resourceBundle.getString(helpKey), locale);

    return formatter.format(new Object[]{url});
  }

  public Agent getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(Agent userAgent) {
    this.userAgent = userAgent;
  }
}
