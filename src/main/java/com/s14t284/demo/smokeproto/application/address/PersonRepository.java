package com.s14t284.demo.smokeproto.application.address;

import com.s14t284.demo.smokeproto.domain.address.Person;

import java.util.Optional;

public interface PersonRepository {
    Optional<Person> findByIdWithLock(int personId);

    Optional<Person> findById(int personId);

    void insert(Person person);

    void update(Person person);
}
