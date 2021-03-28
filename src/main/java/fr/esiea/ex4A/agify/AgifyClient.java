package fr.esiea.ex4A.agify;


import fr.esiea.ex4A.model.AgifyData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AgifyClient {
    @GET("/")
    Call <AgifyData> getData (@Query("name") String name, @Query("country_id") String country);

}
