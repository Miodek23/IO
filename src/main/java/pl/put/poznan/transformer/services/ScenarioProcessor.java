package pl.put.poznan.transformer.services;

import org.springframework.stereotype.Service;
import pl.put.poznan.transformer.model.ProcessedScenario;
import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.UseCase;
import pl.put.poznan.transformer.services.visitors.KeyWordsVisitor;
import pl.put.poznan.transformer.services.visitors.NumberOfPointsInScenarioVisitor;
import pl.put.poznan.transformer.services.visitors.ScenarioInPointsVisitor;
import pl.put.poznan.transformer.services.visitors.ScenarioInPointsWithTabsVisitor;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioProcessor {
    public ProcessedScenario processScenario(UseCase useCase) {
        Scenario scenario = new Scenario(
                useCase.getScenario().getText(),
                useCase.getScenario().getInputText(),
                useCase.getScenario().getParent()
        );

        ScenarioInPointsVisitor visitor = new ScenarioInPointsVisitor();
        scenario.accept(visitor);
        System.out.println("scenario in points: " + "\n" + visitor.getOutputList());

        ScenarioInPointsWithTabsVisitor visitorTab = new ScenarioInPointsWithTabsVisitor();
        scenario.accept(visitorTab);
        System.out.println("scenario in points with tabs: " + "\n" + visitorTab.getOutputList());

        NumberOfPointsInScenarioVisitor visitorNumber = new NumberOfPointsInScenarioVisitor();
        scenario.accept(visitorNumber);
        System.out.println("Scenario's points: " + visitorNumber.getNumberOfPoints());

        List<String> slowaKluczowe = new ArrayList<>();
        slowaKluczowe.add("IF");
        slowaKluczowe.add("ELSE");
        slowaKluczowe.add("FOR EACH");

        KeyWordsVisitor visitorKey = new KeyWordsVisitor();
        visitorKey.setListOfKeyWords(slowaKluczowe);
        scenario.accept(visitorKey);
        System.out.println("Ile kroków zawiera słowa kluczowe: " + visitorKey.getNumberOfKeyWord());

        ProcessedScenario processedScenario =
                new ProcessedScenario(
                        visitor.getOutputList(),
                        visitorTab.getOutputList(),
                        visitorNumber.getNumberOfPoints(),
                        visitorKey.getNumberOfKeyWord());

        return processedScenario;

    }
}