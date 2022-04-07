package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Population {

    public String code;
    public String name;
    public String continent;
    public String region;
    public String district;
    public double surfaceArea;
    public int independenceYear;
    public int population;
    public double lifeExpectancy;
    public double gnp;
    public double gnpOld;
    public String localName;
    public String governmentForm;
    public String headOfState;
    public int capital;
    public String code2;


    ReportingApp ra = new ReportingApp();

    public void getPopulationbyCity() {
        ArrayList<Population> populations = getPopulation();

        System.out.println(populations.size());

        printPopulation(populations);

    }
    public ArrayList<Population> getPopulation() {
        try {
            Connection con = ra.connect();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT cty.district, pn.code, pn.name, pn.localName, pn.population, pn.capital "
                            + "FROM population pn, city cty "
                            + "order by pn.population desc";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            ArrayList<Population> population = new ArrayList<Population>();
            while (rset.next()) {
                Population pn = new Population();
                pn.code = rset.getString("code");
                pn.name = rset.getString("name");
                pn.district = rset.getString("district");
                pn.localName = rset.getString("localName");
                pn.population = rset.getInt("population");
                pn.capital = rset.getInt("capital");
                population.add(pn);
            }
            return population;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }



    public void printPopulation(ArrayList<Population> population) {
        System.out.println(String.format("%-10s %-15s %-15s %-20s %-15s %-15s", "code", "name", "continent", "region", "population", "capital"));

        for (Population pn : population) {
            String ctr_string =
                    String.format("%-10s %-15s %-15s %-20s %-15s %-15s",
                            pn.code, pn.name, pn.continent, pn.region, pn.population, pn.capital);
            System.out.println(ctr_string);
        }
    }











}
