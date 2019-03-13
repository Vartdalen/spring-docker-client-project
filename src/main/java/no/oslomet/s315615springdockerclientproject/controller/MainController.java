package no.oslomet.s315615springdockerclientproject.controller;

import no.oslomet.s315615springdockerclientproject.model.Building;
import no.oslomet.s315615springdockerclientproject.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BuildingService buildingService;
    @GetMapping("/")
    public String home(Model model ){
        Building building  = new Building();
        List<Building> buildingList = buildingService.getAllBuildings();
        model.addAttribute("building", building);
        model.addAttribute("buildings", buildingList);
        return "index";
    }

    @PostMapping("/saveBuilding")
    public String saveBuilding(@ModelAttribute("building") Building newBuilding){
        buildingService.saveBuilding(newBuilding);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateBuilding(@PathVariable long id, Model model ){
        model.addAttribute("building"
                , buildingService.getBuildingById(id));
        model.addAttribute("buildings", buildingService.getAllBuildings());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuilding(@PathVariable long id ){
        buildingService.deleteBuildingById(id);
        return "redirect:/";
    }


}
