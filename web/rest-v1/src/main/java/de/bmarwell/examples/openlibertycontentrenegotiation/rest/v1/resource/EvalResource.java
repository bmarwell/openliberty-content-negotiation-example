package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import static dev.diceroll.parser.Dice.roll;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import java.io.Serial;
import java.io.Serializable;

@Path("/eval")
@ApplicationScoped
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class EvalResource implements Serializable {

    @Serial
    private static final long serialVersionUID = -4751317617404445262L;

    @GET
    public int evalObject(@QueryParam("dice") String dice) {
        return roll(dice);
    }

    @POST
    public int rollObjectPost(String dice) {
        return roll(dice);
    }
}
