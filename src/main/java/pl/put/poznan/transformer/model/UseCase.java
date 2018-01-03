package pl.put.poznan.transformer.model;

import java.util.*;

public class UseCase {
    private String tytul;
    private List<Actor> aktorzyZewnetrzni;
    private Actor system;
    private Scenario scenario;


    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public List<Actor> getAktorzyZewnetrzni() {
        return aktorzyZewnetrzni;
    }

    public void setAktorzyZewnetrzni(List<Actor> aktorzyZewnetrzni) {
        this.aktorzyZewnetrzni = aktorzyZewnetrzni;
    }

    public Actor getSystem() {
        return system;
    }

    public void setSystem(Actor system) {
        this.system = system;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public UseCase(String tytul, List<Actor> aktorzyZewnetrzni, Actor system, Scenario scenario) {
        this.tytul = tytul;
        this.aktorzyZewnetrzni = aktorzyZewnetrzni;
        this.system = system;
        this.scenario = scenario;
    }

    public UseCase() {
    }
}
