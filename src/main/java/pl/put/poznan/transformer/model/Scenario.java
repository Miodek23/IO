package pl.put.poznan.transformer.model;

import pl.put.poznan.transformer.services.visitors.StepVisitor;

import java.util.ArrayList;
import java.util.List;


/** Klasa reprezentująca złożony scenariusz (składający się z kroków bądź podscenariuszy).
 * <p>
 * Pełni rolę <b> Kompozytu (elementu złożonego) </b> we wzorcu <b> Kompozyt </b>.
 * @author Oliwia Masian
 * @version 1.0
 */

public class Scenario implements Step {
    /**
     * Tekst (treść) scenariusza, umieszczona nad krokami,
     * dla najstarszego nadscenariusza jest pustym stringiem ""
     */
    private String text = "";
    /**
     * lista kroków dzieci (pojedynczych kroków lub podscenariuszy)
     */
    private List<Step> listOfSteps;
    /**
     * Rodzic (nadscenariusz) dla danego scenariusza
     */
    private Scenario parent;

    /**
     * Surowy tekst, z którego powstanie złożona struktura scenariusza
     */
    private String inputText;

    /**
     * Tworzy scenariusz z określoną treścią i rodzicem
     * @param text tekst (treść) kroku
     * @param parent rodzic (nadscenariusz)
     */
    public Scenario(String text, Scenario parent){
        setText(text);
        this.parent = parent;
        setListOfSteps(new ArrayList<Step>());
    }

    /**
     * Tworzy scenariusz z pustym tekstem i rodzicem wskazującym na null.
     * Ten konstruktor można użyć do tworzenia najstarszego nadscenariusza,
     * który nie ma tekstu ani rodzica.
     */
    public Scenario(){
        this("", null);
    }

    /**
     * Tworzy scenariusz z zadanym tekstem, rodzicem oraz przetwarza
     * inputText w odpowiednią strukturę dzieci (kroków oraz podscenariuszy).
     * @param text tekst (treść) kroku
     * @param inputText struktura, która zostanie przetworzona na dzieci,
     *                  podscenariusze i kroki- każdy krok jest oddzielony znakiem
     *                  nowej linii, a podscenariusze dodatkowo znakiem tabulatora
     *                  wg poziomu zagłębienia
     * @param parent referencja na rodzica (nadscenariusz)
     */
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

    /**
     * Metoda realizująca przyjęcie odwiedzającego- przyjmuje wizytatora
     * u siebie (scenariusza), a następnie wywołuje accept dla wszystkich dzieci
     * (kroków lub podscenariuszy)
     * @param visitor odwiedzający
     */
    @Override
    public void accept(StepVisitor visitor) {
        visitor.visisitScenario(this);
        for(Step step: getListOfSteps()){
            step.accept(visitor);
        }
    }

    /**
     * Dodaje kolejny krok w scenariuszu (dziecko- pojedynczy krok bądź podscenariusz)
     * @param step krok do dodania
     */
    @Override
    public void add(Step step) {
        getListOfSteps().add(step);
    }

    /**
     * Usuwa krok w scenariuszu (dziecko- pojedynczy krok bądź podscenariusz) z listy dzieci
     * @param step krok do usunięcia
     */
    @Override
    public void remove(Step step) {
        getListOfSteps().remove(step);
    }

    /**
     * Zwraca referencję na dziecko (krok w scenariuszu) o podanym indeksie
     * @param index indeks kroku (dziecka) w liście kroków
     * @return  referencja na dziecko
     */
    @Override
    public Step getChild(int index) {
        return getListOfSteps().get(index);
    }

    /**
     * Zwraca indeks dziecka o podanej referencji
     * @param step dziecko, którego indeks w liście kroków chcemy otrzymać
     * @return indeks dziecka
     */
    @Override
    public int getIndexOfChild(Step step) {
        return getListOfSteps().indexOf(step);
    }

    /**
     * Zwraca tekst kroku (w przypadku najstarszego nadscenariusza tekst jest zawsze pusty,
     * dzieci nadscenariusza będące podscenariuszami mogą mieć niepusty tekst)
     * @return tekst kroku (treść kroku)
     */
    @Override
    public String getText(){
        return this.text;
    }

    /**
     * Ustawia tekst kroku (w przypadku najstarszego nadscenariusza tekst jest zawsze pusty,
     * dzieci nadscenariusza będące podscenariuszami mogą mieć niepusty tekst)
     * @param text tekst kroku (treść kroku)
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Zwraca referencję na rodzica (nadscenariusz) lub null,
     * jeśli obiekt nie ma rodzica (jest najstarszym nadscenariuszem)
     * @return referencja na rodzica
     */
    @Override
    public Scenario getParent() {
        return parent;
    }

    /**
     * Ustawia rodzica danego kroku
     * @param scenario rodzic
     * (rodzic może być tylko scenariuszem, pojedyncze kroki nigdy nie mają dzieci)
     */
    @Override
    public void setParent(Scenario scenario) {
        this.parent = scenario;
    }

    /**
     * Zwraca listę kroków w scenariuszu (listę dzieci- pojedynczych kroków lub podscenariuszy)
     * @return lista kroków
     */

    public List<Step> getListOfSteps() {
        return listOfSteps;
    }

    /**
     * Ustawia listę kroków w scenariuszu (listę dzieci- pojedynczych kroków lub podscenariuszy)
     * @param listOfSteps lista kroków
     */
    public void setListOfSteps(List<Step> listOfSteps) {
        this.listOfSteps = listOfSteps;
    }

    /**
     * Zwraca tekst użyty do tworzenia zależności
     * @return tekst użyty do tworzenia zależności
     */
    public String getInputText() {
        return inputText;
    }

    /**
     * Ustawia tekst użyty do tworzenia zależności
     * @param inputText tekst użyty do tworzenia zależności
     */
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }



}
