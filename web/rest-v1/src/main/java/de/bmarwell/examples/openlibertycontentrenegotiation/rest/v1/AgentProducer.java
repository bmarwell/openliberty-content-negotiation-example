package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

@RequestScoped
public class AgentProducer implements Serializable {

  @Context
  HttpHeaders httpHeaders;

  @Produces
  @Default
  public Agent getAgent() {
    final String userAgentHeader = httpHeaders.getHeaderString(HttpHeaders.USER_AGENT);

    return Agents.parse(userAgentHeader);
  }

}
