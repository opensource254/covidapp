package app.azim.opensource254.covidkenya.models;

public class SituationModel {
    public int id;
    public String cases;
    public String todayCases;
    public String deaths;
    public String todayDeaths;
    public String recovered;
    public String active;
    public String critical;
    public String casesPerOneMillion;
    public String deathsPerOneMillion;
    public String tests;
    public String testsPerOneMillion;


    public SituationModel(int id, String cases, String
            todayCases, String deaths, String todayDeaths, String recovered,
                          String active, String critical, String casesPerOneMillion,
                          String deathsPerOneMillion, String tests, String testsPerOneMillion) {

        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.tests = tests;
        this.testsPerOneMillion = testsPerOneMillion;


    }
}
