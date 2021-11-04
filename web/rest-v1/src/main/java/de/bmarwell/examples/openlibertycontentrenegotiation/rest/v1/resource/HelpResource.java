package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.LocalizedHelp;
import de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.filter.LanguageFilter;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

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
  public String help(
      @Context Request request,
      @Context UriInfo uriInfo) {
    return localizedHelp.getHelp(
        uriInfo,
        request.selectVariant(LanguageFilter.VARIANTS).getLanguage());
  }

  public LocalizedHelp getLocalizedHelp() {
    return localizedHelp;
  }

}
