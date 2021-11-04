package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1;

import de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.filter.UserAgentFilter;
import java.io.Serial;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@RequestScoped
public class AgentProducer implements Serializable {

  @Serial
  private static final long serialVersionUID = -7936816035015642025L;

  @Produces
  public Agent getAgent() {
    return Agents.parse(UserAgentFilter.userAgent.get());
  }

}
