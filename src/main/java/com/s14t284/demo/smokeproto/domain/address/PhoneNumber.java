package com.s14t284.demo.smokeproto.domain.address;

/**
 * 電話番号を表すValueObject
 *
 * @param number 番号
 * @param type   電話端末タイプ
 */
public record PhoneNumber(String number, PhoneType type) {
    public PhoneNumber {
        // 本来は正規表現で電話番号の検証を行う
        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("電話番号が不正です");
        }
    }
}
