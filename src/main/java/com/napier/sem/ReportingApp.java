package com.napier.sem;

import java.sql.*;

public class ReportingApp {

    public static void main(String[] args)
    {
        // Create new Application
        ReportingApp a = new ReportingApp();
        Country c = new Country();
        City cy = new City();
        //CityRegion cr = new CityRegion();

        /*// Connect to database
        a.connect();*/
        //c.countries();
        // cy.allCities();
        // cy.citiesByRegion();
        cy.TopNCities();

        /*  // Get Country
        ArrayList<Country> countries = a.getCountry();
        // Display results
        System.out.println(countries.size());

        a.printCountries(countries);*/

        // Disconnect from database
        a.disconnect();
    }

    // Connection to the MySQL database
    private Connection con = null;

    public Connection connect() {
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
        return con;
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
}
