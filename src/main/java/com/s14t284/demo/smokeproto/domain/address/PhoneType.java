package com.s14t284.demo.smokeproto.domain.address;

/**
 * 電話端末タイプ
 */
public enum PhoneType {
    MOBILE(0),
    HOME(1),
    WORK(2);

    private final int type;

    PhoneType(int type) {
        this.type = type;
    }

    /**
     * int -> enum に変換
     *
     * @param type int型の電話番号タイプ
     * @return enum
     */
    public static PhoneType fromInteger(int type) {
        return switch (type) {
            case 0 -> MOBILE;
            case 1 -> HOME;
            case 2 -> WORK;
            default -> throw new IllegalArgumentException("PhoneTypeが不正です");
        };
    }

}
