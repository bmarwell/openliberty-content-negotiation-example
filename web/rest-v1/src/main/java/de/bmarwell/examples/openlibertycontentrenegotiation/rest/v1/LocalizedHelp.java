package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.io.Serializable;
import java.util.Locale;
import javax.ws.rs.core.UriInfo;

/**
 * Returns a help String for the given URI and locale.
 *
 * <p>The UriInfo field is actually used as a first parameter only for formatting messages.</p>
 */
public interface LocalizedHelp extends Serializable {

    String getHelp(UriInfo uriInfo, Locale locale);
}
