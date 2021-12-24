package com.beaconfire.week9day4user.DAO;

import com.beaconfire.week9day4user.Domain.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().username("AAA").password("aaa").email("AA@Email").employeeId(0).build());
        userList.add(User.builder().username("BBB").password("bbb").email("BB@Email").employeeId(1).build());
        userList.add(User.builder().username("CCC").password("ccc").email("CC@Email").employeeId(2).build());
        return userList;
    }
}
