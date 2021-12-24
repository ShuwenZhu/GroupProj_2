package com.beaconfire.week9day4composite.Domain.HousingService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class House {
    private String address;
    private String city;
    private String state;
    private String zipCode;
}
