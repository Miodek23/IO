/** Klasa reprezentująca aktora w scenariuszu (wykonawcę jednego z kroków).
 * @author Oliwia Masian
 * @version 1.0
 */


package pl.put.poznan.transformer.model;

public class Actor {
    /**
     * Przechowuje nazwe aktora
     */
    private String nazwa;
    /**
     * Tworzy nowy obiekt aktora
     * @param nazwa nazwa aktora
     */

    public Actor(String nazwa) {
        this.nazwa = nazwa;
    }

    public Actor() {
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
