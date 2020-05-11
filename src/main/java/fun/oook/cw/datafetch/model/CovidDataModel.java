package fun.oook.cw.datafetch.model;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class CovidDataModel {
    private final GlobalData globalData;
    private final CountryData countryData;

    public CovidDataModel(final GlobalData globalData, final CountryData countryData) {
        this.globalData = globalData;
        this.countryData = countryData;
    }

    public GlobalData getGlobalData() {
        return globalData;
    }

    public CountryData getCountryData() {
        return countryData;
    }

    @Override
    public String toString() {
        return "CovidDataModel{" +
                "globalData=" + globalData +
                ", countryData=" + countryData +
                '}';
    }
}
