package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

public class ScenarioInPointsVisitor implements StepVisitor{
    private String outputList = "";

    @Override
    public void visitSingleStep(SingleStep step) {
        visitStep(step);

    }

    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);

    }
    private void visitStep(Step step){
        if(!step.getText().equals("")) {
            outputList += this.getNumPrefix(step) + " " + step.getText() + "\n";
        }
    }
    public String getOutputList(){
        return outputList;
    }

    private String getNumPrefix(Step step) { //np. 2.4.1.
        String output = "";
        Step parent = step.getParent();
        if (parent != null)
            output += getNumPrefix(parent) + (parent.getIndexOfChild(step )  + 1) + ".";
        return output;
    }
}
