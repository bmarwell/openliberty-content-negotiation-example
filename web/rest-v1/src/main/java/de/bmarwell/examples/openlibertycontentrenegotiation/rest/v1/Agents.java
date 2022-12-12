package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * See:
 * <a href="https://github.com/oktadev/quarkus-content-negotiation-example/blob/acd946789646d19b2bfae657ec74a6cba049e9de/src/main/java/com/example/Agents.java">
 * oktadev/quarkus-content-negotiation-example/[…]/Agents.java
 * </a>
 */
public enum Agents implements Agent {
    GENERIC("generic"),

    CURL("curl"),

    HTTPIE("HTTPie");

    private final String agentName;
    private static final Pattern TOOL_REGEX = Pattern.compile("([^\\/]*)\\/([^ ]*).*"); // .

    Agents(String agentName) {
        this.agentName = agentName;
    }

    @Override
    public String getAgentName() {
        return agentName;
    }

    public static Agents parse(String userAgent) {
        if (userAgent == null) {
            return GENERIC;
        }

        Matcher matcher = TOOL_REGEX.matcher(userAgent);
        String name = (matcher.matches()) ? matcher.group(1) : null;

        return Arrays.stream(Agents.values())
                .filter(agent -> agent.agentName.equals(name))
                .findFirst()
                .orElse(GENERIC);
    }
}
