package com.napier.sem;

import java.sql.*;
import java.util.*;

public class Country {

   public String code;
   public String name;
   public String continent;
   public String region;
   public int population;
   public int capital;

   ReportingApp ra = new ReportingApp();

   public void Countries(){
      ArrayList<Country> countries = getCountries();

      System.out.println(countries.size());

      printCountryReport(countries);
   }

   public void countriesbyContinent(){
      ArrayList<Country> countries = getCountrybyContinent();

      System.out.println(countries.size());

      printCountryReport(countries);
   }

   public void countriesbyRegion() {
      ArrayList<Country> countriesbyregion = getCountrybyRegion();

      System.out.println(countriesbyregion.size());

      printCountryReport(countriesbyregion);
   }

   public void topNCountries(){
      ArrayList<Country> topNCountries = getTopNCountries(5);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

   public void topNCountriesbyContinent(){
      ArrayList<Country> topNCountries = getTopNCountriesbyContinent(3);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

   public void topNCountriesbyRegion(){
      ArrayList<Country> topNCountries = getTopNCountriesbyRegion(4);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

  // All the countries in the world organised by largest population to smallest.
   public ArrayList<Country> getCountries() {
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
         ArrayList<Country> countries = new ArrayList<>();
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

   // All the countries in a continent organised by largest population to smallest.
   public ArrayList<Country> getCountrybyContinent() {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.continent, c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new employee if valid.
         // Check one is returned
         ArrayList<Country> countriesbyregion = new ArrayList<>();
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

   // All the countries in a region organised by largest population to smallest.
   public ArrayList<Country> getCountrybyRegion() {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.region, c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new employee if valid.
         // Check one is returned
         ArrayList<Country> countriesbyregion = new ArrayList<>();
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

   // The top N populated countries in the world where N is provided by the user.
   public ArrayList<Country> getTopNCountries(int rank) {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (order by c.population desc) as countryRank "
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

   // The top N populated countries in a continent where N is provided by the user.
   public ArrayList<Country> getTopNCountriesbyContinent(int rank) {
      try {
         Connection con = ra.connect();
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (partition by c.continent order by c.population desc) as countryRank "
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

   // The top N populated countries in a region where N is provided by the user.
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

   public void printCountryReport(ArrayList<Country> countries) {

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
