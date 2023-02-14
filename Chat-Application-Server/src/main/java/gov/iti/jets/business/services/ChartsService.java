package gov.iti.jets.business.services;

import java.util.ArrayList;

import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.persistence.dao.GenderDaoImpl;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.dao.interfaces.UserDao;
import gov.iti.jets.persistence.dao.interfaces.countryDao;
import gov.iti.jets.persistence.entities.User;

public class ChartsService {

    public int getGenderCharts(int index) {
        GenderDaoImpl userGender = new GenderDaoImpl();
        int genderNumber = userGender.seletcGender(index);
        return genderNumber;
    }

    public ArrayList<CountryDto> getCountryChart() {

        countryDaoImpl countryData = new countryDaoImpl();
        return countryData.selectCountries();

    }
    
    public int getNumberOfUsers() {
        UserImpl users = new UserImpl();
        int number = users.getNumberOfUsers();
        return number;
    }


}
