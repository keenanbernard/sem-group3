package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class CapitalTest {

    static Capital cpt;

    @BeforeAll
    static void init()
    {
        cpt = new Capital();
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
