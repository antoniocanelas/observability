package poc.observability.opentelemetry.domain;

import poc.observability.opentelemetry.infrastructure.client.GreetingClientAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class GreetingService {

    private final GreetingClientAPI proccessPersonClientAPI;

    @Inject
    public GreetingService(
            @RestClient
            final GreetingClientAPI proccessPersonClientAPI) {
        this.proccessPersonClientAPI = proccessPersonClientAPI;

    }


    public Response proccessPerson(String correlationId, String language, String name) {
//        System.out.println("* Service B*\ncorrelationId: " + correlationId + "\nlanguage: " + language + "\nname: " + name);
        return proccessPersonClientAPI.greetingPerson(correlationId, language, name);
    }

    public String greetingLocalPerson(String correlationId, String language, String name) {
        return "* Service A*\ncorrelationId: " + correlationId + "\nlanguage: " + language + "\nname: " + name;
    }
}
