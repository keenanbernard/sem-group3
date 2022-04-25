package com.napier.sem;

import java.sql.*;
import java.util.*;

public class City {

    public String name;
    public String countrycode;
    public String district;
    public int population;
    public String continent;

    ReportingApp ra = new ReportingApp();

    public void Cities(){
        ArrayList<City> cities = getCities();

        System.out.println(cities.size());

        printCityReport(cities);
    }

    public void CitiesByContinent(){
        ArrayList<City> cContinent = getCitybyContinent();

        System.out.println(cContinent.size());

        printCityReport(cContinent);
    }

    public void CitiesByRegion(){
        ArrayList<City> cr = getCitybyRegion();

        System.out.println(cr.size());

        printCityReport(cr);
    }

    public void CitiesbyCountry(){
        ArrayList<City> cc = getCitiesbyCountry();

        System.out.println(cc.size());

        printCityReport(cc);
    }

    public void CitiesByDistrict(){
        ArrayList<City> dCities = getCitybyDistrict();

        System.out.println(dCities.size());

        printCityReport(dCities);
    }

    public void TopNCities(){
        ArrayList<City> topNCities = getTopNCities(5);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void TopNCitiesbyContinent(){
        ArrayList<City> topNCities = getTopNCitiesbyContinent(1);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void TopNCitiesbyRegion(){
        ArrayList<City> topNCities = getTopNCitiesbyRegion(1);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void TopNCitiesbyCountry(){
        ArrayList<City> topNCities = getTopNCitiesbyCountry(1);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void TopNCitiesbyDistrict(){
        ArrayList<City> topNCities = getTopNCitiesbyDistrict(1);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }


    //Get the population of cities in the world
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
            ArrayList<City> cities = new ArrayList<>();
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

    //Get the population of cities in each continent
    public ArrayList<City> getCitybyContinent() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    " SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy, country c "
                            + "WHERE cy.countrycode = c.code "
                            + "order by c.continent, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
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

    //Get the population of cities in each region
    public ArrayList<City> getCitybyRegion() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy, country c "
                            + "WHERE cy.countrycode = c.code "
                            + "order by c.region, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
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

    //Get the population of cities in each country
    public ArrayList<City> getCitiesbyCountry() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "order by cy.countrycode, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> cities = new ArrayList<>();
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

    //Get the population of cities in each district
    public ArrayList<City> getCitybyDistrict() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "order by cy.district, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> dCities = new ArrayList<>();
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

    //Get the top population of cities in the world
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
            ArrayList<City> tpNCities = new ArrayList<>();
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

    //Get the top population of cities in each continent
    public ArrayList<City> getTopNCitiesbyContinent(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by c.continent order by cy.population desc) as cityRank "
                            + "FROM city cy, country c where cy.countrycode = c.code) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> tpNCities = new ArrayList<>();
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

    //Get the top population of cities in each region
    public ArrayList<City> getTopNCitiesbyRegion(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by c.region order by cy.population desc) as cityRank "
                            + "FROM city cy, country c where cy.countrycode = c.code) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> tpNCities = new ArrayList<>();
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

    //Get the top population of cities in each country
    public ArrayList<City> getTopNCitiesbyCountry(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by cy.countrycode order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<City> tpNCities = new ArrayList<>();
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

    //Get the top population of cities in each district
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
            ArrayList<City> tpNCities = new ArrayList<>();
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

    public void printCityReport(ArrayList<City> cities) {

        if(cities == null)
        {
            System.out.println("No Data found.");
            return;
        }

        System.out.println(String.format("%-10s %-15s %-15s %-20s", "name", "countrycode", "district", "population"));

        for (City cty : cities) {
            if (cty == null) continue;

            String cty_string =
                    String.format("%-10s %-15s %-15s %-20s",
                            cty.name, cty.countrycode, cty.district, cty.population);
            System.out.println(cty_string);
        }
    }
}
