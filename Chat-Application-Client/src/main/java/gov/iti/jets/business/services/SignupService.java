package gov.iti.jets.business.services;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.UserDtoSignup;

import java.rmi.RemoteException;
import java.util.ArrayList;

import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.dto.UserDto;
import gov.iti.jets.interfaces.Server;

public class SignupService {

    public UserDtoSignup signupUser(UserDtoSignup signupDto) {
        Server ser = RMIConnection.getServerServive();

        UserDtoSignup user = null;
        try {
            user = ser.Signup(signupDto);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return user;
    }

    public ArrayList<CountryDto> getCountries() {
        Server ser = RMIConnection.getServerServive();

        ArrayList<CountryDto>  countries = null;
        try {
            countries = ser.getCountriesNames();
            System.out.println(countries.size());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return countries;
        
    }
}