package com.napier.sem;

import java.awt.*;
import java.sql.*;
import java.util.*;

public class Country {

   public String code;
   public String name;
   public String continent;
   public String region;
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

   public void allCountries(){
      ArrayList<Country> countries = getCountry();

      System.out.println(countries.size());

      printCountries(countries);
   }

   public void TopNCountriesbyRegion(){
      ArrayList<Country> topNCountries = getTopNCountriesbyRegion(5);

      System.out.println(topNCountries.size());

      printCountries(topNCountries);
   }
   public void CountriesbyRegion() {
      ArrayList<Country> countriesbyregion = getCountry();

      System.out.println(countriesbyregion.size());

      printCountries(countriesbyregion);
   }


   public ArrayList<Country> getCountry() {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new employee if valid.
         // Check one is returned
         ArrayList<Country> countries = new ArrayList<Country>();
         while (rset.next()) {
            Country ctr = new Country();
            ctr.code = rset.getString("code");
            ctr.name = rset.getString("name");
            ctr.continent = rset.getString("continent");
            ctr.region = rset.getString("region");
            ctr.population = rset.getInt("population");
            ctr.capital = rset.getInt("capital");
            countries.add(ctr);
         }
         return countries;

      } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("Failed to get employee details");
         return null;
      }
   }

   public ArrayList<Country> getTopNCountriesbyRegion(int rank) {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (partition by c.region order by c.population desc) as countryRank "
                         + "FROM country c) ranks "
                         + "WHERE countryRank <= " + rank;
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new employee if valid.
         // Check one is returned
         ArrayList<Country> tpNCountries = new ArrayList<>();
         while (rset.next()) {
            Country ctr = new Country();
            ctr.code = rset.getString("code");
            ctr.name = rset.getString("name");
            ctr.continent = rset.getString("continent");
            ctr.region = rset.getString("region");
            ctr.population = rset.getInt("population");
            ctr.capital = rset.getInt("capital");
            tpNCountries.add(ctr);
         }
         return tpNCountries;

      } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("Failed to get table details");
         return null;
      }
   }

   public ArrayList<Country> getCountrybyRegion() {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new employee if valid.
         // Check one is returned
         ArrayList<Country> countriesbyregion = new ArrayList<Country>();
         while (rset.next()) {
            Country ctr = new Country();
            ctr.code = rset.getString("code");
            ctr.name = rset.getString("name");
            ctr.continent = rset.getString("continent");
            ctr.region = rset.getString("region");
            ctr.population = rset.getInt("population");
            ctr.capital = rset.getInt("capital");
            countriesbyregion.add(ctr);
         }
         return countriesbyregion;

      } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("Failed to get employee details");
         return null;
      }
   }

   public void printCountries(ArrayList<Country> countries) {

      if(countries == null)
      {
         System.out.println("No Data found.");
         return;
      }

      System.out.println(String.format("%-10s %-15s %-15s %-20s %-15s %-15s", "code", "name", "continent", "region", "population", "capital"));

      for (Country ctr : countries) {
         if (ctr == null) continue;

         String ctr_string =
                 String.format("%-10s %-15s %-15s %-20s %-15s %-15s",
                         ctr.code, ctr.name, ctr.continent, ctr.region, ctr.population, ctr.capital);
         System.out.println(ctr_string);
      }
   }

}
