package poc.observability.opentelemetry.domain;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProccessNameService {


    public String proccessPerson(String correlationId, String language, String name) {
        return "* Service B*\ncorrelationId: " + correlationId + "\nlanguage: " + language + "\nname: " + name;
    }
}
