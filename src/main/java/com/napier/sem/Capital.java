package com.napier.sem;

import java.sql.*;
import java.util.*;

public class Capital {

    public String name;
    public String country;
    public String continent;
    public int population;
    public String region;

    ReportingApp ra = new ReportingApp();
    private String location;
    private int delay;

    public void CapitalCities(){
        ArrayList<Capital> capitalCities = getCapitalCity(location, delay);

        System.out.println(capitalCities.size());

        printCapitalCities(capitalCities);
    }

    public void capitalCitiesbyContinent(){
        ArrayList<Capital> capitalCities = capitalCitybyContinent(location, delay);

        System.out.println(capitalCities.size());

        printCapitalCities(capitalCities);
    }

    public void capitalCitiesbyRegion(){
        ArrayList<Capital> capitalCitiesregion = capitalCitybyRegion(location, delay);

        System.out.println(capitalCitiesregion.size());

        printCapitalCities(capitalCitiesregion);
    }

    public void topNCapitalCitiesinWorld(){
        ArrayList<Capital> topCapitalCitiesinWorld = getTopNCapitalCities(5, location, delay);

        System.out.println(topCapitalCitiesinWorld.size());

        printCapitalCities(topCapitalCitiesinWorld);
    }

    public void topNCapitalCitiesinContinent(){
        ArrayList<Capital> topCapitalCitiesinContinent = getTopNCapitalCitiesinContinent(3, location, delay);

        System.out.println(topCapitalCitiesinContinent.size());

        printCapitalCities(topCapitalCitiesinContinent);
    }

    public void topNCapitalCitiesinRegion(){
        ArrayList<Capital> topCapitalCitiesinRegion = getTopNCapitalCitiesinRegion(4, location, delay);

        System.out.println(topCapitalCitiesinRegion.size());

        printCapitalCities(topCapitalCitiesinRegion);
    }

    // All the capital cities in the world organised by largest population to smallest.
    public ArrayList<Capital> getCapitalCity(String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
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

    // All the capital cities in a continent organised by largest population to smallest.
    public ArrayList<Capital> capitalCitybyContinent(String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, c.name as country, cy.population "
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
            System.out.println("Failed to get table details");
            return null;
        }
    }

    // All the capital cities in a region organised by largest to smallest.
    public ArrayList<Capital> capitalCitybyRegion(String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, c.name as country, cy.population "
                            + "FROM city cy, country c WHERE cy.id = c.capital "
                            + "order by c.region, cy.population desc";
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

    // The top N populated capital cities in the world where N is provided by the user.
    public ArrayList<Capital> getTopNCapitalCities(int rank, String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM (SELECT cy.name, c.name as country, cy.population, c.continent, row_number() over (order by cy.population desc) as cityRank "
                            + "FROM country c, city cy  WHERE c.capital = cy.id) ranks "
                            + "WHERE cityRank <= " + rank;
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
                ccty.continent = rset.getString("continent");
                capitalCities.add(ccty);
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    // The top N populated capital cities in a continent where N is provided by the user.
    public ArrayList<Capital> getTopNCapitalCitiesinContinent(int rank, String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM (SELECT cy.name, c.name as country, cy.population, c.continent, row_number() over (partition by c.continent order by cy.population desc) as cityRank "
                            + "FROM country c, city cy  WHERE c.capital = cy.id) ranks "
                            + "WHERE cityRank <= " + rank;
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
                ccty.continent = rset.getString("continent");
                capitalCities.add(ccty);
            }
            return capitalCities;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    // The top N populated capital cities in a region where N is provided by the use
    public ArrayList<Capital> getTopNCapitalCitiesinRegion(int rank, String location, int delay) {
        try {
            Connection con = ra.connect(location, delay);
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * FROM (SELECT cy.name, c.name as country, cy.population, c.continent, c.region, row_number() over (partition by c.region order by cy.population desc) as cityRank "
                            + "FROM country c, city cy  WHERE c.capital = cy.id) ranks "
                            + "WHERE cityRank <= " + rank;
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
                ccty.continent = rset.getString("continent");
                ccty.region = rset.getString("region");
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

        if(capitalCities == null)
        {
            System.out.println("No Data found.");
            return;
        }

        System.out.println(String.format("%-20s %-20s %-20s", "name", "country", "population"));

        for (Capital ccty : capitalCities) {
            if (ccty == null) continue;

            String ccty_string =
                    String.format("%-20s %-20s %-20s",
                            ccty.name, ccty.country, ccty.population);
            System.out.println(ccty_string);
        }
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay){
        this.delay=delay;
    }
}
