package de.bmarwell.examples.openlibertycontentrenegotiation.its.ol;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import java.net.URI;
import org.apache.johnzon.jaxrs.jsonb.jaxrs.JsonbJaxrsProvider;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class AbstraceRestIT {

    final Client client = ClientBuilder.newClient().register(new JsonbJaxrsProvider<>());

    protected URI getBaseUri() {
        final String httpPort = System.getProperty("http.port");
        final String appContextRoot = System.getProperty("app.context.root");

        return URI.create("http://localhost:%s/%s/".formatted(httpPort, appContextRoot));
    }
}
