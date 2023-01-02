package com.s14t284.demo.smokeproto.application.address;

import com.s14t284.demo.smokeproto.domain.address.AddressBook;

import java.util.Optional;

public interface AddressBookRepository {
    Optional<AddressBook> find(int id);
}
