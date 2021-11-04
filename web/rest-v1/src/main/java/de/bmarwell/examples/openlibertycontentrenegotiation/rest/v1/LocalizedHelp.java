package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.io.Serializable;
import java.util.Locale;
import javax.ws.rs.core.UriInfo;

public interface LocalizedHelp extends Serializable {

  String getHelp(UriInfo uriInfo, Locale locale);
}
