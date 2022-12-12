package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.LocalizedHelp;
import de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.filter.LanguageFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;

@Path("/")
@ApplicationScoped
public class HelpResource {

    @Inject
    private LocalizedHelp localizedHelp;

    public HelpResource() {
        // cdi
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String help(@Context Request request, @Context UriInfo uriInfo) {
        return localizedHelp.getHelp(
                uriInfo, request.selectVariant(LanguageFilter.VARIANTS).getLanguage());
    }

    public LocalizedHelp getLocalizedHelp() {
        return localizedHelp;
    }
}
