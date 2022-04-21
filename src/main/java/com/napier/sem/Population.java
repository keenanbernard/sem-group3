package com.napier.sem;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Population {

    public String name;
    public BigDecimal population;
    public BigDecimal urban;
    public BigDecimal rural;


    ReportingApp ra = new ReportingApp();

    public void getPopulationbyCity() {
        ArrayList<Population> populations = getPopulation();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void PopulationbyRegion() {
        ArrayList<Population> populationbyregion = getPopulation();

        System.out.println(populationbyregion.size());

        printPopulation(populationbyregion);
    }

    public void getWorldPopulation() {
        ArrayList<Population> populations = getWorldsPopulation();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void TopNPopulationbyRegion() {
        ArrayList<Population> topNRegion = getTopNPopulationbyRegion(5);

        System.out.println(topNRegion.size());

        printPopulation(topNRegion);
    }

    public void TopNPopulationbyCountry() {
        ArrayList<Population> topNCountry = getTopNPopulationbyCountry(5);

        System.out.println(topNCountry.size());

        printPopulation(topNCountry);
    }

    public void TopNPopulationbyDistrict(){
        ArrayList<Population> topNDistrict = getTopNPopulationbyDistrict(1);

        System.out.println(topNDistrict.size());

        printPopulation(topNDistrict);
    }

    public ArrayList<Population> getPopulation() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.district, pn.code, pn.name, pn.localName, pn.population, pn.capital "
                            + "FROM population pn, city cy "
                            + "order by pn.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationbyRegion() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(pn.population) as population, ((SELECT SUM(pn.population) FROM population pn)/(SELECT SUM(pn.region) FROM country c)) as urban, (((SELECT SUM(c.population) FROM country c)-(SELECT SUM(pn.population) FROM city cy))/(SELECT SUM(pn.population) FROM region rg)) as rural  "
                            + "FROM country c ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> populationbyregion = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                //pn.name = "World";
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                populationbyregion.add(pn);
            }
            return populationbyregion;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getWorldsPopulation() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(c.population) as population, ((SELECT SUM(c.population) FROM city cy)/(SELECT SUM(c.population) FROM country c)) as urban, (((SELECT SUM(c.population) FROM country c)-(SELECT SUM(cy.population) FROM city cy))/(SELECT SUM(c.population) FROM country c)) as rural  "
                            + "FROM country c ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                //pn.name = "World";
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Population> getTopNPopulationbyRegion(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT pn.name, pn.countrycode, cy.district, pn.population, row_number() over (partition by pn.region order by pn.population desc) as countryRank "
                            + "FROM city cy, population pn where pn.countrycode = c.code) ranks "
                            + "WHERE countryRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> tpNRegion = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                tpNRegion.add(pn);
            }
            return tpNRegion;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }


    public ArrayList<Population> getTopNPopulationbyCountry(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT pn.name, pn.countrycode, cy.district, pn.population, row_number() over (partition by pn.region order by pn.population desc) as countryRank "
                            + "FROM city cy, population pn where pn.countrycode = c.code) ranks "
                            + "WHERE countryRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> tpNCountry = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                tpNCountry.add(pn);
            }
            return tpNCountry;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getTopNPopulationbyDistrict(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT * from (SELECT pn.name, pn.countrycode, pn.district, pn.population, row_number() over (partition by pn.district order by pn.population desc) as districtRank "
                            + "FROM population pn) ranks "
                            + "WHERE districtRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> tpNDistrict = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.rural = rset.getBigDecimal("rural");
                tpNDistrict.add(pn);
            }
            return tpNDistrict;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }


    public void printPopulation(ArrayList<Population> population) {
        System.out.println(String.format("%-15s %-15s %-20s", "population", "urban", "rural"));

        for (Population pn : population) {
            String pn_string =
                    String.format("%-15s %-15s %-20s",
                            pn.population, pn.urban, pn.rural);
            System.out.println(pn_string);
        }
    }











}
