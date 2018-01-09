package pl.put.poznan.transformer.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.model.ProcessedScenario;
import pl.put.poznan.transformer.model.UseCase;
import pl.put.poznan.transformer.services.ScenarioProcessor;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;


@RestController
@RequestMapping(value = "/scenario")
public class TextTransformerController {
    private final ScenarioProcessor scenarioProcessor;

    @Autowired
    public TextTransformerController(ScenarioProcessor scenarioProcessor) {
        this.scenarioProcessor = scenarioProcessor;
    }

    @PostMapping(value = "/processed")
    public ResponseEntity<ProcessedScenario> getProcessedScenario(@RequestBody UseCase useCase) {
        return Optional.ofNullable(scenarioProcessor.processScenario(useCase))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>((ProcessedScenario) null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/inPoints")
    public ResponseEntity<String> getScenarioInPoints(@RequestBody UseCase useCase) {
        return Optional.ofNullable(scenarioProcessor.processScenario(useCase))
                .map(result -> new ResponseEntity<>(result.getScenarioInPoints(), HttpStatus.OK))
                .orElse(new ResponseEntity<>((String) null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/pointsWithTabs")
    public ResponseEntity<String> getScenarioInPointsWithTabs(@RequestBody UseCase useCase) {
        return Optional.ofNullable(scenarioProcessor.processScenario(useCase))
                .map(result -> new ResponseEntity<>(result.getScenarioWithTabs(), HttpStatus.OK))
                .orElse(new ResponseEntity<>((String) null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/numberOfPoints")
    public ResponseEntity<Integer> getScenarioNumberOfPoints(@RequestBody UseCase useCase) {
        return Optional.ofNullable(scenarioProcessor.processScenario(useCase))
                .map(result -> new ResponseEntity<>(result.getNumberOfPoints(), HttpStatus.OK))
                .orElse(new ResponseEntity<>((Integer) null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/keywords")
    public ResponseEntity<Integer> getScenarioKeywords(@RequestBody UseCase useCase) {
        return Optional.ofNullable(scenarioProcessor.processScenario(useCase))
                .map(result -> new ResponseEntity<>(result.getNumberOfKeywords(), HttpStatus.OK))
                .orElse(new ResponseEntity<>((Integer) null, HttpStatus.NOT_FOUND));
    }
}