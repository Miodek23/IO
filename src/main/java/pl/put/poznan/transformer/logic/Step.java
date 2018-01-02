package pl.put.poznan.transformer.logic;

public interface Step {

    public void accept(StepVisitor visitor);
    public void add(Step step);
    public void remove(Step step);
    public Step getChild(int index);
    public Scenario getParent();
    public void setParent(Scenario scenario);
    public int getIndexOfChild(Step step);
    public String getText();
    public void setText(String text);

}
