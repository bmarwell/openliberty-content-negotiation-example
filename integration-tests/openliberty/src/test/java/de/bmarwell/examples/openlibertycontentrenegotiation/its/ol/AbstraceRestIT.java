package de.bmarwell.examples.openlibertycontentrenegotiation.its.ol;

import java.net.URI;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.apache.johnzon.jaxrs.jsonb.jaxrs.JsonbJaxrsProvider;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class AbstraceRestIT {

  final Client client = ClientBuilder.newClient()
      .register(new JsonbJaxrsProvider<>());

  protected URI getBaseUri() {
    return URI.create(
        "http://localhost:" + System.getProperty("http.port") + "/" + System.getProperty(
            "app.context.root") + "/");
  }
}
