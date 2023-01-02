package com.s14t284.demo.smokeproto.domain.address;

import java.util.List;

/**
 * 電話番号一覧
 *
 * @param values 電話番号一覧
 */
public record PhoneNumbers(List<PhoneNumber> values) {
}
