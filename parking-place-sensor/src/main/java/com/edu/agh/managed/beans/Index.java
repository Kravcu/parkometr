package com.edu.agh.managed.beans;

import com.edu.agh.sei.ParkingPlaceDetailsDTO;
import com.edu.agh.sei.ParkingPlaceSensor;
import com.edu.agh.sei.ParkingPlaceSensorImplService;
import com.edu.agh.sei.ParkingPlaceState;
import com.google.common.collect.Maps;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ManagedBean
@SessionScoped
public class Index {

    private static final Logger logger = Logger.getLogger(Index.class.toString());

    private ParkingPlaceSensor parkingPlaceSensorService;
    private List<ParkingPlaceDetailsDTO> parkingPlaces;


    @PostConstruct
    private void init() {
        parkingPlaceSensorService = new ParkingPlaceSensorImplService().getParkingPlaceSensorPort();
        parkingPlaces = parkingPlaceSensorService.getAllParkingPlaces();
    }



    public String updateState(Long placeId, String state) {
        parkingPlaceSensorService.markParkingPlace(placeId, ParkingPlaceState.fromValue(state));
        return "index";
    }

    public Map<Long, List<ParkingPlaceDetailsDTO>> getParkingPlaces() {
        return parkingPlaces.stream().collect(Collectors.groupingBy(ParkingPlaceDetailsDTO::getSectionNumber));
    }

}
