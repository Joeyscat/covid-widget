package fun.oook.cw.config;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class ConfigModel {
    private int refreshIntervalInSeconds;
    private String countryName;
    private String countryCode;

    public ConfigModel() {
        refreshIntervalInSeconds = 300;
        countryName = "China";
        countryCode = "CN";
    }

    @Override
    public String toString() {
        return "ConfigModel{" +
                "refreshIntervalInSeconds=" + refreshIntervalInSeconds +
                ", countryName='" + countryName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    public int getRefreshIntervalInSeconds() {
        return refreshIntervalInSeconds;
    }

    public void setRefreshIntervalInSeconds(final int refreshIntervalInSeconds) {
        this.refreshIntervalInSeconds = refreshIntervalInSeconds;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }
}
