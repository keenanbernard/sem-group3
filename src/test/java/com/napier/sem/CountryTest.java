package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;


public class CountryTest {

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
        ctr.code = "BLZ";
        ctr.name = "Belize";
        ctr.continent = "North America";
        ctr.region = "Central America";
        ctr.population = 241000;
        ctr.capital = 185;
        countries.add(ctr);
        ctry.printCountryReport(countries);
    }

    @Test
       void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(null);
        ctry.printCountryReport(countries);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<>();
        ctry.printCountryReport(countries);
    }
}