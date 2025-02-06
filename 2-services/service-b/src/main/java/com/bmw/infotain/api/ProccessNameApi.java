package poc.observability.opentelemetry.api;

import poc.observability.opentelemetry.domain.ProccessNameService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/service-b")
public class ProccessNameApi {

    private final ProccessNameService proccessNameService;

    @Inject
    public ProccessNameApi(final ProccessNameService proccessNameService) {
        this.proccessNameService = proccessNameService;
    }

    @POST
    //@Consumes(MediaType.TEXT_PLAIN)
    //@Produces(MediaType.TEXT_PLAIN)
    @Path("/proccess")
    public Response getLocalGreeting(
            @HeaderParam("correlationId") final String correlationId,

            @QueryParam("language") final String language,
            final String name

    ) {
        final String greeting = proccessNameService.proccessPerson(correlationId, language, name);
        return Response.ok(greeting).type(MediaType.TEXT_PLAIN).build();
    }
}
