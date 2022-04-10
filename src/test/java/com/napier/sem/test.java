package com.napier.sem;

import com.napier.sem.Country;
import com.napier.sem.ReportingApp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class test{
    static Country ctry;

    @BeforeAll
    static void init()
    {
        ctry = new Country();
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<>();
        Country ctr = new Country();
        ctr.code = "BZE";
        ctr.name = "Belize";
        ctr.continent = "CA";
        ctr.region = "n/a";
        ctr.population = 300000;
        ctr.capital = 01;
        countries.add(ctr);
        ctry.printCountries(countries);
    }

    @Test
       void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        ctry.printCountries(countries);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        ctry.printCountries(countries);
    }
}