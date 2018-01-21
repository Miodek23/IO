package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;

/** Interfejs reprezentujący odwiedzającego scenariusze
 * <p>
 * Realizuje wzorzec <b> Wizytator/Odwiedzajacy </b>
 * @author Oliwia Masian
 * @version 1.0
 */

public interface StepVisitor {
    /**
     * operacja odwiedzenia pojedynczego kroku w scenariuszu, w celu wykonania na nim jakichś operacji
     * @param step krok do odwiedzenia
     */
    public void visitSingleStep(SingleStep step);

    /**
     *  operacja odwiedzenia scenariusza (elemntu złożonego), w celu wykonania na nim jakichś operacji
     * @param scenario scenariusz do odwiedzenia
     */
    public void visisitScenario(Scenario scenario);

}
