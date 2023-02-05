
package gov.iti.jets.persistence.dao.interfaces;

import gov.iti.jets.business.dto.CountryDto;
import gov.iti.jets.persistence.entities.Country;
import java.util.ArrayList;

public interface countryDao {
        ArrayList<Country> getCountries();
        public  ArrayList<CountryDto> selectCountries() ;

}
