package gov.iti.jets.persistence.dao;

import gov.iti.jets.persistence.dao.interfaces.countryDao;
import gov.iti.jets.persistence.entities.Country;
import gov.iti.jets.persistence.utils.DBConnecttion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class countryDaoImpl implements countryDao {

    @Override
    public ArrayList<Country> getCountries() {

        Connection con = DBConnecttion.getConnection();
        ArrayList<Country> countriesNames = new ArrayList<>();
        if (con == null)
            return null;

        String query = new String("select * from countries");

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("name"));
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
