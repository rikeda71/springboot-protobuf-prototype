package com.s14t284.demo.smokeproto.domain.address;

/**
 * メールアドレスを表現する Value Object
 *
 * @param value
 */
public record Email(String value) {

    public Email {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("メールアドレスの文字列が不正です");
        }
    }
}
