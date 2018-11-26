//Name: Michael Felix
//Date: November 17, 2018
//EGR327-A - Software Construction
//Email: Michael.Felix@calbaptist.edu
//Project 2


package com.example.projecttwo;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MyTask {

    RestTemplate restTemplate = new RestTemplate();
    private int vehicleID;

    @Scheduled(cron = "0 0 * * * *")
    public void latestVehiclesReport() {
        String getLatestUrl = "http://localhost:8080/getLatestVehicles";
        List<Vehicle> recentVehicles = restTemplate.getForObject(getLatestUrl, List.class);
        System.out.println("***** LATEST VEHICLES *****");
        for (int i = 0; i < recentVehicles.size(); i++) {
            System.out.println(recentVehicles.get(i));
        }
    }
    @Scheduled(cron = "*/5 * * * * *")
    public void addNewVehicle() {
        String makeModel = RandomStringUtils.randomAlphabetic(10);
        int year = RandomUtils.nextInt(1986, 2016);
        double retailPrice = RandomUtils.nextInt(15000, 450000);
        Vehicle vehicle = new Vehicle(0, makeModel, year, retailPrice);
        String url = "http://localhost:8080/addVehicleDB";
        restTemplate.postForObject(url, vehicle, Vehicle.class);
    }
    @Scheduled(cron = "*/10 * * * * *")
    public void deleteRandomVehicle() {
        int deleteID = RandomUtils.nextInt(0, 100);
        String getUrl = "http://localhost:8080/getVehicleDB/" + deleteID;
        String deleteUrl = "http://localhost:8080/deleteVehicleDB/" + deleteID;
        Vehicle v = restTemplate.getForObject(getUrl, Vehicle.class);
        if (v != null) {
            restTemplate.delete(deleteUrl);
            System.out.println("Successfully deleted: "+v);
        }
    }
    @Scheduled(cron = "*/10 * * * * *")
    public void updateRandomVehicle() {
        int updateID = RandomUtils.nextInt(0, 100);
        Vehicle newVehicle = new Vehicle(updateID, "111111", 1111, 111111);
        String getUrl = "http://localhost:8080/getVehicleDB/" + updateID;
        String updateUrl = "http://localhost:8080/updateVehicleDB";
        Vehicle v = restTemplate.getForObject(getUrl, Vehicle.class);
        if (v != null) {
            restTemplate.put(updateUrl, newVehicle, Vehicle.class);
            System.out.println("Successfully updated: " + v);
        }
    }
}
