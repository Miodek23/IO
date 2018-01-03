package pl.put.poznan.transformer.services;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.model.UseCase;
import pl.put.poznan.transformer.services.visitors.KeyWordsVisitor;
import pl.put.poznan.transformer.services.visitors.ScenarioInPointsVisitor;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioProcessor {
    public void processScenario(UseCase useCase) {
        System.out.println("text: " + useCase.getScenario().getText());
        useCase.getAktorzyZewnetrzni().forEach(actor -> System.out.println(actor.getNazwa()));
        System.out.println("system name: " + useCase.getSystem().getNazwa());
        System.out.println("scenario title: " + useCase.getTytul());
        System.out.println("input text: " + useCase.getScenario().getInputText());

        ScenarioInPointsVisitor visitor2 = new ScenarioInPointsVisitor();
        useCase.getScenario().accept(visitor2);
        System.out.println(visitor2.getOutputList());
        List<String> slowaKluczowe = new ArrayList<>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");

        KeyWordsVisitor visitorKey = new KeyWordsVisitor();
        visitorKey.setListOfKeyWords(slowaKluczowe);
        useCase.getScenario().accept(visitorKey);
        System.out.println("Ile kroków zawiera słowa kluczowe: " + visitorKey.getNumberOfKeyWord());
    }
}