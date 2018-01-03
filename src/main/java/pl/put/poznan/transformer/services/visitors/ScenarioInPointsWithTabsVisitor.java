package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

public class ScenarioInPointsWithTabsVisitor implements StepVisitor {

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
            String prefix = this.getNumPrefixWithTab(step);
            prefix = prefix.substring(1); //remove first \t
            outputList += prefix + " " + step.getText() + "\n";
        }
    }
    public String getOutputList(){
        return outputList;
    }

    private String getNumPrefixWithTab(Step step) { // np. \t\t2.4.1.
        String output = "";
        Step parent = step.getParent();
        if (parent != null)
            output += "\t" + getNumPrefixWithTab(parent) + (parent.getIndexOfChild(step )  + 1) + ".";
        return output;
    }

}
