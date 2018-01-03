package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

import java.util.List;

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
        for(String word: getListOfKeyWords()){
            if(step.getText().contains(word)){
                numberOfKeyWords++; //gdyby doszlo do sytuacji, ze w jednym kroku jest kilka slow kluczowych, liczymy to tylko raz
                break;
            }
        }

    }
    public int getNumberOfKeyWord(){
        return this.numberOfKeyWords;
    }

    public List<String> getListOfKeyWords() {
        return listOfKeyWords;
    }

    public void setListOfKeyWords(List<String> listOfKeyWords) {
        this.listOfKeyWords = listOfKeyWords;
    }
}
