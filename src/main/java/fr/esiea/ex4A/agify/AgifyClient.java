package fr.esiea.ex4A.agify;


import fr.esiea.ex4A.model.AgifyUser;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AgifyClient {
    @GET("/")
    Call <AgifyUser> getData (@Query("name") String name, @Query("country_id") String country);

}
