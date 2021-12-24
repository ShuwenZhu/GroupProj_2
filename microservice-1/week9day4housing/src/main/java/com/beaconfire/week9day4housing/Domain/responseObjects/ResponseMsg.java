package com.beaconfire.week9day4housing.Domain.responseObjects;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseMsg implements Serializable{
	private String msg;

}
