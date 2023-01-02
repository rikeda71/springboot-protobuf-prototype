package com.s14t284.demo.smokeproto.application.address;

import com.s14t284.demo.smokeproto.domain.address.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CrudPersonService {
    private final PersonRepository personRepository;

    public CrudPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void insert(Person person) {
        personRepository.insert(person);
    }

    @Transactional(readOnly = true)
    public Person find(int personId) {
        return personRepository.findById(personId)
                .orElseThrow(RuntimeException::new)
                ;
    }

    public void update(Person person) {
        personRepository.findByIdWithLock(person.getId())
                .orElseThrow(RuntimeException::new)
        ;
        personRepository.update(person);
    }

    public void delete(int personId) {
        Person person = personRepository.findByIdWithLock(personId)
                .orElseThrow(RuntimeException::new);
        personRepository.update(person.delete());
    }
}
