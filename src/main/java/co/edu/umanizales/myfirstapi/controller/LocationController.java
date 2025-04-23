package co.edu.umanizales.myfirstapi.controller;

import co.edu.umanizales.myfirstapi.model.Location;
import co.edu.umanizales.myfirstapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path = "/location")
public class LocationController {
    /*
        @GetMapping
        public Location getLocation(){
            Location location = new Location("1","Manizales");

            return location;
        }

        Método "get" para devolver una lista de Location
        @GetMapping
        public List<Location> getLocations() {
            // Aquí estamos creando una lista de ejemplo. Podrías obtener estos datos de una base de datos.
            return Arrays.asList(
                    new Location("001", "Location 1"),
                    new Location("002", "Location 2"),
                    new Location("003", "Location 3")
            );
        }

     */

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getLocations() {

        return locationService.getLocations();

    }

    @GetMapping(path = "/{code}")
    public Location getLocationByCode(@PathVariable String code){
        return locationService.getLocationByCode(code);
    }

    @GetMapping(path = "/states")
    public List<Location> getLocationsByStates(){
        return locationService.getStates();
    }

}