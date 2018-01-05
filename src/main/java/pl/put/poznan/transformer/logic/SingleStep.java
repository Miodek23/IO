package pl.put.poznan.transformer.logic;

/** Klasa reprezentująca pojedynczy krok w scenariuszu (liść).
 * <p>
 *  Pojedynczy krok składa się wyłącznie z tekstu (nie ma podpuntów).
 * <p>
 * Pełni rolę <b> Liścia </b> we wzorcu <b> Kompozyt </b>.
 * @author Oliwia Masian
 * @version 1.0
 */

public class SingleStep implements Step {

    /**
     * Tekst (treść) kroku
     */
    private String text;
    /**
     * Rodzic (nadscenariusz)
     */
    private Scenario parent;

    /**
     * Tworzy pojedynczy krok z określoną treścią i rodzicem
     * @param text tekst (treść) kroku
     * @param parent rodzic (nadscenariusz)
     */
    public SingleStep(String text, Scenario parent){
        setText(text);
        this.parent = parent;
    }

    /**
     * Przyjmuje wizytatora, wywołując dedykowaną dla siebie metodę na obiekcie
     * odwiedzającym
     * @param visitor odwiedzający (wizytator)
     */
    @Override
    public void accept(StepVisitor visitor) {
        visitor.visitSingleStep(this);
    }

    /**
     * Zwraca tekst kroku
     * @return tekst kroku (treść kroku)
     */
    @Override
    public String getText(){
        return this.text;
    }

    /**
     * Ustawia tekst kroku
     * @param text tekst kroku (treść kroku)
     */
    @Override
    public void setText(String text) {
        this.text = text;
    }
    /**
     * Zwraca referencję na rodzica
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
     * W przypadku tej klasy (będącej liściem), metoda ta nie ma sensu,
     * więc jest pusta.
     * <p>
     * this is leaf node so this method is not applicable to this class
     * @param step krok do dodania
     */
    @Override
    public void add(Step step) {
        //this is leaf node so this method is not applicable to this class.
    }
    /**
     * W przypadku tej klasy (będącej liściem), metoda ta nie ma sensu,
     * więc jest pusta.
     * <p>
     * this is leaf node so this method is not applicable to this class
     * @param step krok do usunięcia
     */
    @Override
    public void remove(Step step) {
        //this is leaf node so this method is not applicable to this class.
    }
    /**
     * W przypadku tej klasy (będącej liściem), metoda ta nie ma sensu,
     * więc jest pusta.
     * <p>
     * this is leaf node so this method is not applicable to this class
     * @param index indeks kroku (dziecka) w liście kroków
     * @return referencja na dziecko
     */
    @Override
    public Step getChild(int index) {
        //this is leaf node so this method is not applicable to this class.
        return null;
    }

    /**
     * W przypadku tej klasy (będącej liściem), metoda ta nie ma sensu,
     * więc jest pusta.
     * <p>
     * this is leaf node so this method is not applicable to this class
     * @param step dziecko, którego indeks w liście kroków chcemy otrzymać
     * @return indeks dziecka
     */

    @Override
    public int getIndexOfChild(Step step) {
        //this is leaf node so this method is not applicable to this class.
        return 0;
    }
}
