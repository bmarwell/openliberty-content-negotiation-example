package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Variant;
import jakarta.ws.rs.ext.Provider;
import java.util.List;
import java.util.Locale;

@Provider
public class LanguageFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String LANG = "LanguageFilter.lang";

    public static final List<Variant> VARIANTS = Variant.VariantListBuilder.newInstance()
            .languages(Locale.ENGLISH, Locale.GERMAN) // .
            .build();

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Variant variant = requestContext.getRequest().selectVariant(VARIANTS); // .

        if (variant == null) { // .
            // Error, respond with 406
            requestContext.abortWith(Response.notAcceptable(VARIANTS).build());
        } else {
            // keep the resolved lang around for the response
            requestContext.setProperty(LANG, variant.getLanguageString()); // .
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        String lang = (String) requestContext.getProperty(LANG);
        responseContext.getHeaders().putSingle(HttpHeaders.CONTENT_LANGUAGE, lang); // .
    }
}
