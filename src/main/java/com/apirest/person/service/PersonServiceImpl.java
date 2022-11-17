package com.apirest.person.service;

import com.apirest.person.model.Person;
import com.apirest.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        Person _person = personRepository.save(person);

        return _person;
    }

    @Override
    public List<Person> getAllPersonsNames() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> getPersonByName(String name) {
        return personRepository.findByFirstNameContaining(name);
    }
}
