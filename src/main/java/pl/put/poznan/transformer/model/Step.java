package pl.put.poznan.transformer.model;

import pl.put.poznan.transformer.services.visitors.StepVisitor;

/** Interfejs reprezentujący krok w scenariuszu
 * <p>
 * Implementują go klasy:
 * <ul>
 *     <li> {@link SingleStep} -  pojedynczy krok w scenariuszu (sam tekst) </li>
 *     <li> {@link Scenario }- podscenariusz (tekst + zagnieżdżone kroki) </li>
 *
 * </ul>
 * Realizuje wzorzec <b> Kompozyt </b>
 * @author Oliwia Masian
 * @version 1.0
 */

public interface Step {
    /**
     * Metoda realizująca przyjęcie odwiedzającego
     * @param visitor odwiedzający
     */
    public void accept(StepVisitor visitor);

    /**
     * Dodaje kolejny krok w scenariuszu (dziecko- pojedynczy krok bądź podscenariusz)
     * @param step krok do dodania
     */
    public void add(Step step);

    /**
     * Usuwa krok w scenariuszu (dziecko- pojedynczy krok bądź podscenariusz) z listy dzieci
     * @param step krok do usunięcia
     */
    public void remove(Step step);

    /**
     * Zwraca referencję na dziecko (krok w scenariuszu) o podanym indeksie
     * @param index indeks kroku (dziecka) w liście kroków
     * @return  referencja na dziecko
     */
    public Step getChild(int index);

    /**
     * Zwraca referencję na rodzica (nadscenariusz) lub null,
     * jeśli obiekt nie ma rodzica (jest najstarszym nadscenariuszem)
     * @return referencja na rodzica
     */
    public Scenario getParent();

    /**
     * Ustawia rodzica danego kroku
     * @param scenario rodzic
     * (rodzic może być tylko scenariuszem, pojedyncze kroki nigdy nie mają dzieci)
     */
    public void setParent(Scenario scenario);

    /**
     * Zwraca indeks dziecka o podanej referencji
     * @param step dziecko, którego indeks w liście kroków chcemy otrzymać
     * @return indeks dziecka
     */
    public int getIndexOfChild(Step step);

    /**
     * Zwraca tekst kroku (w przypadku najstarszego nadscenariusza tekst jest zawsze pusty,
     * dzieci nadscenariusza będące zarówno pojedynczymi krokami bądź podscenariuszami
     * mogą mieć niepusty tekst)
     * @return tekst kroku (treść kroku)
     */
    public String getText();

    /**
     * Ustawia tekst kroku (w przypadku najstarszego nadscenariusza tekst jest zawsze pusty,
     * dzieci nadscenariusza będące zarówno pojedynczymi krokami bądź podscenariuszami
     * mogą mieć niepusty tekst)
     * @param text tekst kroku (treść kroku)
     */
    public void setText(String text);

}

