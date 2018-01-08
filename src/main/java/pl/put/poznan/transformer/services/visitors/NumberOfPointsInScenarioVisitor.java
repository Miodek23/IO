package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

public class NumberOfPointsInScenarioVisitor implements StepVisitor {
    int numberOfPoints;

    @Override
    public void visitSingleStep(SingleStep step) {
       visitStep(step);
    }

    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);
    }

    private void visitStep(Step step){
        numberOfPoints += 1;
    }

    public int getNumberOfPoints(){
        return numberOfPoints - 1; // -1, bo nie liczymy nadscenariusza (scenariusza, ktory nie ma rodzica)
    }
}
