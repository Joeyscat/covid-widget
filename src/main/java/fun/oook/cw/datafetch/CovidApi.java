package fun.oook.cw.datafetch;

import fun.oook.cw.datafetch.model.CountryData;
import fun.oook.cw.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public interface CovidApi {

    @GET("http://coronavirus-19-api.herokuapp.com/all")
    Call<GlobalData> getGlobalData();


    @GET("http://coronavirus-19-api.herokuapp.com/countries/{countryName}")
    Call<CountryData> getCountryData(@Path(value = "countryName") final String countryName);
}
