package pl.put.poznan.transformer.model;

import pl.put.poznan.transformer.services.visitors.StepVisitor;

public class SingleStep implements Step {
    private String text;
    private Scenario parent;

    public SingleStep(String text, Scenario parent){
        setText(text);
        this.parent = parent;
    }

    @Override
    public void accept(StepVisitor visitor) {
        visitor.visitSingleStep(this);
    }

    @Override
    public String getText(){
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Scenario getParent() {
        return parent;
    }

    @Override
    public void setParent(Scenario scenario) {
        this.parent = scenario;
    }

    @Override
    public void add(Step step) {
        //this is leaf node so this method is not applicable to this class.
    }

    @Override
    public void remove(Step step) {
        //this is leaf node so this method is not applicable to this class.
    }

    @Override
    public Step getChild(int index) {
        //this is leaf node so this method is not applicable to this class.
        return null;
    }

    @Override
    public int getIndexOfChild(Step step) {
        //this is leaf node so this method is not applicable to this class.
        return 0;
    }
}
