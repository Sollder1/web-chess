package de.sollder1.webchess.backend.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/greetings")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreeting() throws JsonProcessingException {
        GreetingBean bean = new GreetingBean("Hello from the OTHER SIDE!");
        return Response.status(200).entity(new ObjectMapper().writeValueAsString(bean)).build();
    }

}
