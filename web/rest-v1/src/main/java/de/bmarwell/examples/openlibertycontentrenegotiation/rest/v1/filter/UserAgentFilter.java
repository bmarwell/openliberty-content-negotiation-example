package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

@Provider
public class UserAgentFilter implements ContainerRequestFilter {

  public static final ThreadLocal<String> userAgent = new ThreadLocal<>();

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    final String headerString = requestContext.getHeaderString(HttpHeaders.USER_AGENT);

    userAgent.set(headerString);
  }
}
