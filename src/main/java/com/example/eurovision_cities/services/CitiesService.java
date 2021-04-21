package com.example.eurovision_cities.services;

import com.example.eurovision_cities.daos.CitiesRepository;
import com.example.eurovision_cities.exceptions.InternalException;
import com.example.eurovision_cities.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitiesService {

    private CitiesRepository citiesRepository;
    @Autowired
    public void setCitiesRepository(CitiesRepository citiesRepository){
        this.citiesRepository = citiesRepository;
    }

    public Page<City>getCitiesPage(Pageable pageable){
        Page<City> cityPage = citiesRepository.findAll(pageable);
        return cityPage;
    }

    public List<City> listCities(){
        List<City>cities = citiesRepository.findAll();
        return cities;
    }

    public List<Long>getLongestSequence() {
        List<City>cities =citiesRepository.findAll();
        /*
        We will return an error in case the list of cities on database is null or empty
         */
        if(cities==null||cities.isEmpty()){
            throw new InternalException("Missing ot empty list of cities");
        }
        cities = sortAlphabetically(cities);
        List<Long> biggestSequence = findSequence(getCitiesIds(cities));
        return biggestSequence;
    }
    /*
    Method that finds the different sequence for every number on the given list
    we end up with a "2D" Arraylist and then return the longest sequence found.
     */
    public List<Long> findSequence(List<Long>idList){
        if(idList==null||idList.isEmpty()){
            throw new InternalException("Missing ot empty list of numbers");
        }
        if(idList.size()==1){
            return idList;
        }

        List<List<Long>> results =initializeResultsList(idList.size());

        results.get(0).add(idList.get(0));
        for(int i=1;i<idList.size();i++){
            for(int j=0;j<i;j++){
                if(idList.get(j)<idList.get(i)&&results.get(j).size()>results.get(i).size()){
                    results.set(i,new ArrayList<>(results.get(j)));
                }
            }

            results.get(i).add(idList.get(i));
        }
        return getResultListForLongestSequence(results);
    }

    /*
   Sort cities alphabetically
    */
    public List<City> sortAlphabetically(List<City>cities){
        return cities.stream().sorted().collect(Collectors.toList());
    }
    /*
    return a list of Ids from the sorted cities list
     */
    public List<Long>getCitiesIds(List<City>cities){
        return cities.stream().map(City::getId).collect(Collectors.toList());
    }
    /*
    Create a list to add every solution for each of the ids on the given id list
     */
    private List<List<Long>> initializeResultsList(int size){
        List<List<Long>> result= new ArrayList<>();
        for(int i=0;i<size;i++){
            result.add(new ArrayList<>());
        }
        return result;
    }
    /*
    Find the longest sequence of the given results
     */
    public List<Long>getResultListForLongestSequence(List<List<Long>>results){
        return results.stream().max(Comparator.comparing(v->v.size())).get();
    }
}
