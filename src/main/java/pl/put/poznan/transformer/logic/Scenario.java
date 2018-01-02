package pl.put.poznan.transformer.logic;
import java.util.ArrayList;
import java.util.List;

public class Scenario implements Step {
    private String text = "";
    private List<Step> listOfSteps;
    private Scenario parent;

    public Scenario(String text, Scenario parent){
        setText(text);
        this.parent = parent;
        setListOfSteps(new ArrayList<Step>());
    }
    public Scenario(){
        this("", null);
    }
    public Scenario(String text, String inputText, Scenario parent){
        listOfSteps = new ArrayList<Step>();
        this.parent = parent;
        setText(text);

        String[] lines = inputText.split("\\r?\\n"); // split by new line (dzieli stringa wg znaku nowej linii \n)
        int i = 0;
        String textForChild = "";
        while (i < lines.length){

            if(i +1 < lines.length && !lines[i].startsWith("\t") && lines[i+1].startsWith("\t")){
                textForChild = lines[i]; //w nastepnej linii bedzie podscenariusz (dziecko)
                i++;
            }
            else if(lines[i].startsWith("\t")){ // punkty podscenariusza
                String inputTextForChild = "";
                while(i < lines.length && lines[i].startsWith("\t")){
                    inputTextForChild += lines[i].substring(1) + "\n"; // usun pierwszy \t
                    i++;
                }
                Scenario scenario = new Scenario(textForChild, inputTextForChild, this);
                listOfSteps.add(scenario);
            }
            else{
                //normalny punkt scenariusza
                SingleStep singleStep = new SingleStep(lines[i], this);
                listOfSteps.add(singleStep);
                i++;
            }
        }

    }
    @Override
    public void accept(StepVisitor visitor) {
        visitor.visisitScenario(this);
        for(Step step: getListOfSteps()){
            step.accept(visitor);
        }
    }

    @Override
    public void add(Step step) {
        getListOfSteps().add(step);
    }

    @Override
    public void remove(Step step) {
        getListOfSteps().remove(step);
    }

    @Override
    public Step getChild(int index) {
        return getListOfSteps().get(index);
    }

    @Override
    public int getIndexOfChild(Step step) {
        return getListOfSteps().indexOf(step);
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

    public List<Step> getListOfSteps() {
        return listOfSteps;
    }

    public void setListOfSteps(List<Step> listOfSteps) {
        this.listOfSteps = listOfSteps;
    }



}
