package pl.put.poznan.transformer.logic;

public interface StepVisitor {
    public void visitSingleStep(SingleStep step);
    public void visisitScenario(Scenario scenario);

}
