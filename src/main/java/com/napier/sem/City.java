package com.napier.sem;

import java.sql.*;
import java.util.*;

public class City {
    public int id;
    public String name;
    public String countrycode;
    public String district;
    public int population;

    ReportingApp ra = new ReportingApp();

    public void allCities(){
        ArrayList<City> cities = getCities();

        System.out.println(cities.size());

        printCities(cities);
    }
    public void citiesByRegion(){
        ArrayList<City> cr = getCitybyRegion();

        System.out.println(cr.size());

        printCities(cr);
    }

    public void citiesByDistrict(){
        ArrayList<City> dCities = getCitybyDistrict("Flevoland");

        System.out.println(dCities.size());

        printCities(dCities);
    }

    public ArrayList<City> getCities() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
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

    public ArrayList<City> getCitybyRegion() {
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
            ArrayList<City> cr = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                cr.add(cty);
            }
            return cr;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<City> getCitybyDistrict(String district) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "WHERE cy.district = '"+ district +"'"
                            + "order by cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> dCities = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                dCities.add(cty);
            }
            return dCities;

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
