package gov.iti.jets.business.services;

import gov.iti.jets.business.dto.UserDto;
import gov.iti.jets.business.mapper.UserMapper;
import gov.iti.jets.persistence.dao.GenderDaoImpl;
import gov.iti.jets.persistence.dao.UserImpl;
import gov.iti.jets.persistence.entities.User;

public class ChartsService {


    public int getCharts(String str)
    {
        GenderDaoImpl userGender = new GenderDaoImpl();
        int  genderNumber = userGender.seletcGender(str);

    

        return genderNumber;
    }
    
}
