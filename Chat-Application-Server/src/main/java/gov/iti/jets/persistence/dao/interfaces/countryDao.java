
package gov.iti.jets.persistence.dao.interfaces;


import java.util.ArrayList;

import gov.iti.jets.dto.CountryDto;

public interface countryDao {
    public  ArrayList<CountryDto> getCountries();
        public  ArrayList<CountryDto> selectCountries() ;

}
