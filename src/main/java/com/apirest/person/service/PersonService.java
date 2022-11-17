package com.apirest.person.service;

import com.apirest.person.model.Person;

import java.util.List;

public interface PersonService {
    Person savePerson(Person person);
    List<Person> getAllPersonsNames();
    List<Person> getPersonByName(String name);
}
