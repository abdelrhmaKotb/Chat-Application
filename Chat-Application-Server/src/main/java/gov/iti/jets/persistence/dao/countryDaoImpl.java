package gov.iti.jets.persistence.dao;


import gov.iti.jets.dto.CountryDto;
import gov.iti.jets.persistence.dao.interfaces.countryDao;
import gov.iti.jets.persistence.utils.DBConnecttion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class countryDaoImpl implements countryDao {

     @Override
    public ArrayList<CountryDto> getCountries() {

        Connection con = DBConnecttion.getConnection();
        ArrayList<CountryDto> countriesNames = new ArrayList<>();
        if (con == null)
            return null;

        String query = new String("select * from countries");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CountryDto country = new CountryDto(rs.getString("name"),rs.getInt("country_id"));
                countriesNames.add(country);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {

            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return countriesNames;

    }

 

}
