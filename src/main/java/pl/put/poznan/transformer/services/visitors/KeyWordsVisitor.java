package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Actor;
import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

import java.util.ArrayList;
import java.util.List;

/** Klasa reprezentująca odwiedzającego, który ma za zadanie znaleźć, ile kroków zawiera słowa kluczowe,
 *  aby analityk mógł dowiedzieć się jak wiele decyzji warunkowych znajduje się w scenariuszu
 *  <p> Jest <b>ConcreteVistor</b>(konkretnym odwiedzającym) </p>
 * @author Oliwia Masian
 * @version 1.0
 */

public class KeyWordsVisitor implements StepVisitor {
    /**
     * liczba kroków zawierających słowa kluczowe (wynik)
     */
    int numberOfKeyWords;
    /**
     * lista zawierająca wszystkie zdefiniowane słowa określane jako kluczowe (wejście)
     */
    private List<String> listOfKeyWords;
    /**
     * operacja odwiedzenia pojedynczego kroku w scenariuszu, w celu sprawdzenia, czy zawiera słowa kluczowe
     * @param step krok do odwiedzenia
     */
    @Override
    public void visitSingleStep(SingleStep step) {
        visitStep(step);
    }

    /**
     * operacja odwiedzenia scenariusza, w celu sprawdzenia, czy jego treść
     * (tekst znajdujący się nad krokami) zawiera słowa kluczowe
     * @param scenario scenariusz do odwiedzenia
     */
    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);

    }

    /**
     * ogólna, prywatna metoda do sprawdzania, czy krok (pojedynczy lub cały scenariusz) zawiera słowa kluczowe;
     * jest wywoływana przez specjalizowane metody do odwiedzania kroków i scenariuszy
     * @param step
     */
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
