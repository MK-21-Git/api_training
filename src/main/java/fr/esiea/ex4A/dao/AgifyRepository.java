package fr.esiea.ex4A.dao;


import fr.esiea.ex4A.model.AgifyData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AgifyRepository {
   public AgifyData countainAgifyData (String name, String country) {
       AgifyData agifyData = new AgifyData(name, 0,0,country);
       List<AgifyData> agifyDataList = List.copyOf(CacheAgify.agifyBD.keySet());
       if (agifyDataList.contains(agifyData)) {
           return agifyDataList.get(agifyDataList.indexOf(agifyData));
       }
       return null;
   }

}
