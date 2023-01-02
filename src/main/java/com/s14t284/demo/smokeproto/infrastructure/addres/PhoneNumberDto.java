package com.s14t284.demo.smokeproto.infrastructure.addres;

/**
 * DB上の電話番号を表すエンティティ
 * MyBatis を使うと必要なくなるが、わかりやすさのために定義
 *
 * @param personId
 * @param phoneId
 * @param phoneNumber
 * @param phoneType
 * @param isDeleted
 */
public record PhoneNumberDto(int personId, int phoneId, String phoneNumber, int phoneType, boolean isDeleted) {
}
