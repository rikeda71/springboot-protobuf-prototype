package com.s14t284.demo.smokeproto.presenter;

import com.example.tutorial.protos.AddPersonRequest;
import com.example.tutorial.protos.AddPersonResponse;
import com.example.tutorial.protos.DeletePersonRequest;
import com.example.tutorial.protos.DeletePersonResponse;
import com.example.tutorial.protos.GetPersonRequest;
import com.example.tutorial.protos.GetPersonResponse;
import com.example.tutorial.protos.ResponseStatus;
import com.example.tutorial.protos.UpdatePersonRequest;
import com.example.tutorial.protos.UpdatePersonResponse;
import com.s14t284.demo.smokeproto.application.address.CrudPersonService;
import com.s14t284.demo.smokeproto.domain.address.Email;
import com.s14t284.demo.smokeproto.domain.address.Person;
import com.s14t284.demo.smokeproto.domain.address.PhoneNumber;
import com.s14t284.demo.smokeproto.domain.address.PhoneNumbers;
import com.s14t284.demo.smokeproto.domain.address.PhoneType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/person")
public class PersonController {

    final CrudPersonService crudPersonService;

    PersonController(CrudPersonService crudPersonService) {
        this.crudPersonService = crudPersonService;
    }


    @GetMapping
    GetPersonResponse get(GetPersonRequest request) {
        Person person = this.crudPersonService.find(request.getId());
        return person.createGetPersonResponse();
    }

    @PostMapping
    AddPersonResponse add(AddPersonRequest request) {
        com.example.tutorial.protos.Person rp = request.getPerson();
        Person person = Person.reconstruct(
                rp.getName(),
                rp.getId(),
                new Email(rp.getEmail()),
                new PhoneNumbers(rp.getPhonesList().stream()
                        .map(v -> new PhoneNumber(v.getNumber(), PhoneType.fromInteger(v.getTypeValue())))
                        .toList()),
                false
        );
        crudPersonService.insert(person);
        return AddPersonResponse.newBuilder()
                .setStatus(ResponseStatus.SUCCESS)
                .build();
    }

    @PatchMapping
    UpdatePersonResponse update(UpdatePersonRequest request) {
        com.example.tutorial.protos.Person rp = request.getPerson();
        Person person = Person.reconstruct(
                rp.getName(),
                rp.getId(),
                new Email(rp.getEmail()),
                new PhoneNumbers(rp.getPhonesList().stream()
                        .map(v -> new PhoneNumber(v.getNumber(), PhoneType.fromInteger(v.getTypeValue())))
                        .toList()),
                false
        );
        crudPersonService.update(person);
        return UpdatePersonResponse.newBuilder()
                .setStatus(ResponseStatus.SUCCESS)
                .build();
    }

    @DeleteMapping
    DeletePersonResponse delete(DeletePersonRequest request) {
        crudPersonService.delete(request.getId());
        return DeletePersonResponse.newBuilder()
                .setStatus(ResponseStatus.SUCCESS)
                .build();
    }
}
