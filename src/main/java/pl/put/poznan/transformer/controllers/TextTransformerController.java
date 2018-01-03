package pl.put.poznan.transformer.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.model.UseCase;
import pl.put.poznan.transformer.services.ScenarioProcessor;

import java.util.Optional;


@RestController
public class TextTransformerController {
    private final ScenarioProcessor scenarioProcessor;

    @Autowired
    public TextTransformerController(ScenarioProcessor scenarioProcessor) {
        this.scenarioProcessor = scenarioProcessor;
    }

    @PostMapping(value = "/getScenarioParams")
    public ResponseEntity getScenarioParameters(@RequestBody UseCase useCase) {
        scenarioProcessor.processScenario(useCase);
        return new ResponseEntity(HttpStatus.OK);
    }
}


