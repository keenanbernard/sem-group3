package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    static Population pn;

    @BeforeAll
    static void init()
    {
        pn = new Population();
    }

    @Test
    void testGetWorldsPopulation()
    {
        Long wpn = new Long("6078749450");
        Long wrl = new Long("4649189566");
        Population pn = new Population();
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



