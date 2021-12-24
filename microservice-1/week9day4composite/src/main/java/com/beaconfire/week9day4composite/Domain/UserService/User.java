package com.beaconfire.week9day4composite.Domain.UserService;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    private String firstName;
    private String lastName;
}
