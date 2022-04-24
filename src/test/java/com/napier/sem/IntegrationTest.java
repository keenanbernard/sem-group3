package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {

    static Country ctry;

    @BeforeAll
    static void init()
    {
        ctry = new Country();
    }

    @Test
    void testGetCountries()
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

        assertEquals(ctr.code, "BLZ");
        assertEquals(ctr.name, "Belize");
        assertEquals(ctr.continent, "North America");
        assertEquals(ctr.region, "Central America");
        assertEquals(ctr.population, 241000);
        assertEquals(ctr.capital, 185);
    }

}
