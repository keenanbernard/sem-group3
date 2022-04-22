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
    public String urban;
    public String rural;
    public String language;
    public int percentage;


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

    public void worldLanguages(){
        ArrayList<Population> worldLanguages = getWorldLanguages();

        System.out.println(worldLanguages.size());

        printPercentage(worldLanguages);
    }

    public ArrayList<Population> getPopulation() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.district, c.code, c.name, c.localName, c.population, c.capital "
                            + "FROM country c, city cy "
                            + "order by c.country desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
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
                    "SELECT c.name, c.population, c.name, c.name  "
                            + "FROM country c, city cy "
                            + "WHERE c.code = cy.countrycode "
                            + "ORDER BY c.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> populationbyRegion = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
                populationbyRegion.add(pn);
            }
            return populationbyRegion;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
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
                    "SELECT '',  SUM(c.population) as population, "
                            + "CONCAT(FORMAT(((SELECT SUM(cy.population) FROM city cy)/(SELECT SUM(c.population) FROM country c))*100,2),'%') as urban, "
                            + "CONCAT(FORMAT((((SELECT SUM(c.population) FROM country c)-(SELECT SUM(cy.population) FROM city cy))/(SELECT SUM(c.population) FROM country c))*100,2),'%') as rural  "
                            + "FROM country c ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = "World";
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
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
                    "SELECT * from (SELECT c.name, cy.countrycode, cy.district, c.population, row_number() over (partition by pn.region order by pn.population desc) as countryRank "
                            + "FROM city cy, population pn where cy.countrycode = c.code) ranks "
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
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
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
                    "SELECT * from (SELECT c.name, c.countrycode, cy.district, c.population, row_number() over (partition by pn.region order by pn.population desc) as countryRank "
                            + "FROM city cy, population pn where cy.countrycode = c.code) ranks "
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
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
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
                    "SELECT * from (SELECT cy.name, cy.countrycode, cy.district, cy.population, row_number() over (partition by cy.district order by cy.population desc) as cityRank "
                            + "FROM city cy) ranks "
                            + "WHERE cityRank <= " + rank;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> tpNDistrict = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getString("urban");
                pn.rural = rset.getString("rural");
                tpNDistrict.add(pn);
            }
            return tpNDistrict;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getWorldLanguages() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT  cl.Language , SUM(c.Population * (cl.Percentage/100)) as population, (SUM(c.Population * (cl.Percentage/100))/(SELECT SUM(c.Population) FROM country c)*100) as percent\n" +
                            "FROM (SELECT * \n" +
                            "      FROM countrylanguage as cl\n" +
                            "      WHERE cl.Language IN (\"Chinese\", \"English\", \"Hindi\", \"Spanish\", \"Arabic\")) as cl, country c \n" +
                            "WHERE cl.CountryCode = c.Code \n" +
                            "GROUP BY cl.Language \n" +
                            "ORDER BY population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> worldLanguage = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.language = rset.getString("Language");
                pn.population = rset.getBigDecimal("population");
                pn.percentage = rset.getInt("percent");
                worldLanguage.add(pn);
            }
            return worldLanguage;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public void printPopulation(ArrayList<Population> population) {
        System.out.println(String.format("%-15s %-15s %-20s %-20s", "name", "population", "urban", "rural"));

        for (Population pn : population) {
            String pn_string =
                    String.format("%-15s %-15s %-20s %-20s",
                            pn.name, pn.population, pn.urban, pn.rural);
            System.out.println(pn_string);
        }
    }


    public void printPercentage(ArrayList<Population> popPercentages) {
        System.out.println(String.format("%-25s %-25s %-15s", "Language", "Population", "Percentage"));

        for (Population pn : popPercentages) {
            String pn_string =
                    String.format("%-25s %-25s %-15s ",
                            pn.language,pn.population, pn.percentage + "%");
            System.out.println(pn_string);
        }
    }










}
