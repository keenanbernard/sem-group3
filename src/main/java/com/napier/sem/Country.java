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

   //creating the relationship with the ReportingApp Class
   ReportingApp ra = new ReportingApp();
   private String location;
   private int delay;

   //The following 6 public voids assigns the returned arraylists from the methods and send them to the print method for display.
   public void Countries(){
      ArrayList<Country> countries = getCountries(location, delay);

      System.out.println(countries.size());

      printCountryReport(countries);
   }

   public void countriesbyContinent(){
      ArrayList<Country> countries = getCountrybyContinent(location, delay);

      System.out.println(countries.size());

      printCountryReport(countries);
   }

   public void countriesbyRegion() {
      ArrayList<Country> countriesbyregion = getCountrybyRegion(location, delay);

      System.out.println(countriesbyregion.size());

      printCountryReport(countriesbyregion);
   }

   public void topNCountries(){
      ArrayList<Country> topNCountries = getTopNCountries(5, location, delay);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

   public void topNCountriesbyContinent(){
      ArrayList<Country> topNCountries = getTopNCountriesbyContinent(3, location, delay);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

   public void topNCountriesbyRegion(){
      ArrayList<Country> topNCountries = getTopNCountriesbyRegion(4, location, delay);

      System.out.println(topNCountries.size());

      printCountryReport(topNCountries);
   }

   //Get all the countries in the world organised by largest population to smallest, along with its code, name, continent, region, population and capital.
   public ArrayList<Country> getCountries(String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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

   //Get all the countries in a continent organised by largest population to smallest, along with its code, name, continent, region, population and capital.
   public ArrayList<Country> getCountrybyContinent(String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.continent, c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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

   //Get all the countries in a region organised by largest population to smallest, along with its code, name, continent, region, population and capital.
   public ArrayList<Country> getCountrybyRegion(String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT c.code, c.name, c.continent, c.region, c.population, c.capital "
                         + "FROM country c "
                         + "order by c.region, c.population desc";
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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

   // Get the top N populated countries in the world where N is provided by the user.
   public ArrayList<Country> getTopNCountries(int rank, String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (order by c.population desc) as countryRank "
                         + "FROM country c) ranks "
                         + "WHERE countryRank <= " + rank;
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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

   // Get the top N populated countries in a continent where N is provided by the user.
   public ArrayList<Country> getTopNCountriesbyContinent(int rank, String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (partition by c.continent order by c.population desc) as countryRank "
                         + "FROM country c) ranks "
                         + "WHERE countryRank <= " + rank;
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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

   // get the top N populated countries in a region where N is provided by the user.
   public ArrayList<Country> getTopNCountriesbyRegion(int rank, String location, int delay) {
      try {
         //runs the db connection method from main. Also sends out the location (db host) and delay variables
         Connection con = ra.connect(location, delay);
         // Create an SQL statement
         Statement stmt = con.createStatement();
         // Create string for SQL statement
         String strSelect =
                 "SELECT * from (SELECT c.code, c.name, c.continent, c.region, c.population, c.capital, row_number() over (partition by c.region order by c.population desc) as countryRank "
                         + "FROM country c) ranks "
                         + "WHERE countryRank <= " + rank;
         // Execute SQL statement
         ResultSet rset = stmt.executeQuery(strSelect);
         // Return new population array, if valid
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


   //returns the location variable to the population class to assign it to the private variable
   public String getLocation() {
      return location;
   }

   //gets the location variable from main and assigns it to the locally
   public void setLocation(String location){
      this.location=location;
   }

   //returns the delay variable to the population class to assign it to the private variable
   public int getDelay() {
      return delay;
   }

   //gets the delay variable from main and assigns it to the locally
   public void setDelay(int delay){
      this.delay=delay;
   }
}
