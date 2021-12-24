package com.beaconfire.week9day4housing.Service;

import com.beaconfire.week9day4housing.DAO.HousingDAO;
import com.beaconfire.week9day4housing.Domain.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HousingService {
    private HousingDAO housingDAO;

    @Autowired
    public void setHousingService(HousingDAO housingDAO) {
        this.housingDAO = housingDAO;
    }

    public List<House> getAllHouses(){
        return housingDAO.getAllHouses();
    }
}
