package com.apirest.person.controller;

import com.apirest.person.model.Person;
import com.apirest.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<Person> addPerson(@RequestBody Person person){
        try {
            Person _person = personService.savePerson(new Person(person.getFirstName(), person.getLastName(), person.getAge(), person.getEmail(), person.getPhoneNum(), person.getDateOfBirth()));

            return new ResponseEntity<>(_person, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getAllPersonsNames(@RequestParam(required = false) String name){
        try {
            List<Person> persons = personService.getAllPersonsNames();

            if(name == null){
                persons = personService.getAllPersonsNames();
            }else {
                persons = personService.getPersonByName(name);
            }

            if(persons.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(persons, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
