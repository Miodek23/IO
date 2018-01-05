package pl.put.poznan.transformer.logic;
import java.util.List;
import java.util.ArrayList;

public class KeyWordsVisitor implements StepVisitor {
    int numberOfKeyWords;
    private List<String> listOfKeyWords;
    @Override
    public void visitSingleStep(SingleStep step) {
        visitStep(step);
    }

    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);

    }
    private void visitStep(Step step){
        for(String word: listOfKeyWords){
            if(step.getText().contains(word)){
                numberOfKeyWords++; //gdyby doszlo do sytuacji, ze w jednym kroku jest kilka slow kluczowych, liczymy to tylko raz
                break;
            }
        }

    }
    public int getNumberOfKeyWords(){
        return this.numberOfKeyWords;
    }

    public List<String> getListOfKeyWords() {
        return listOfKeyWords;
    }

    public void setListOfKeyWords(List<String> listOfKeyWords) {
        this.listOfKeyWords = listOfKeyWords;
    }
}
