package pl.edu.agh.service.impl;

import org.jboss.annotation.security.SecurityDomain;
import pl.edu.agh.dto.ParkingPlaceInfoDTO;
import pl.edu.agh.exceptions.UserNotFoundException;
import pl.edu.agh.model.ParkingPlace;
import pl.edu.agh.model.User;
import pl.edu.agh.service.DashboardService;
import pl.edu.agh.repository.impl.ParkingPlaceRepositoryImpl;
import pl.edu.agh.repository.impl.UserRepositoryImpl;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.security.*;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@SecurityDomain("projectDomain")
@Stateless(mappedName = "DashboardService")
@Remote(DashboardService.class)
public class DashboardServiceImpl implements DashboardService {

    @Resource
    private SessionContext sctx;

    @Inject
    private ParkingPlaceRepositoryImpl parkingPlaceRepositoryImpl;

    @Inject
    private UserRepositoryImpl userRepositoryImpl;

    private static final Logger logger = Logger.getLogger(DashboardServiceImpl.class.toString());

    @Override
    @RolesAllowed({"ADMIN", "WORKER"})
    public List<ParkingPlaceInfoDTO> getParkingPlacesInfo(int stateChangesAmount) {


        List<ParkingPlace> parkingPlaces;
        if (sctx.isCallerInRole("ADMIN")) {
            parkingPlaces = parkingPlaceRepositoryImpl.getAllParkingPlaces();
        } else { // MUST BE WORKER
            User userByLogin = getCurrentUser();
            parkingPlaces = parkingPlaceRepositoryImpl.getParkingPlacesBySectionNumber(userByLogin.getSectionNumber());
        }

        return parkingPlaces.stream()
                .map(pp -> ParkingPlaceInfoDTO.fromEntity(pp, stateChangesAmount))
                .collect(Collectors.toList());
    }

    @Override
    @RolesAllowed({"ADMIN", "WORKER"})
    public void changePassword(String password) {
        String name = sctx.getCallerPrincipal().getName();
        String encodedPassword = encodePassword(password);
        userRepositoryImpl.updatePassword(name, encodedPassword);
    }


    @Override
    @RolesAllowed({"ADMIN"})
    @Transactional
    public void changeOtherUserPassword(String login, String password) throws UserNotFoundException{
        try {
            logger.info("Try to change password for user " + login);
            User currentUser =  userRepositoryImpl.getUserByLogin(login);
            currentUser.setPassword(encodePassword(password));
        } catch (Exception e) {
            throw new UserNotFoundException();
        }

    }

    private User getCurrentUser() {
        String principalName = sctx.getCallerPrincipal().getName();
        return userRepositoryImpl.getUserByLogin(principalName);
    }


    private String encodePassword(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return new String(Base64.getEncoder().encode(md5.digest(password.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot happen");
        }

    }
}
