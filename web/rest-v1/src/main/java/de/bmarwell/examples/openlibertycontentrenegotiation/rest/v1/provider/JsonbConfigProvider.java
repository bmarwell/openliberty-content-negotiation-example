package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.provider;

import java.io.Serial;
import java.io.Serializable;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonbConfigProvider implements ContextResolver<Jsonb>, Serializable {

  @Serial
  private static final long serialVersionUID = -2635771645761444722L;

  @Override
  public Jsonb getContext(Class<?> type) {
    JsonbConfig config = getJsonbConfig();
    return JsonbBuilder.newBuilder().withConfig(config).build();
  }

  private JsonbConfig getJsonbConfig() {
    return new JsonbConfig()
        .withPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE_WITH_UNDERSCORES)
        .setProperty(JsonbConfig.FORMATTING, true)
        ;
  }
}
