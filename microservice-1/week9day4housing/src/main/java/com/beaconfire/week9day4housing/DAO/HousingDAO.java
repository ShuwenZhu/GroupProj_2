package com.beaconfire.week9day4housing.DAO;

import com.beaconfire.week9day4housing.Domain.House;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HousingDAO {

    public List<House> getAllHouses(){
        List<House> houseList = new ArrayList<>();
        houseList.add(House.builder().address("50 Millstone Rd").city("East Windsor").state("NJ").zipCode("08536").build());
        houseList.add(House.builder().address("585 Standford Ct").city("Laguna Niguel").state("CA").zipCode("92612").build());
        houseList.add(House.builder().address("27685 Daisyfield Dr").city("Surprise").state("AZ").zipCode("25671").build());
        return houseList;
    }

}
