package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.core.HttpHeaders;
import java.io.Serial;
import java.io.Serializable;

@RequestScoped
public class AgentProducer implements Serializable {

    @Serial
    private static final long serialVersionUID = -7936816035015642025L;

    @Produces
    @HttpHeader
    public String header(HttpServletRequest req, InjectionPoint ip) {
        HttpHeader annotation = ip.getAnnotated().getAnnotation(HttpHeader.class);

        if (annotation.value().isEmpty()) {
            return "";
        }

        return req.getHeader(annotation.value());
    }

    @Produces
    public Agent getAgent(@HttpHeader(HttpHeaders.USER_AGENT) String userAgent) {
        return Agents.parse(userAgent);
    }
}
