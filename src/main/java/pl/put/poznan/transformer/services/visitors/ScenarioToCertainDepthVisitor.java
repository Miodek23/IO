package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

public class ScenarioToCertainDepthVisitor implements StepVisitor{

    private String outputList = "";
    private int maxDepth;

    public ScenarioToCertainDepthVisitor(int maxDepth) {
        this.setMaxDepth(maxDepth);
    }

    @Override
    public void visitSingleStep(SingleStep step) {
        if(this.getDepth(step) <= maxDepth){
            for(int i = 0; i < this.getDepth(step) - 1; i++){
                outputList  += "\t";
            }
            outputList += step.getText() + "\n";
        }

    }

    @Override
    public void visisitScenario(Scenario scenario) {
        if(this.getDepth(scenario) <= maxDepth){
            for(int i = 0; i < this.getDepth(scenario) - 1; i++){
                outputList  += "\t";
            }
            outputList += scenario.getText() + "\n";
        }

    }

    public String getOutputList(){
        return outputList;
    }
    public int getDepth(Step step){
        int depth = 0;
        Step parent = step.getParent();
        while(parent != null){
            depth++;
            parent = parent.getParent();
        }
        return depth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
