package de.bmarwell.examples.openlibertycontentrenegotiation.rest.v1.resource;

import static dev.diceroll.parser.Dice.detailedRoll;

import dev.diceroll.parser.ParseException;
import dev.diceroll.parser.ResultTree;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Variant;

@Path("/roll")
@ApplicationScoped
public class DiceResource implements Serializable {

  @GET
  public ResultTree rollObject(@QueryParam("dice") String dice) throws ParseException {
    return detailedRoll(dice);
  }

  @Path("/lang")
  @GET
  public Response getLang(@Context Request request) {
    List<Variant> variants = Variant.VariantListBuilder.newInstance()
        .languages(Locale.ENGLISH, Locale.GERMAN)
        .build();

    Variant variant = request.selectVariant(variants);

    if (variant == null) {
      return Response.notAcceptable(variants).build();
    }

    // set the response header, to the client knows which language was selected
    String lang = variant.getLanguageString();
    return Response.ok(lang)
        .header(HttpHeaders.CONTENT_LANGUAGE, lang)
        .build();
  }
}
