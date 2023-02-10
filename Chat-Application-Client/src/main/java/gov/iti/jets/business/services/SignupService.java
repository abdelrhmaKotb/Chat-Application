package gov.iti.jets.business.services;

import gov.iti.jets.business.rmi.RMIConnection;
import gov.iti.jets.dto.UserDtoSignup;

import java.rmi.RemoteException;

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
    }