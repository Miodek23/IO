package pl.put.poznan.transformer.logic;

/** Klasa reprezentująca aktora w scenariuszu (wykonawcę jednego z kroków).
 * @author Oliwia Masian
 * @version 1.0
 */

public class Actor {
    /**
     * Przechowuje nazwe aktora
     */
    private String name;

    /**
     * Tworzy nowy obiekt aktora
     * @param nazwa nazwa aktora
     */

    public Actor(String nazwa) {
        this.name = nazwa;
    }

    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }
}
