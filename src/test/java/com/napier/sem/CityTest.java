package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class CityTest {

    static City city;

    @BeforeAll
    static void init()
    {
        city = new City();
    }

    @Test
    void printCities()
    {
        ArrayList<City> cities = new ArrayList<>();
        City cty = new City();
        cty.name = "Presidente Prudente";
        cty.countrycode = "BRA";
        cty.district = "São Paulo";
        cty.population = 185340;
        cities.add(cty);
        city.printCityReport(cities);
    }

    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(null);
        city.printCityReport(cities);
    }

    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<City> cities = new ArrayList<>();
        city.printCityReport(cities);
    }
}
