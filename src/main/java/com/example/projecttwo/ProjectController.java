//Name: Michael Felix
//Date: November 17, 2018
//EGR327-A - Software Construction
//Email: Michael.Felix@calbaptist.edu
//Project 2

package com.example.projecttwo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private VehicleDao vehicleDao;
    @RequestMapping(value = "/addVehicleDB", method = RequestMethod.POST)
    public Vehicle addVehicleDB(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDao.create(newVehicle);
        return newVehicle;
    }
    @RequestMapping(value = "/getVehicleDB/{id}", method = RequestMethod.GET)
    public Vehicle getVehicleDB(@PathVariable("id") int id) throws IOException {
        return vehicleDao.getById(id);
    }
    @RequestMapping(value = "/updateVehicleDB", method = RequestMethod.PUT)
    public Vehicle updateVehicleDB(@RequestBody Vehicle newVehicle) throws IOException {
        System.out.println("Updating vehicle: "+newVehicle);
        vehicleDao.update(newVehicle);
        return newVehicle;
    }
    @RequestMapping(value = "/deleteVehicleDB/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicleDB(@PathVariable("id") int id) throws IOException {
        vehicleDao.delete(id);
        return new ResponseEntity<>("Deleted: "+id, HttpStatus.OK);
    }
    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        return vehicleDao.getVehicleList();
    }
}
