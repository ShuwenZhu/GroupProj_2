package com.beaconfire.week9day4composite.Service;

import com.beaconfire.week9day4composite.Domain.HousingService.House;
import com.beaconfire.week9day4composite.Domain.UserHouse;
import com.beaconfire.week9day4composite.Domain.UserService.User;
import com.beaconfire.week9day4composite.Service.RemoteService.RemoteHousingService;
import com.beaconfire.week9day4composite.Service.RemoteService.RemoteUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompositeService {

    private RemoteUserService remoteUserService;
    private RemoteHousingService remoteHousingService;

    private CompositeService(RemoteUserService remoteUserService, RemoteHousingService remoteHousingService){
        this.remoteHousingService = remoteHousingService;
        this.remoteUserService = remoteUserService;
    }

    public List<UserHouse> getAllUserHouse(){
        List<User> userList = remoteUserService.getAllUsers().getBody();
        List<House> houseList = remoteHousingService.getAllHouses().getBody();
        remoteUserService.getByID(1,"token");
        List<UserHouse> userHouseList = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            userHouseList.add(
                    UserHouse.builder()
                            .user(userList.get(i))
                            .house(houseList.get(i))
                            .build()
            );
        }

        return userHouseList;
    }

}
