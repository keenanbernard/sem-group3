package com.napier.sem;

import java.sql.*;
import java.util.*;

public class CityRegion {
    public int id;
    public String name;
    public String countrycode;
    public String district;
    public int population;

    ReportingApp ra = new ReportingApp();

    public void cities(){
        ArrayList<City> cities = getCity();

        System.out.println(cities.size());

        printCities(cities);
    }

    public ArrayList<City> getCity() {
        district = "Buenos Aires";
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "WHERE cy.district = '"+district+"' "
                            + "order by cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                cities.add(cty);
            }
            return cities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void printCities(ArrayList<City> cities) {
        System.out.println(String.format("%-10s %-15s %-15s %-20s", "name", "countrycode", "district", "population"));

        for (City cty : cities) {
            String cty_string =
                    String.format("%-10s %-15s %-15s %-20s",
                            cty.name, cty.countrycode, cty.district, cty.population);
            System.out.println(cty_string);
        }
    }
}
