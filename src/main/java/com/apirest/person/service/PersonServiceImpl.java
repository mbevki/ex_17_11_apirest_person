package com.apirest.person.service;

import com.apirest.person.model.Person;
import com.apirest.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Person getPersonById(long id) {
        Optional<Person> personData = personRepository.findById(id);

        return personData.orElse(null);
    }

    @Override
    public Person updatePersonById(long id, Person person) {

        Person personData= getPersonById(id);

        if(personData != null){
            personData.setFirstName(person.getFirstName());
            personData.setLastName(person.getLastName());
            personData.setAge(person.getAge());
            personData.setEmail(person.getEmail());
            personData.setPhoneNum(person.getPhoneNum());
            personData.setDateOfBirth(person.getDateOfBirth());

            return personData;
        }

        return null;
    }

    @Override
    public void deletePersonById(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public void deleteAllPerson() {
        personRepository.deleteAll();
    }
}
