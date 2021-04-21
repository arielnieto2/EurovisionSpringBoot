package com.example.eurovision_cities.controllers;

import com.example.eurovision_cities.models.City;
import com.example.eurovision_cities.models.CitiesPage;
import com.example.eurovision_cities.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    @Autowired
    private CitiesService citiesService;


    /*
    This method returns the full list of cities.
     */
    @CrossOrigin
    @GetMapping("/")
    public List<City> listCities(){
        List<City>cities =  citiesService.listCities();
        return cities;
        }

    /*
    this method gets the biggest ascending sequences of ids found on database.

     */
    @CrossOrigin
    @GetMapping("/getLongestSequence")
    public List<Long>getLongestSequence() {
    return citiesService.getLongestSequence();
    }



   /*
   return a page with the list of cities. This method has two params that indicates the page number and the size of the page.
   So we can check the max entries we have to get to fill a page and the page we want to show.
   The method return a Page object with the list of cities and pagination parameters.
    */
    @CrossOrigin
    @GetMapping("/queryByPage")
    private Page findCitiesPage(@RequestParam(value = "page",required = false)Integer page, @RequestParam("size") Integer size){
        if(page == null){
            page = 1;
        }
    Page<City> cityPage = citiesService.getCitiesPage(PageRequest.of(page,size));
    return cityPage;
    }
}
