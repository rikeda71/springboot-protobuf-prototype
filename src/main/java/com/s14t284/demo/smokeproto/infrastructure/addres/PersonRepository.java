package com.s14t284.demo.smokeproto.infrastructure.addres;

import com.s14t284.demo.smokeproto.domain.address.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository implements com.s14t284.demo.smokeproto.application.address.PersonRepository {

    private final PersonMapper personMapper;
    private final PhoneNumberMapper phoneNumberMapper;

    PersonRepository(PersonMapper personMapper, PhoneNumberMapper phoneNumberMapper) {
        this.personMapper = personMapper;
        this.phoneNumberMapper = phoneNumberMapper;
    }

    @Override
    public Optional<Person> findByIdWithLock(int personId) {
        PersonDto person = personMapper.findByIdWithLock(personId);
        if (person == null) {
            return Optional.empty();
        }
        List<PhoneNumberDto> phoneNumbers = phoneNumberMapper.findByPersonId(personId);
        // MyBatis の Mapping 機能を使えばあえてこのような実装をする必要はない
        return Optional.of(
                Person.reconstruct(
                        person.name(),
                        person.personId(),
                        new Email(person.email()),
                        new PhoneNumbers(phoneNumbers.stream()
                                .map(pn -> new PhoneNumber(
                                        pn.phoneNumber(),
                                        PhoneType.fromInteger(pn.phoneType())
                                ))
                                .toList()),
                        person.isDeleted()
                )
        );
    }

    public Optional<Person> findById(int personId) {
        PersonDto person = personMapper.findById(personId);
        if (person == null) {
            return Optional.empty();
        }
        List<PhoneNumberDto> phoneNumbers = phoneNumberMapper.findByPersonId(personId);
        // MyBatis の Mapping 機能を使えばあえてこのような実装をする必要はない
        return Optional.of(
                Person.reconstruct(
                        person.name(),
                        person.personId(),
                        new Email(person.email()),
                        new PhoneNumbers(phoneNumbers.stream()
                                .map(pn -> new PhoneNumber(
                                        pn.phoneNumber(),
                                        PhoneType.fromInteger(pn.phoneType())
                                ))
                                .toList()),
                        person.isDeleted()
                )
        );
    }

    /**
     * 人物情報集約ごとDBに追加
     *
     * @param person 人物情報
     */
    public void insert(Person person) {
        PersonDto personDto = person.createPersonDto();
        List<PhoneNumberDto> phoneNumberDtoList = person.createPhoneNumberDtoList();
        personMapper.insert(personDto);
        // 本来は INSERT ALL を使うべき
        phoneNumberDtoList.forEach(phoneNumberMapper::insert);
    }

    /**
     * 人物情報集約ごとDB内のデータを更新
     *
     * @param person 人物情報
     */
    public void update(Person person) {
        PersonDto personDto = person.createPersonDto();
        List<PhoneNumberDto> phoneNumberDtoList = person.createPhoneNumberDtoList();
        personMapper.update(personDto);
        phoneNumberDtoList.forEach(phoneNumberMapper::update);
    }
}
