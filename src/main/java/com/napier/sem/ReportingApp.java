package com.napier.sem;

import java.sql.*;
import java.util.*;

public class ReportingApp {

    public static void main(String[] args)
    {
        // Create new Application
        ReportingApp a = new ReportingApp();

        // Connect to database
        a.connect();

        // Get Country
        ArrayList<Country> countries = a.getCountry();
        // Display results
        System.out.println(countries.size());

        a.printCountries(countries);

        // Disconnect from database
        a.disconnect();
    }

    // Connection to the MySQL database
    private Connection con = null;

    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 5;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database using database container name
                con = DriverManager.getConnection("jdbc:mysql://worldDB:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
                // Exit for loop
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    public void disconnect() {
        if (con != null) {
                try {
                    // Close connection
                    con.close();
                } catch (Exception e) {
                    System.out.println("Error closing connection to database");
                }
            }
        }


    public ArrayList<Country> getCountry()
    {
        try
        {
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
            while (rset.next())
            {
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
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void printCountries(ArrayList<Country> countries)
    {
        System.out.println(String.format("%-10s %-15s %-15s %-20s %-15s %-15s" , "code", "name", "continent", "region", "population", "capital"));

        for (Country ctr : countries)
        {
            String ctr_string =
                    String.format("%-10s %-15s %-15s %-20s %-15s %-15s",
                            ctr.code, ctr.name, ctr.continent,  ctr.region, ctr.population, ctr.capital);
            System.out.println(ctr_string);
        }
    }

}
