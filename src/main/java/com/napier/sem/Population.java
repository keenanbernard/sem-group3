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
    public String urbanPercent;
    public BigDecimal urban;
    public String ruralPercent;
    public BigDecimal rural;
    public String district;
    public String language;
    public String percentage;


    ReportingApp ra = new ReportingApp();

    public void getPopulationbyCity() {
        ArrayList<Population> populations = getPopulationinruralandurban();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void PopulationbyRegion() {
        ArrayList<Population> populations = getPopulationbyRegion();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void worldsPopulation() {
        ArrayList<Population> populations = getWorldsPopulation();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationbyAllContinent() {
        ArrayList<Population> populations = getPopulationbyAllContinent();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void PopulationofaRegion(String reg) {
        ArrayList<Population> populations = getPopulationofaRegion(reg);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void TopNPopulationbyCountry() {
        ArrayList<Population> topNCountry = getPopulationofaCountry(5);

        System.out.println(topNCountry.size());

        printPopulation(topNCountry);
    }

    public void populationofaDistrict(String district){
        ArrayList<Population> populations = getPopulationofaDistrict(district);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationofaContinent(String cont){
        ArrayList<Population> populations = getPopulationofaContinent(cont);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationOfaCity(String cont){
        ArrayList<Population> cityPop = getPopulationOfaCity(cont);

        System.out.println(cityPop.size());

        printPopulation(cityPop);
    }

    public void worldLanguages(){
        ArrayList<Population> worldLanguages = getWorldLanguages();

        System.out.println(worldLanguages.size());

        printPercentage(worldLanguages);
    }

    public ArrayList<Population> getPopulationinruralandurban() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.name as name,  SUM(distinct c.population) as population, \n" +
                            "SUM(cy.population) as urban, \n" +
                            "(SUM(distinct c.population)-SUM(cy.population)) as rural   \n" +
                            "FROM country c, city cy WHERE c.code = cy.countrycode \n" +
                            "GROUP BY c.region";
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
                    "SELECT c.region as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode "
                            + "GROUP BY c.region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> populationbyRegion = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
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
                            + "(SELECT SUM(cy.population) FROM city cy) as urban, "
                            + "CONCAT(FORMAT(((SELECT SUM(cy.population) FROM city cy)/(SELECT SUM(c.population) FROM country c))*100,2),'%') as 'urban(%)', "
                            + "((SELECT SUM(c.population) FROM country c)-(SELECT SUM(cy.population) FROM city cy)) as rural, "
                            + "CONCAT(FORMAT((((SELECT SUM(c.population) FROM country c)-(SELECT SUM(cy.population) FROM city cy))/(SELECT SUM(c.population) FROM country c))*100,2),'%') as 'rural(%)'  "
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
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationbyAllContinent() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                   "SELECT c.continent as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode "
                            + "GROUP BY c.continent";
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
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationofaCountry(int rank) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT name, SUM(Population) FROM country\n" +
                            "WHERE name = 'United States' GROUP BY name";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> tpNRegion = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.district = rset.getString("district");
                tpNRegion.add(pn);
            }
            return tpNRegion;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }


    public ArrayList<Population> getPopulationofaRegion(String reg) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.region as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND c.Continent = '"+reg+"' "
                            + "GROUP BY c.region";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> PopulationsofaRegion = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                PopulationsofaRegion.add(pn);
            }
            return PopulationsofaRegion;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationofaDistrict(String district) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.District as name,  SUM(cy.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(cy.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(cy.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(cy.population)-SUM(cy.population))/SUM(cy.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND cy.District = '"+district+"' "
                            + "GROUP BY cy.District";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationofaContinent(String cont) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.continent as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND c.Continent = '"+cont+"' "
                            + "GROUP BY c.continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get table details");
            return null;
        }
    }

    public ArrayList<Population> getPopulationOfaCity(String city) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cy.name as name,  SUM(cy.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(cy.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(cy.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(cy.population)-SUM(cy.population))/SUM(cy.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND cy.name = '"+city+"' "
                            + "GROUP BY cy.name";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<>();
            while (rset.next()) {
                Population pn = new Population();
                pn.name = rset.getString("name");
                pn.population = rset.getBigDecimal("population");
                pn.urban = rset.getBigDecimal("urban");
                pn.urbanPercent = rset.getString("urban(%)");
                pn.rural = rset.getBigDecimal("rural");
                pn.ruralPercent = rset.getString("rural(%)");
                population.add(pn);
            }
            return population;

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
                    "SELECT  cl.Language , SUM(c.Population * (cl.Percentage/100)) as population, CONCAT(FORMAT((SUM(c.Population * (cl.Percentage/100))/(SELECT SUM(c.Population) FROM country c)*100), 2), '%') as percent\n" +
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
                pn.percentage = rset.getString("percent");
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
        System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-20s", "name", "population", "urban", "urban(%)", "rural", "rural(%)"));

        for (Population pn : population) {
            String pn_string =
                    String.format("%-15s %-15s %-20s %-20s %-15s %-20s",
                            pn.name, pn.population, pn.urban, pn.urbanPercent, pn.rural, pn.ruralPercent);
            System.out.println(pn_string);
        }
    }


    public void printPercentage(ArrayList<Population> popPercentages) {
        System.out.println(String.format("%-25s %-25s %-15s", "Language", "Population", "Percentage"));

        for (Population pn : popPercentages) {
            String pn_string =
                    String.format("%-25s %-25s %-15s ",
                            pn.language,pn.population, pn.percentage);
            System.out.println(pn_string);
        }
    }
}
