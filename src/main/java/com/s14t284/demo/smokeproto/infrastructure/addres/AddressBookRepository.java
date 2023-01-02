package com.s14t284.demo.smokeproto.infrastructure.addres;

import com.s14t284.demo.smokeproto.domain.address.AddressBook;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressBookRepository implements com.s14t284.demo.smokeproto.application.address.AddressBookRepository {

    @Override
    public Optional<AddressBook> find(int id) {
        return Optional.empty();
    }
}
