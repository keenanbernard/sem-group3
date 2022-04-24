package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class test{
    static Country ctry;
    static City city;
    static Capital cpt;

    @BeforeAll
    static void init()
    {
        ctry = new Country();
        city = new City();
        cpt = new Capital();
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

    @Test
    void printCapitalCities()
    {
        ArrayList<Capital> capitalCities = new ArrayList<>();
        Capital ccty = new Capital();
        ccty.name = "Canberra";
        ccty.country = "Australia";
        ccty.population = 322723;
        capitalCities.add(ccty);
        cpt.printCapitalCities(capitalCities);
    }

    @Test
    void printCapitalCitiesTestContainsNull()
    {
        ArrayList<Capital> capitalCities = new ArrayList<>();
        capitalCities.add(null);
        cpt.printCapitalCities(capitalCities);
    }

    @Test
    void printCapitalCitiesTestEmpty()
    {
        ArrayList<Capital> capitalCities = new ArrayList<>();
        cpt.printCapitalCities(capitalCities);
    }
}