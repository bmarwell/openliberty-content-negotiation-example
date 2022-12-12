package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import static dev.diceroll.parser.Dice.detailedRoll;

import dev.diceroll.parser.ResultTree;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.Serial;
import java.io.Serializable;

@Path("/roll")
@ApplicationScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class DiceResource implements Serializable {

    @Serial
    private static final long serialVersionUID = 8457184509620558191L;

    @GET
    public ResultTree rollObject(@QueryParam("dice") String dice) {
        return detailedRoll(dice);
    }

    @POST
    public ResultTree rollObjectPost(String dice) {
        return detailedRoll(dice);
    }
}
