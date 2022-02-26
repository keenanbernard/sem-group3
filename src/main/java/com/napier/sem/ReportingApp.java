package com.napier.sem;

import java.sql.*;


public class ReportingApp {

    public static void main(String[] args)
    {
        // Create new Application
        ReportingApp a = new ReportingApp();

        // Connect to database
        a.connect();

        // Get Country
        Country ctr = a.getCountry(50000);
        // Display results
        a.displayCountry(ctr);

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

    public Country getCountry(int pop)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT code, name, gnp "
                            + "FROM country "
                            + "WHERE population > " + pop;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country ctr = new Country();
                ctr.code = rset.getString("code");
                ctr.name = rset.getString("name");
                ctr.gnp = rset.getDouble("gnp");
                return ctr;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayCountry(Country ctr)
    {
        if (ctr != null)
        {
            System.out.println(
                    "Country Code: "+ ctr.code + " "
                    + "Country Name: "+ ctr.name + " "
                    + "Country GNP: "+ ctr.gnp + "\n");
        }
    }
}
