package co.edu.umanizales.myfirstapi.service;

import co.edu.umanizales.myfirstapi.model.Location;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Getter

public class LocationService {

    private List<Location> locations;

    @Value("${locations_filename}")
    private String locationsFilename;

    @PostConstruct
    public void readLocationsFromCSV() throws IOException, URISyntaxException {
        locations = new ArrayList<>();

        Path pathFile = Paths.get(ClassLoader.getSystemResource(locationsFilename).toURI());

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile.toString()))) {
            String line;
            boolean firstLine = true;

            // Leer todas las filas del CSV
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] tokens = line.split(",");
                String departmentCode = tokens[0].trim(); // Código del departamento
                String departmentName = tokens[1].trim(); // Nombre del departamento
                String cityCode = tokens[2].trim(); // Código del municipio
                String cityName = tokens[3].trim(); // Nombre del municipio

                // Si el código de la ciudad termina en "001", es una capital
                if (cityCode.endsWith("001")) {
                    locations.add(new Location(cityCode, cityName)); // Agregar la capital
                }

                // Siempre agregamos el departamento, independientemente de la capital
                locations.add(new Location(departmentCode, departmentName)); // Agregar el departamento
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Location getLocationByCode(String code) {
        for (Location location : locations) {
            if (location.getCode().equals(code)) {
                return location;
            }
        }
        return null;
    }

    public List<Location> getStates() {
        List<Location> states = new ArrayList<>();
        for (Location location : locations) {
            if (location.getCode().length() == 2) {
                states.add(location);
            }
        }
        return states;
    }

    /*
    HAGA UN MÉT0DO GET QUE DEVUELVA
    LOS 32 DEPARTAMENTOS Y SUS RESPECTIVAS CAPITALES
    EJ:
    05 Antioquia
    05001 Medellín

    TipoDeAcceso TipoDeRetorno NombreMét0do(parámtero) {
    }

    public List<Location> nombreMét0do() {
        List<Location> results = new ArrayList<>()
        for (Location l : states) {
            if (l.getCode().length() == 2) {
                results.add(l);
            }
            if (l.getCode().startsWith(departmentCode) && l.getCode.endsWith("001")) {
                results.add(l);
            }
        }
        return results
    }
     */
    public List<Location> getDepartmentsWithCapitals() {
        Set<Location> results = new HashSet<>();

        for (Location location : locations) {
            // Si el código tiene solo 2 caracteres => es un departamento
            if (location.getCode().length() == 2) {
                results.add(location); // departamento
                // Buscar su capital (termina en "001" y comienza con el código del departamento)
                for (Location subLocation : locations) {
                    if (subLocation.getCode().startsWith(location.getCode())
                            && subLocation.getCode().endsWith("001")
                            && !subLocation.getCode().equals(location.getCode())) {
                        results.add(subLocation); // capital
                        break;
                    }
                }
            }
        }
        return new ArrayList<>(results);
    }
}

