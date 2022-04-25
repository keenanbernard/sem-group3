package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PopulationTest {
    static Population pln;

    @BeforeAll
    static void init()
    {
        pln = new Population();
    }

    @Test
    void printPopulation()
    {
        ArrayList<Population> population = new ArrayList<>();
        Population pn = new Population();
        pn.name = "Asia" ;
        pn.population = BigDecimal.valueOf(3705025);
        pn.urban = BigDecimal.valueOf(697604103);
        pn.urbanPercent = "18.83";
        pn.rural = BigDecimal.valueOf(300742159);
        pn.ruralPercent = "81.17";
        population.add(pn);
        pln.printPopulation(population);
    }

    @Test
    void printPopulationTestContainsNull()
    {
        ArrayList<Population> population = new ArrayList<>();
        population.add(null);
        pln.printPopulation(population);
    }

    @Test
    void printPopulationTestEmpty()
    {
        ArrayList<Population> population = new ArrayList<>();
        pln.printPopulation(population);
    }

    @Test
    void printWorldLanguages()
    {
        ArrayList<Population> population = new ArrayList<>();
        Population pn = new Population();
        pn.language = "Chinese" ;
        pn.population = BigDecimal.valueOf(1191843539);
        pn.percentage = "19.61";
        population.add(pn);
        pln.printPercentage(population);
    }

    @Test
    void printWorldLanguagesTestContainsNull()
    {
        ArrayList<Population> population = new ArrayList<>();
        population.add(null);
        pln.printPercentage(population);
    }

    @Test
    void printWorldLanguagesTestEmpty()
    {
        ArrayList<Population> population = new ArrayList<>();
        pln.printPercentage(population);
    }
}
