package it.lorenzogiorgi.tesi.common;

import java.util.List;

public class Application {
    private String name;
    private boolean sharable;
    private List<Microservice> microservices;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSharable() {
        return sharable;
    }

    public void setSharable(boolean sharable) {
        this.sharable = sharable;
    }

    public List<Microservice> getMicroservices() {
        return microservices;
    }

    public void setMicroservices(List<Microservice> microservices) {
        this.microservices = microservices;
    }

}
