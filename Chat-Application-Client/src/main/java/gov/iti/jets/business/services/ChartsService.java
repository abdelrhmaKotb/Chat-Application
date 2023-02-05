package gov.iti.jets.business.services;

import java.util.ArrayList;

import gov.iti.jets.business.dto.CountryDto;
import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.persistence.dao.GenderDaoImpl;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import gov.iti.jets.persistence.dao.interfaces.countryDao;
import gov.iti.jets.persistence.entities.User;

public class ChartsService {

    public int getGenderCharts(String str) {
        GenderDaoImpl userGender = new GenderDaoImpl();
        int genderNumber = userGender.seletcGender(str);
        return genderNumber;
    }

    public ArrayList<CountryDto> getCountryChart() {

        countryDaoImpl countryData = new countryDaoImpl();
        return countryData.selectCountries();

    }

}
