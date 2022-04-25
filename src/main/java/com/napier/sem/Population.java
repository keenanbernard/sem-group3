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
    public String language;
    public String percentage;


    ReportingApp ra = new ReportingApp();


    public void populationbyAllContinents() {
        ArrayList<Population> populations = getPopulationbyAllContinents();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationbyAllRegions() {
        ArrayList<Population> populations = getPopulationbyAllRegions();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationbyALLCountries() {
        ArrayList<Population> populations = getPopulationbyAllCountries();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void worldsPopulation() {
        ArrayList<Population> populations = getWorldsPopulation();

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationofaContinent(String cont){
        ArrayList<Population> populations = getPopulationofaContinent(cont);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void PopulationofaRegion(String reg) {
        ArrayList<Population> populations = getPopulationofaRegion(reg);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationofaCountry(String country) {
        ArrayList<Population> populations = getPopulationofaCountry(country);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationofaDistrict(String district){
        ArrayList<Population> populations = getPopulationofaDistrict(district);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void populationofaCity(String city){
        ArrayList<Population> populations = getPopulationOfaCity(city);

        System.out.println(populations.size());

        printPopulation(populations);
    }

    public void worldLanguages(){
        ArrayList<Population> worldLanguages = getWorldLanguages();

        System.out.println(worldLanguages.size());

        printPercentage(worldLanguages);
    }

    //Get the population for all continents along with their statistics for living in cities (urban) and not living in cities (rural)
    public ArrayList<Population> getPopulationbyAllContinents() {
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


    //Get the population for all regions along with their statistics for living in cities (urban) and not living in cities (rural)
    public ArrayList<Population> getPopulationbyAllRegions() {
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
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    //Get the population for all countries along with their statistics for living in cities (urban) and not living in cities (rural)
    public ArrayList<Population> getPopulationbyAllCountries() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.name as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode "
                            + "GROUP BY c.name";
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
            System.out.println("Failed to get employee details");
            return null;
        }
    }


    //Get the population for the world along with its statistics for living in cities (urban) and not living in cities (rural)
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


    //Get the population for a specific continent along with their statistics for living in cities (urban) and not living in cities (rural)
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


    //Get the population for a specific region along with their statistics for living in cities (urban) and not living in cities (rural)
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
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND c.region = '"+reg+"' "
                            + "GROUP BY c.region";
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


    //Get the population for a specific country along with their statistics for living in cities (urban) and not living in cities (rural)
    public ArrayList<Population> getPopulationofaCountry(String country) {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT c.name as name,  SUM(distinct c.population) as population, "
                            + "SUM(cy.population) as urban, "
                            + "CONCAT(FORMAT((SUM(cy.population)/SUM(distinct c.population))*100,2),'%') as 'urban(%)', "
                            + "(SUM(distinct c.population)-SUM(cy.population)) as rural, "
                            + "CONCAT(FORMAT(((SUM(distinct c.population)-SUM(cy.population))/SUM(distinct c.population))*100,2),'%') as 'rural(%)'  "
                            + "FROM country c, city cy WHERE c.code = cy.countrycode AND c.name = '"+country+"' "
                            + "GROUP BY c.name";
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
            System.out.println("Failed to get employee details");
            return null;
        }
    }


    //Get the population for a specific district along with their statistics for living in cities (urban) and not living in cities (rural)
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


    //Get the population for a specific city along with their statistics for living in cities (urban) and not living in cities (rural)
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


    //Get the population for people that speak specific languages along with their percentage, in regard to the world's population.
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

        if(population == null)
        {
            System.out.println("No Data found.");
            return;
        }

        System.out.println(String.format("%-15s %-15s %-20s %-20s %-15s %-20s", "name", "population", "urban", "urban(%)", "rural", "rural(%)"));

        for (Population pn : population) {
            if (pn == null) continue;

            String pn_string =
                    String.format("%-15s %-15s %-20s %-20s %-15s %-20s",
                            pn.name, pn.population, pn.urban, pn.urbanPercent, pn.rural, pn.ruralPercent);
            System.out.println(pn_string);
        }
    }


    public void printPercentage(ArrayList<Population> popPercentages) {

        if(popPercentages == null)
        {
            System.out.println("No Data found.");
            return;
        }

        System.out.println(String.format("%-25s %-25s %-15s", "Language", "Population", "Percentage"));

        for (Population pn : popPercentages) {
            if (pn == null) continue;

            String pn_string =
                    String.format("%-25s %-25s %-15s ",
                            pn.language,pn.population, pn.percentage);
            System.out.println(pn_string);
        }
    }
}