package pl.put.poznan.transformer.services.visitors;

import pl.put.poznan.transformer.model.Scenario;
import pl.put.poznan.transformer.model.SingleStep;
import pl.put.poznan.transformer.model.Step;

/** Klasa reprezentująca odwiedzającego, który ma za zadanie uzyskać scenariusz
 * zawierający pod-scenariusze tylko do określonego poziomu,
 * aby analityk mógł zobaczyć uproszczoną wersję wymagań
 *  <p> Jest <b>ConcreteVistor</b> (konkretnym odwiedzającym) </p>
 * @author Oliwia Masian
 * @version 1.0
 */

public class ScenarioToCertainDepthVisitor implements StepVisitor{

    /**
     * scenariusz zawierający podscenariusze do określonego poziomu (wyjście),
     * jest to <b>surowy</b> tekst z podscenariuszami o odpowiednich wcięciach-
     * (<b>nie jest</b> to złożony obiekt Scenario)
     */
    private String outputList = "";
    /**
     * maksymalna głębokość- poziom, do którego chcemy mieć podscenariusze:
     * <ul>
     *     <li> Przy poziomie = 1 zwracany jest tylko scenariusz najwyższego poziomu </li>
     *     <li> Przy poziomach > 1 zwracane są scenariusze do danego poziomu włącznie
     *     (np. poziom = 2 oznacza scenariusz na najwyższym poziomie oraz jego bezpośrednie pod-scenariusze)</li>
     *
     </ul>
     */
    private int maxDepth;

    /**
     * Tworzy nowy obiekt wizytatora z zadaną maksymalną głębokością
     * @param maxDepth maksymalna głębokość
     */

    public ScenarioToCertainDepthVisitor(int maxDepth) {
        this.setMaxDepth(maxDepth);
    }
    /**
     * operacja odwiedzenia pojedynczego kroku w scenariuszu;
     * jeżeli zagłębienie kroku jest mniejsze
     * niż maksymalna głębokość, treść kroku zostaje dodana do wyniku
     * @param step krok do odwiedzenia
     */
    @Override
    public void visitSingleStep(SingleStep step) {
        visitStep(step);

    }

    /**
     * operacja odwiedzenia scenariusza;
     * jeżeli zagłębienie scenariusza jest mniejsze
     * niż maksymalna głębokość, treść scenariusza (tytuł- tekst umieszczony nad podscenariuszami)
     * zostaje dodana do wyniku
     * @param scenario scenariusz do odwiedzenia
     */
    @Override
    public void visisitScenario(Scenario scenario) {
        visitStep(scenario);

    }

    /**
     * ogólna, prywatna metoda odwiedzenia kroku- jeżeli zagłębienie kroku jest mniejsze
     * niż maksymalna głębokość, treść kroku zostaje dodana do wyniku;
     * metoda jest wywoływana przez specjalizowane metody do odwiedzania kroków i scenariuszy
     * @param step krok do odwiedzenia
     */
    private void visitStep(Step step){
        if(this.getDepth(step) <= maxDepth){
            for(int i = 0; i < this.getDepth(step) - 1; i++){
                outputList  += "\t";
            }
            outputList += step.getText() + "\n";
        }
    }

    public String getOutputList(){
        return outputList;
    }

    /**
     * pomocnicza metoda określająca głębokość danego kroku w całym scenariuszu
     * @param step krok, którego głębokość chcemy poznać
     * @return głębokość kroku w scenariuszu
     */
    private int getDepth(Step step){
        int depth = 0;
        Step parent = step.getParent();
        while(parent != null){
            depth++;
            parent = parent.getParent();
        }
        return depth;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
