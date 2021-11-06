package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import static dev.diceroll.parser.Dice.detailedRoll;

import dev.diceroll.parser.ResultTree;
import java.io.Serial;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/eval")
@ApplicationScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class EvalResource implements Serializable {

  @Serial
  private static final long serialVersionUID = -4751317617404445262L;

  @GET
  public ResultTree evalObject(@QueryParam("dice") String dice) {
    return detailedRoll(dice);
  }

  @POST
  public ResultTree rollObjectPost(String dice) {
    return detailedRoll(dice);
  }

}
