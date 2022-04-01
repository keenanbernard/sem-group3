package com.napier.sem;

import java.sql.*;
import java.util.*;

public class Capital {
    public int id;
    public String name;
    public String country;
    private int continent;
    public int population;

    ReportingApp ra = new ReportingApp();

    public void allCapitalCities(){
        ArrayList<Capital> capitalCities = getCapitalCity();

        System.out.println(capitalCities.size());

        printCapitalCities(capitalCities);
    }

    public void capitalCitiesbyContinent(){
        ArrayList<Capital> capitalCities = capitalCitybyContinent();

        System.out.println(capitalCities.size());

        printCapitalCities(capitalCities);
    }


    public ArrayList<Capital> getCapitalCity() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, c.name as country, cy.population "
                            + "FROM city cy, country c WHERE cy.id = c.capital "
                            + "order by cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> capitalCities = new ArrayList<>();
            while (rset.next()) {
                Capital ccty = new Capital();
                ccty.name = rset.getString("name");
                ccty.country = rset.getString("country");
                ccty.population = rset.getInt("population");
                capitalCities.add(ccty);
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }


    public ArrayList<Capital> capitalCitybyContinent() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, c.name as country, cy.population, c.continent "
                            + "FROM city cy, country c WHERE cy.id = c.capital "
                            + "order by c.continent, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Capital> capitalCities = new ArrayList<>();
            while (rset.next()) {
                Capital ccty = new Capital();
                ccty.name = rset.getString("name");
                ccty.country = rset.getString("country");
                ccty.population = rset.getInt("population");
                capitalCities.add(ccty);
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void printCapitalCities(ArrayList<Capital> capitalCities) {
        System.out.println(String.format("%-10s %-15s %-20s", "name", "country", "population"));

        for (Capital ccty : capitalCities) {
            String cty_string =
                    String.format("%-10s %-15s %-20s",
                            ccty.name, ccty.country, ccty.population);
            System.out.println(cty_string);
        }
    }
}
