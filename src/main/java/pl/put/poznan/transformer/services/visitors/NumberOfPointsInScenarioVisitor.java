package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

/** Klasa reprezentująca odwiedzającego, który ma za zadanie znaleźć,
 * ile kroków zawiera cały scenariusz (łącznie z pod-scenariuszami),
 * aby analityk mógł ocenić, jak bardzo jest skomplikowany
 *  <p> Jest <b>ConcreteVistor</b> (konkretnym odwiedzającym) </p>
 * @author Oliwia Masian
 * @version 1.0
 */

public class NumberOfPointsInScenarioVisitor implements StepVisitor {
    /**
     * liczba punktów scenariusza (wynik)
     */
    int numberOfPoints;

    /**
     * operacja odwiedzenia pojedynczego kroku w scenariuszu (podbija wynik o +1)
     * @param step krok do odwiedzenia
     */
    @Override
    public void visitSingleStep(SingleStep step) {
       visitStep(step);
    }

    /**
     * operacja odwiedzenia scenariusza (podbija wynik o +1)
     * @param scenario scenariusz do odwiedzenia
     */
    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);
    }

    /**
     * ogólna, prywatna metoda odwiedzenia kroku (podbija wynik o +1);
     * jest wywoływana przez specjalizowane metody do odwiedzania kroków i scenariuszy
     * @param step krok do odwiedzenia
     */
    private void visitStep(Step step){
        numberOfPoints += 1;
    }

    /**
     * zwraca liczbę kroków w całym scenariuszu
     * @return liczba kroków w scenariuszu
     */
    public int getNumberOfPoints(){
        return numberOfPoints - 1; // -1, bo nie liczymy nadscenariusza (scenariusza, ktory nie ma rodzica)
    }
}
