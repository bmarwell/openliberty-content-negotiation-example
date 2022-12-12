package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.UriInfo;
import java.io.Serial;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Returns a localized and formatted help string by using resource bundles.
 * <p>
 * Based on:
 * <a href="https://github.com/oktadev/quarkus-content-negotiation-example/blob/acd946789646d19b2bfae657ec74a6cba049e9de/src/main/java/com/example/Help.java">
 * oktadev/quarkus-content-negotiation-example/[…]/ResourceBundleLocalizedHelp.java
 * </a>
 */
@RequestScoped
public class ResourceBundleLocalizedHelp implements LocalizedHelp {

    @Serial
    private static final long serialVersionUID = -1702395883407930723L;

    @Inject
    private Agent userAgent;

    @Inject
    private MessageProvider messageProvider;

    private static final Map<Agent, String> AGENT_HELP_MAP = Map.of(
            Agents.CURL, "help.curl",
            Agents.HTTPIE, "help.httpie",
            Agents.GENERIC, "help.generic");

    @Override
    public String getHelp(UriInfo uriInfo, Locale locale) {

        String url = uriInfo.getBaseUri().resolve("/").toString();

        // look up the help key
        String helpKey = AGENT_HELP_MAP.getOrDefault(userAgent, "help.generic");

        // Resource Bundle lookup/formatting
        final String messageString = messageProvider.getString("messages", locale, helpKey);
        MessageFormat formatter = new MessageFormat(messageString, locale);

        return formatter.format(new Object[] {url});
    }

    public Agent getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(Agent userAgent) {
        this.userAgent = userAgent;
    }

    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
