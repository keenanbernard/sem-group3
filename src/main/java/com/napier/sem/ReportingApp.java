package com.napier.sem;

import java.sql.*;

public class ReportingApp {

    public static void main(String[] args)
    {
        // Create new Application
        ReportingApp a = new ReportingApp();
        Country c = new Country();
        City cy = new City();
        Capital cc = new Capital();
        Population pn = new Population();

        if (args.length < 1){
            cy.setLocation("localhost:33060");
            c.setLocation("localhost:33060");
            cc.setLocation("localhost:33060");
            pn.setLocation("localhost:33060");
            cy.setDelay(0);
            c.setDelay(0);
            cc.setDelay(0);
            pn.setDelay(0);
        }else{
            cy.setLocation("worldDB:3306");
            c.setLocation("worldDB:3306");
            cc.setLocation("worldDB:3306");
            pn.setLocation("worldDB:3306");
            cy.setDelay(30000);
            c.setDelay(30000);
            cc.setDelay(30000);
            pn.setDelay(30000);
        }


        // Report Method Called, initiating connection on each method.
        pn.worldLanguages();

        // Disconnect from database
        a.disconnect();
    }

    // Connection to the MySQL database
    private Connection con = null;

    public Connection connect(String location, int delay) {
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
                Thread.sleep(delay);
                // Connect to database using database container name
                con = DriverManager.getConnection("jdbc:mysql://"+location+"/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                //db connection worldDB:3306
                System.out.println("Successfully connected");
                // Wait a bit
                //Thread.sleep(10000);
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
