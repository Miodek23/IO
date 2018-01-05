package pl.put.poznan.transformer.logic;

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
    public static void main(String[] args) {

        /** PIERWSZY SPOSOB KORZYSTANIA Z KLASY **/

        Scenario scenario = new Scenario();
        SingleStep s1 = new SingleStep("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej", scenario);
        scenario.add(s1);
        SingleStep s2 = new SingleStep("Wyświetla się formularz.", scenario);
        scenario.add(s2);
        SingleStep s3 = new SingleStep("Bibliotekarz podaje dane książki.", scenario);
        scenario.add(s3);
        Scenario s4 = new Scenario("IF: Bibliotekarz pragnie dodać egzemplarze książki", scenario);
        scenario.add(s4);
        SingleStep s41 = new SingleStep("Bibliotekarz wybiera opcję definiowania egzemplarzy", s4);
        s4.add(s41);
        SingleStep s42 = new SingleStep("System prezentuje zdefiniowane egzemplarze", s4);
        s4.add(s42);
        Scenario s43 = new Scenario("FOR EACH egzemplarz:", s4);
        s4.add(s43);
        SingleStep s431 = new SingleStep("Bibliotekarz wybiera opcję dodania egzemplarza", s43);
        s43.add(s431);
        SingleStep s432 = new SingleStep("System prosi o podanie danych egzemplarza", s43);
        s43.add(s432);
        SingleStep s433 = new SingleStep("Bibliotekarz podaje dane egzemplarza i zatwierdza.", s43);
        s43.add(s433);
        SingleStep s434 = new SingleStep("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.", s43);
        s43.add(s434);
        SingleStep s5 = new SingleStep("Bibliotekarz zatwierdza dodanie książki.", scenario);
        scenario.add(s5);
        SingleStep s6 = new SingleStep("System informuje o poprawnym dodaniu książki.", scenario);
        scenario.add(s6);

        Actor system = new Actor("System");
        List<Actor> aktorzy = new ArrayList<Actor>();
        aktorzy.add(new Actor("Bibliotekarz"));
        UseCase useCase = new UseCase("Jakis tytul", aktorzy, system, scenario);

        scenario = useCase.getScenario(); // tego nie musi byc

        ScenarioInPointsVisitor visitor = new ScenarioInPointsVisitor();
        scenario.accept(visitor);
        System.out.println(visitor.getOutputList());

        ScenarioInPointsWithTabsVisitor visitorTab = new ScenarioInPointsWithTabsVisitor();
        scenario.accept(visitorTab);
        System.out.println(visitorTab.getOutputList());

        NumberOfPointsInScenarioVisitor visitorNumber = new NumberOfPointsInScenarioVisitor();
        scenario.accept(visitorNumber);
        System.out.println("Scenariusz ma liczbe punktow rowna: " + visitorNumber.getNumberOfPoints());





        /** DRUGI SPOSOB KORZYSTANIA Z KLASY **/
        
        String text = "Bibliotekarz wybiera opcje dodania nowej pozycji książkowej\n" +
                "Wyświetla się formularz.\n" +
                "Bibliotekarz podaje dane książki.\n" +
                "IF: Bibliotekarz pragnie dodać egzemplarze książki\n" +
                "\tBibliotekarz wybiera opcję definiowania egzemplarzy\n" +
                "\tSystem prezentuje zdefiniowane egzemplarze\n" +
                "\tFOR EACH egzemplarz:\n" +
                "\t\tBibliotekarz wybiera opcję dodania egzemplarza\n" +
                "\t\tSystem prosi o podanie danych egzemplarza\n" +
                "\t\tBibliotekarz podaje dane egzemplarza i zatwierdza.\n" +
                "\t\tSystem informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy.\n" +
                "Bibliotekarz zatwierdza dodanie książki.\n" +
                "System informuje o poprawnym dodaniu książki.\n";
        Scenario scenario2 = new Scenario("", text, null);
        UseCase useCase2 =  new UseCase("Jakis tytul", aktorzy, system, scenario2);


        ScenarioInPointsVisitor visitor2 = new ScenarioInPointsVisitor();
        scenario2.accept(visitor2);
        System.out.println(visitor2.getOutputList());
        List<String> slowaKluczowe = new ArrayList<String>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");

        KeyWordsVisitor visitorKey = new KeyWordsVisitor();
        visitorKey.setListOfKeyWords(slowaKluczowe);
        scenario2.accept(visitorKey);
        System.out.println("Ile kroków zawiera słowa kluczowe: " + visitorKey.getNumberOfKeyWords());


    }
}
