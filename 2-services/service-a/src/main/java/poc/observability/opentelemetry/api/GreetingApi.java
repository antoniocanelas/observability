package poc.observability.opentelemetry.api;

import poc.observability.opentelemetry.domain.GreetingService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/service-a")
public class GreetingApi {

    private final GreetingService greetingService;

    @Inject
    public GreetingApi(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/proccess")
    public Response getGreeting(
            @HeaderParam("correlationId") final String correlationId,

            @QueryParam("language") final String language,
            final String name

    ) {
        final Response greeting = greetingService.proccessPerson(correlationId, language, name);

        return greeting;
    }

    @POST
    //@Consumes(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/local")
    public Response getLocalGreeting(
            @HeaderParam("correlationId") final String correlationId,

            @QueryParam("language") final String language,
            final String name

    ) {
        final String greeting = greetingService.greetingLocalPerson(correlationId, language, name);
        return Response.ok(greeting).type(MediaType.TEXT_PLAIN).build();
    }
}
