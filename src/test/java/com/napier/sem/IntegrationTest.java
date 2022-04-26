package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    static Country ctr;
    static City cty;
    static Capital cpl;
    static Population pn;


    @BeforeAll
    static void init()
    {
        ctr = new Country();
        cty = new City();
        cpl = new Capital();
        pn = new Population();

    }

    @Test
    void testGetCountries()
    {
        List<Country> countries = ctr.getCountries("localhost:33060", 0);
        assertEquals(countries.get(0).code, "CHN");
        assertEquals(countries.get(0).name, "China");
        assertEquals(countries.get(0).continent, "Asia");
        assertEquals(countries.get(0).region, "Eastern Asia");
        assertEquals(countries.get(0).population, 1277558000);
        assertEquals(countries.get(0).capital, 1891);
    }

    @Test
    void testGetTopNCountries()
    {
        List<Country> countries = ctr.getTopNCountries(3,"localhost:33060", 0);
        assertEquals(countries.get(1).code, "IND");
        assertEquals(countries.get(1).name, "India");
        assertEquals(countries.get(1).continent, "Asia");
        assertEquals(countries.get(1).region, "Southern and Central Asia");
        assertEquals(countries.get(1).population, 1013662000);
        assertEquals(countries.get(1).capital, 1109);
    }

    @Test
    void testGetCities()
    {
        List<City> cities = cty.getCities("localhost:33060", 0);
        assertEquals(cities.get(0).name, "Mumbai (Bombay)");
        assertEquals(cities.get(0).countrycode, "IND");
        assertEquals(cities.get(0).district, "Maharashtra");
        assertEquals(cities.get(0).population, 10500000);
    }

    @Test
    void testGetTopNCitiesbyDistrict()
    {
        List<City> cities = cty.getTopNCitiesbyDistrict(2, "localhost:33060", 0);
        assertEquals(cities.get(0).name, "Taiping");
        assertEquals(cities.get(0).countrycode, "TWN");
        assertEquals(cities.get(0).district, "");
        assertEquals(cities.get(0).population, 165524);
    }

    @Test
    void testCapitalCity()
    {
        List<Capital> capitalCities = cpl.getCapitalCity("localhost:33060", 0);
        assertEquals(capitalCities.get(0).name, "Seoul");
        assertEquals(capitalCities.get(0).country, "South Korea");
        assertEquals(capitalCities.get(0).population, 9981619);
    }

    @Test
    void testTopNCapitalCitiesinRegion()
    {
        List<Capital> capitalCities = cpl.getTopNCapitalCitiesinRegion(2,"localhost:33060", 0);
        assertEquals(capitalCities.get(3).name, "Vilnius");
        assertEquals(capitalCities.get(3).country, "Lithuania");
        assertEquals(capitalCities.get(3).population, 577969);
    }

    @Test
    void testGetWorldsPopulation()
    {
        Long wpn = new Long("6078749450");
        Long wrl = new Long("4649189566");
        List<Population> population = pn.getWorldsPopulation("localhost:33060", 0);
        assertEquals(population.get(0).name, "World");
        assertEquals(population.get(0).population, BigDecimal.valueOf(wpn));
        assertEquals(population.get(0).urban, BigDecimal.valueOf(1429559884));
        assertEquals(population.get(0).urbanPercent, "23.52%");
        assertEquals(population.get(0).rural, BigDecimal.valueOf(wrl));
        assertEquals(population.get(0).ruralPercent, "76.48%");
    }

    @Test
    void testGetPopulationOfaCountry()
    {
        List<Population> population = pn.getPopulationofaCountry("Belize","localhost:33060", 0);
        assertEquals(population.get(0).name, "Belize");
        assertEquals(population.get(0).population, BigDecimal.valueOf(241000));
        assertEquals(population.get(0).urban, BigDecimal.valueOf(62915));
        assertEquals(population.get(0).urbanPercent, "26.11%");
        assertEquals(population.get(0).rural, BigDecimal.valueOf(178085));
        assertEquals(population.get(0).ruralPercent, "73.89%");
    }

    @Test
    void testGetWorldLanguages()
    {
        List<Population> population = pn.getWorldLanguages("localhost:33060", 0);
        assertEquals(population.get(0).language, "Chinese");
        assertEquals(population.get(0).population, BigDecimal.valueOf(1191843539));
        assertEquals(population.get(0).percentage, "19.61%");
    }
}



