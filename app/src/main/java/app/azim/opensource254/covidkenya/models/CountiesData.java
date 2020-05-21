package app.azim.opensource254.covidkenya.models;

public class CountiesData {

    private String county;
    private String cases;
    private String deaths;
    private String tests;
    private String recoveries;


    public String getCounty(){ return  county;}

    public String getCases() {
        return cases;
    }

    public String getDeaths(){ return deaths;}

    public String getRecoveries(){
        return  recoveries;
    }

    public String getTests() {
        return tests;
    }

    public CountiesData(String county,String cases,String recoveries,String tests,String deaths) {
        this.county = county;
        this.cases = cases;
        this.deaths = deaths;
        this.tests = tests;
        this.recoveries = recoveries;
    }
}
