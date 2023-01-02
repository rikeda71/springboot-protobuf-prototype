package com.s14t284.demo.smokeproto.infrastructure.addres;

/**
 * DB上の人物情報を表すエンティティ
 *
 * @param personId  id
 * @param name      名前
 * @param email     メールアドレス
 * @param isDeleted 論理削除済みか
 */
public record PersonDto(int personId, String name, String email, boolean isDeleted) {
}
