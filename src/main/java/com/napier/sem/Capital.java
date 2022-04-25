package com.napier.sem;

import java.sql.*;
import java.util.*;

public class Capital {
    public int id;
    public String name;
    public String country;
    public String continent;
    public int population;
    public String region;

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
    public void capitalCitiesbyRegion(){
        ArrayList<Capital> capitalCitiesregion = capitalCitybyRegion();

        System.out.println(capitalCitiesregion.size());

        printCapitalCities(capitalCitiesregion);
    }


    public void topNCapitalCitiesinContinent(){
        ArrayList<Capital> topCapitalCitiesinContinent = getTopNCapitalCitiesinContinent(5);

        System.out.println(topCapitalCitiesinContinent.size());

        printCapitalCities(topCapitalCitiesinContinent);
    }

    public void topNCapitalCitiesinRegion(){
        ArrayList<Capital> topCapitalCitiesinRegion = getTopNCapitalCitiesinRegion(5);

        System.out.println(topCapitalCitiesinRegion.size());

        printCapitalCities(topCapitalCitiesinRegion);
    }

    public void topNCapitalCitiesinWorld(){
        ArrayList<Capital> topCapitalCitiesinWorld = getTopNCapitalCitiesinWorld(10);

        System.out.println(topCapitalCitiesinWorld.size());

        printCapitalCities(topCapitalCitiesinWorld);
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

    public ArrayList<Capital> getTopNCapitalCitiesinContinent(int rank) {
        try {
            Connection con = ra.connect();
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

    public ArrayList<Capital> getTopNCapitalCitiesinRegion(int rank) {
        try {
            Connection con = ra.connect();
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

    public ArrayList<Capital> getTopNCapitalCitiesinWorld(int rank) {
        try {
            Connection con = ra.connect();
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
            //
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

    public ArrayList<Capital> capitalCitybyRegion() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name, c.name as country, cy.population, c.region "
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
        System.out.println(String.format("%-20s %-20s %-20s %-20s %-20s", "name", "country", "population", "continent", "region"));

        for (Capital ccty : capitalCities) {
            String cty_string =
                    String.format("%-20s %-20s %-20s %-20s %-20s",
                            ccty.name, ccty.country, ccty.population, ccty.continent, ccty.region);
            System.out.println(cty_string);
        }
    }
}
