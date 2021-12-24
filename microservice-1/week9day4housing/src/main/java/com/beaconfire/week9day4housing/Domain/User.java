package com.beaconfire.week9day4housing.Domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable{
	private int id;

	private String username;

	private String email;
	
	@JsonIgnore
	private String password;

	@JsonIgnore
	private boolean admin;

	@JsonIgnore
	private int employeeId;

	@NoArgsConstructor
	@Getter
	@Setter
	public static class Web {
		private String username;

		private String email;

		private boolean admin;

		public Web(User user) {
			username = user.username;
			email = user.email;
			admin = user.admin;
		}
	}
}
