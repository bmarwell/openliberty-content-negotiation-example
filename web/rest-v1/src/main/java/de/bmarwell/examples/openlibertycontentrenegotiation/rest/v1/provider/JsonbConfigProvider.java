package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.provider;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.config.PropertyNamingStrategy;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;
import java.io.Serial;
import java.io.Serializable;

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
                .setProperty(JsonbConfig.FORMATTING, true);
    }
}
