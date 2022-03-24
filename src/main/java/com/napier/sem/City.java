package com.napier.sem;

import java.sql.*;
import java.util.*;

public class City {
    public int id;
    public String name;
    public String countrycode;
    public String district;
    public int population;
    public String continent;


    ReportingApp ra = new ReportingApp();

    public void allCities(){
        ArrayList<City> cities = getCities();

        System.out.println(cities.size());

        printCityReport(cities);
    }
    public void citiesByRegion(){
        ArrayList<City> cr = getCitybyRegion("Buenos Aires");

        System.out.println(cr.size());

        printCityReport(cr);
    }

    public void citiesByDistrict(){
        ArrayList<City> dCities = getCitybyDistrict("Flevoland");

        System.out.println(dCities.size());

        printCityReport(dCities);
    }

    public void TopNCities(){
        ArrayList<City> topNCities = getTopNCities(5);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void TopNCitiesbyDistrict(){
        ArrayList<City> topNCities = getTopNCitiesbyDistrict(1);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void CitiesbyContinent(){
        ArrayList<City> cContinents = getCitiesByContinent("Asia");

        System.out.println(cContinents.size());

        printCityReport(cContinents);
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
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<City> getCitybyRegion(String region) {
        //district = "Buenos Aires";
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "WHERE cy.district = '"+region+"' "
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
            System.out.println("Failed to get table details");
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
                            + "WHERE cy.district != ' ' "
                            + "order by cy.district, cy.population desc";
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
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<City> getTopNCities(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> tpNCities = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                tpNCities.add(cty);
            }
            return tpNCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<City> getTopNCitiesbyDistrict(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by cy.district order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> tpNCities = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                tpNCities.add(cty);
            }
            return tpNCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<City> getCitiesByContinent(String continent) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population, cy.continent "
                            + "FROM city cy "
                            + "WHERE cy.district != ' ' "
                            + "order by cy.district, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cContinents = new ArrayList<City>();
            while (rset.next()) {
                City cty = new City();
                cty.name = rset.getString("name");
                cty.countrycode = rset.getString("countrycode");
                cty.district = rset.getString("district");
                cty.population = rset.getInt("population");
                cty.continent = rset.getString("continent");
                cContinents.add(cty);
            }
            return cContinents;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public void printCityReport(ArrayList<City> cities) {
        System.out.println(String.format("%-10s %-15s %-15s %-20s", "name", "countrycode", "district", "population"));

        for (City cty : cities) {
            String cty_string =
                    String.format("%-10s %-15s %-15s %-20s",
                            cty.name, cty.countrycode, cty.district, cty.population);
            System.out.println(cty_string);
        }
    }
}
