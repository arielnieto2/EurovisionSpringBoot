package com.example.eurovision_cities.services;


import com.example.eurovision_cities.exceptions.InternalException;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CitiesServiceTest {

    @Test
    public void findSequence() {
        CitiesService citiesService = new CitiesService();
        /*Given a list arranged in ascending order
        given [67,157,255,262]
        expected [67,157,255,262]
         */
        List<Long>ascendingOrderlist = new ArrayList<Long>(Arrays.asList(67L,157L,255L,262L));
        assertEquals(ascendingOrderlist,citiesService.findSequence(ascendingOrderlist));

        /*
        Given a list arranged in descending order
        given [262,255,157,67]
        expected [262]
         */
        List<Long>descendingOrderlist = new ArrayList<Long>(Arrays.asList(262L,255L,157L,67L));
        List<Long>expectedDescending = new ArrayList<Long>(Collections.singleton(262L));
        assertEquals(expectedDescending,citiesService.findSequence(descendingOrderlist));

        /*
        list of random numbers in any order
        given [67,262,255,4,300]
        expected [67,262,300]
         */
        List<Long>listwithoutOrder = new ArrayList<Long>(Arrays.asList(67L,262L,255L,4L,300L));
        List<Long>expectedwithoutOrder = new ArrayList<Long>(Arrays.asList(67L,262L,300L));
        assertEquals(expectedwithoutOrder,citiesService.findSequence(listwithoutOrder));

        /*
        List with repited values
        given [67,157,67,262,351]
        expected [67,157,262,351]
         */
        List<Long>listwithRepetition = new ArrayList<Long>(Arrays.asList(67L,157L,67L,262L,351L));
        List<Long>expectedwithRepetition = new ArrayList<Long>(Arrays.asList(67L,157L,262L,351L));
        assertEquals(expectedwithRepetition,citiesService.findSequence(listwithRepetition));

        /*
        List with only one value
        given [262]
        expected [262]
         */
        List<Long>oneNumberList = new ArrayList<Long>(Collections.singleton(262L));
        assertEquals(oneNumberList,citiesService.findSequence(oneNumberList));

        //empty List
        assertThrows(InternalException.class,()->citiesService.findSequence(new ArrayList<Long>()),"Missing ot empty list of numbers");

        //null List
        assertThrows(InternalException.class,()->citiesService.findSequence(null),"Missing ot empty list of numbers");
    }

}