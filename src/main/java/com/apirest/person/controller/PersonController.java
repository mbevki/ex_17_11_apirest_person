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

    @GetMapping("/persons/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") long id){
        Person person = personService.getPersonById(id);

        if(person != null){
            return new ResponseEntity<>(person, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/persons/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person){
        Person _person = personService.updatePersonById(id, person);

        if(_person != null){
            return new ResponseEntity<>(personService.savePerson(_person), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/persons/{id}")
    public ResponseEntity<HttpStatus>  deletePersonById (@PathVariable("id") long id){
        try{
            personService.deletePersonById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/persons")
    public ResponseEntity<HttpStatus>  deleteAllPerson (){
        try{
            personService.deleteAllPerson();

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
