package pl.edu.agh.service;


import pl.edu.agh.dto.ParkingPlaceInfoDTO;
import pl.edu.agh.exceptions.UserNotFoundException;

import java.util.List;

public interface DashboardService {
    List<ParkingPlaceInfoDTO> getParkingPlacesInfo(int stateChangesAmount);
    void changePassword(String password);
    void changeOtherUserPassword(String login, String password) throws UserNotFoundException;
}
