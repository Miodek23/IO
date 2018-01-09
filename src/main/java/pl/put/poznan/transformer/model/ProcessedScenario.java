package pl.put.poznan.transformer.model;

public class ProcessedScenario {
    private String scenarioInPoints;
    private String scenarioWithTabs;
    private Integer numberOfPoints;
    private Integer numberOfKeywords;

    public ProcessedScenario(String scenarioInPoints, String scenarioWithTabs, Integer numberOfPoints, Integer numberOfKeywords) {
        this.scenarioInPoints = scenarioInPoints;
        this.scenarioWithTabs = scenarioWithTabs;
        this.numberOfPoints = numberOfPoints;
        this.numberOfKeywords = numberOfKeywords;
    }

    public ProcessedScenario() { }

    public String getScenarioInPoints() {

        return scenarioInPoints;
    }

    public void setScenarioInPoints(String scenarioInPoints) {
        this.scenarioInPoints = scenarioInPoints;
    }

    public String getScenarioWithTabs() {
        return scenarioWithTabs;
    }

    public void setScenarioWithTabs(String scenarioWithTabs) {
        this.scenarioWithTabs = scenarioWithTabs;
    }

    public Integer getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(Integer numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public Integer getNumberOfKeywords() {
        return numberOfKeywords;
    }

    public void setNumberOfKeywords(Integer numberOfKeywords) {
        this.numberOfKeywords = numberOfKeywords;
    }
}
