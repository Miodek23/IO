package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;

public interface StepVisitor {
    public void visitSingleStep(SingleStep step);
    public void visisitScenario(Scenario scenario);

}
