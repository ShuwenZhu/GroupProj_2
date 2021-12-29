package com.beaconfire.week9day4user.Domain.responseObjects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SimpleMessage {
    private String title;
    private String userId;
    private String weDate;
}
