package com.beaconfire.week9day4composite.Domain;

import com.beaconfire.week9day4composite.Domain.HousingService.House;
import com.beaconfire.week9day4composite.Domain.UserService.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UserHouse {
    private User user;
    private House house;
}
