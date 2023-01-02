package com.s14t284.demo.smokeproto.domain.address;

import com.example.tutorial.protos.GetPersonResponse;
import com.example.tutorial.protos.ResponseStatus;
import com.s14t284.demo.smokeproto.infrastructure.addres.PersonDto;
import com.s14t284.demo.smokeproto.infrastructure.addres.PhoneNumberDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 人物情報を表す Domain Entity
 * record でも表現できる
 */
@Getter
public class Person {

    /**
     * 名前
     */
    private final String name;

    /**
     * ユーザID
     */
    private final int id;

    /**
     * メールアドレス
     */
    private final Email email;

    /**
     * 電話番号
     */
    private final PhoneNumbers phones;

    /**
     * 削除済みかどうか
     */
    private final boolean isDeleted;

    /**
     * コンストラクタ
     */
    private Person(String name, int id, Email email, PhoneNumbers phones, boolean isDeleted) {
        // 名前の長さなどのバリデーションがあったらここで実施する
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("名前が不正です");
        }

        if (phones == null) {
            throw new IllegalArgumentException("電話番号が不正です");
        }

        this.name = name;
        // プロダクトによってはIDもValueObjectにする
        this.id = id;
        this.email = email;
        this.phones = phones;
        this.isDeleted = isDeleted;
    }

    /**
     * 人物情報を論理削除状態にする
     *
     * @return 論理削除済みの人物情報
     */
    public Person delete() {
        return new Person(name, id, email, phones, true);
    }

    /**
     * 初期化メソッド
     */
    private static Person create(String name, int id, Email email) {
        return new Person(name, id, email, new PhoneNumbers(new ArrayList<>()), false);
    }

    /**
     * 永続層からエンティティを再構築するために利用する初期化メソッド
     */
    public static Person reconstruct(String name, int id, Email email, PhoneNumbers phones, boolean isDeleted) {
        return new Person(name, id, email, phones, isDeleted);
    }

    /**
     * DB用の人物情報のエンティティを生成
     *
     * @return DB用のエンティティ
     */
    public PersonDto createPersonDto() {
        return new PersonDto(
                id,
                name,
                email.value(),
                isDeleted
        );
    }

    /**
     * DB用の電話番号情報のエンティティを生成
     *
     * @return DB用のエンティティ
     */
    public List<PhoneNumberDto> createPhoneNumberDtoList() {
        return phones.values().stream()
                .map(phone -> new PhoneNumberDto(id, 0, phone.number(), phone.type().ordinal(), isDeleted))
                .toList()
                ;
    }

    /**
     * 取得用レスポンスを生成
     *
     * @return 人物情報取得用レスポンス
     */
    public GetPersonResponse createGetPersonResponse() {
        return GetPersonResponse.newBuilder()
                .setPerson(com.example.tutorial.protos.Person.newBuilder()
                        .setName(name)
                        .setId(id)
                        .setEmail(email.value())
                        .addAllPhones(
                                phones.values().stream()
                                        .map(phoneNumber -> com.example.tutorial.protos.Person.PhoneNumber.newBuilder()
                                                .setNumber(phoneNumber.number())
                                                .setTypeValue(phoneNumber.type().ordinal())
                                                .build()
                                        )
                                        .toList()
                        )
                )
                .setStatus(ResponseStatus.SUCCESS)
                .build();
    }
}
