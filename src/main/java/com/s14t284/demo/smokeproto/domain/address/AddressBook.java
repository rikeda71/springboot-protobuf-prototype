package com.s14t284.demo.smokeproto.domain.address;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

/**
 * アドレス帳を表す Domain Entity
 * static method だけにしたら record で表現できる
 */
@Getter
public class AddressBook {

    /**
     * アドレス帳ID
     */
    private final int addressId;

    /**
     * 人物情報一覧
     */
    private final List<Person> people;

    public AddressBook(List<Person> people) {
        this.people = Optional.ofNullable(people).orElseThrow();
    }

    /**
     * 人物情報を追加する
     *
     * @param people 追加する人物情報
     * @return 人物情報を追加したエンティティ
     */
    public AddressBook addPerson(List<Person> people) {
        if (people == null) {
            throw new IllegalArgumentException("追加したい人物情報が不正です");
        }
        this.people.addAll(people);
        return new AddressBook(this.people);
    }

}
