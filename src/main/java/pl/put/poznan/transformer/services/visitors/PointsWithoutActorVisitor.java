package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Actor;
import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

import java.util.ArrayList;
import java.util.List;


public class PointsWithoutActorVisitor implements StepVisitor{

    private List<Actor> actors;
    private List<String> stepsWithoutActor = new ArrayList<String>();
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
        String text = step.getText();
        boolean isActorInText = false;
        for(String keyWord: listOfKeyWords){
            text = text.replace(keyWord + " ", ""); // "FOR EACH "
            text = text.replace(keyWord + ": ", ""); // "IF: ", "ELSE: "
        }

        for(int i = 0; i < getActors().size(); i++){
            if(text.startsWith(getActors().get(i).getNazwa())) {
                isActorInText = true;
            }
        }
        if(!isActorInText){
            getStepsWithoutActor().add(step.getText()); //dodajemy niezmieniony text
        }
    }


    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<String> getStepsWithoutActor() {
        return stepsWithoutActor;
    }

    public List<String> getListOfKeyWords() {
        return listOfKeyWords;
    }

    public void setListOfKeyWords(List<String> listOfKeyWords) {
        this.listOfKeyWords = listOfKeyWords;
    }
}