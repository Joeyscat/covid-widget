package fun.oook.cw.datafetch.model;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class GlobalData {
    private long cases;
    private long recovered;
    private long deaths;

    @Override
    public String toString() {
        return "GlobalData{" +
                "cases=" + cases +
                ", recovered=" + recovered +
                ", deaths=" + deaths +
                '}';
    }

    public long getCases() {
        return cases;
    }

    public void setCases(final long cases) {
        this.cases = cases;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(final long recovered) {
        this.recovered = recovered;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(final long deaths) {
        this.deaths = deaths;
    }
}
