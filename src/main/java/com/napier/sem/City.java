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
    private String location;
    private int delay;

    //The following 5 public voids assigns the returned arraylists from the methods and send them to the print method for display.
    public void Cities(){
        ArrayList<City> cities = getCities(location, delay);

        System.out.println(cities.size());

        printCityReport(cities);
    }

    public void citiesByContinent(){
        ArrayList<City> cContinent = getCitybyContinent(location, delay);

        System.out.println(cContinent.size());

        printCityReport(cContinent);
    }

    public void citiesByRegion(){
        ArrayList<City> cr = getCitybyRegion(location, delay);

        System.out.println(cr.size());

        printCityReport(cr);
    }

    public void citiesbyCountry(){
        ArrayList<City> cc = getCitiesbyCountry(location, delay);

        System.out.println(cc.size());

        printCityReport(cc);
    }

    public void citiesByDistrict(){
        ArrayList<City> dCities = getCitybyDistrict(location, delay);

        System.out.println(dCities.size());

        printCityReport(dCities);
    }

    //The following 5 public voids pushes a variable to the method, assigns the returned arraylists from the methods and send them to the print method for display.
    public void topNCities(){
        ArrayList<City> topNCities = getTopNCities(2, location, delay);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void topNCitiesbyContinent(){
        ArrayList<City> topNCities = getTopNCitiesbyContinent(3, location, delay);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void topNCitiesbyRegion(){
        ArrayList<City> topNCities = getTopNCitiesbyRegion(4, location, delay);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void topNCitiesbyCountry(){
        ArrayList<City> topNCities = getTopNCitiesbyCountry(5, location, delay);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }

    public void topNCitiesbyDistrict(){
        ArrayList<City> topNCities = getTopNCitiesbyDistrict(1, location, delay);

        System.out.println(topNCities.size());

        printCityReport(topNCities);
    }


    //Get the population of cities in the world
    public ArrayList<City> getCities(String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "order by cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City array, if valid
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the population of cities in each continent
    public ArrayList<City> getCitybyContinent(String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
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
            // Return new City array, if valid
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the population of cities in each region
    public ArrayList<City> getCitybyRegion(String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
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
            // Return new City array, if valid
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the population of cities in each country
    public ArrayList<City> getCitiesbyCountry(String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "order by cy.countrycode, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City array, if valid
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the population of cities in each district
    public ArrayList<City> getCitybyDistrict(String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, cy.countrycode, cy.district, cy.population "
                            + "FROM city cy "
                            + "order by cy.district, cy.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the top population of cities in the world
    public ArrayList<City> getTopNCities(int rank, String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the top population of cities in each continent
    public ArrayList<City> getTopNCitiesbyContinent(int rank, String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by c.continent order by cy.population desc) as cityRank "
                            + "FROM city cy, country c where cy.countrycode = c.code) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the top population of cities in each region
    public ArrayList<City> getTopNCitiesbyRegion(int rank, String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by c.region order by cy.population desc) as cityRank "
                            + "FROM city cy, country c where cy.countrycode = c.code) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the top population of cities in each country
    public ArrayList<City> getTopNCitiesbyCountry(int rank, String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by cy.countrycode order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //Get the top population of cities in each district
    public ArrayList<City> getTopNCitiesbyDistrict(int rank, String location, int delay) {
        try {
            //runs the db connection method from main. Also sends out the location (db host) and delay variables
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by cy.district order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City if valid.
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

        } catch (Exception e) { //runs an error if the try fails
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    //pulls the arraylist from the method that's being called and prints it in a specific structure
    public void printCityReport(ArrayList<City> cities) {

        if(cities == null) //if there's an error and nothing in the arraylist is being pulled
        {
            System.out.println("No Data found.");
            return;
        }

        //prints the table headers and their respective cell lengths
        System.out.println(String.format("%-10s %-15s %-15s %-20s", "name", "countrycode", "district", "population"));

        for (City cty : cities) {
            if (cty == null) continue;

            //prints all the data from the array in a specific order and variable as saved within the array. Includes their respective cell lengths
            String cty_string =
                    String.format("%-10s %-15s %-15s %-20s",
                            cty.name, cty.countrycode, cty.district, cty.population);
            System.out.println(cty_string);
        }
    }

    //returns the location variable to the city class to assign it to the private variable
    public String getLocation() {
        return location;
    }

    //gets the location variable from main and assigns it locally
    public void setLocation(String location){
        this.location=location;
    }

    //returns the delay variable to the city class to assign it to the private variable
    public int getDelay() {
        return delay;
    }

    //gets the delay variable from main and assigns it locally
    public void setDelay(int delay){
        this.delay=delay;
    }
}
