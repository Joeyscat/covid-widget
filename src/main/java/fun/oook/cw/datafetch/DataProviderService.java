package fun.oook.cw.datafetch;

import fun.oook.cw.datafetch.model.CountryData;
import fun.oook.cw.datafetch.model.CovidDataModel;
import fun.oook.cw.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @author ZhouYu
 * @since 0.0.1
 */
public class DataProviderService {

    public CovidDataModel getDate(final String countryName) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://coronavirus-19-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final CovidApi covidApi = retrofit.create(CovidApi.class);

        CompletableFuture<CountryData> countryCallback = new CompletableFuture<>();
        covidApi.getCountryData(countryName)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(final Call<CountryData> call, final Response<CountryData> response) {
                        countryCallback.complete(response.body());
                    }

                    @Override
                    public void onFailure(final Call<CountryData> call, final Throwable t) {
                        countryCallback.completeExceptionally(t);
                    }
                });

        CompletableFuture<GlobalData> globalCallback = new CompletableFuture<>();
        covidApi.getGlobalData()
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(final Call<GlobalData> call, final Response<GlobalData> response) {
                        globalCallback.complete(response.body());
                    }

                    @Override
                    public void onFailure(final Call<GlobalData> call, final Throwable t) {
                        globalCallback.completeExceptionally(t);
                    }
                });

        final CountryData countryData = countryCallback.join();
        final GlobalData globalData = globalCallback.join();

        return new CovidDataModel(globalData, countryData);
    }
}
