package no.oslomet.s315615springdockerclientproject.service;

import no.oslomet.s315615springdockerclientproject.model.Building;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingService {

    String BASE_URL = "http://localhost:9090/buildings";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Building> getAllBuildings(){
       return  Arrays.stream(
                restTemplate.getForObject(BASE_URL, Building[].class)
        ).collect(Collectors.toList());
    }

    public Building getBuildingById(long id){
       Building building = restTemplate.getForObject(BASE_URL+"/"+id, Building.class);
       return building;
    }

    public Building saveBuilding(Building newBuilding){
        return restTemplate.postForObject(BASE_URL, newBuilding, Building.class);
    }

    public void updateBuilding(long id, Building updatedBuilding){
          restTemplate.put(BASE_URL+"/"+id, updatedBuilding);
    }

    public void deleteBuildingById(long id){
        restTemplate.delete(BASE_URL+"/"+id);
    }
}
