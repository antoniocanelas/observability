package poc.observability.opentelemetry.infrastructure.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "service-b-api-client")
@Path("/service-b")
public interface GreetingClientAPI {

    @POST
    @Path("/proccess")
    @Produces(MediaType.TEXT_PLAIN)
    Response greetingPerson(
            @HeaderParam("correlationId") String correlationId,
            @QueryParam("language") String language,
            String name);

}