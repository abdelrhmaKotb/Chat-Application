package gov.iti.jets.persistence.dao;

import java.util.ArrayList;

import gov.iti.jets.dto.CountryDto;



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
