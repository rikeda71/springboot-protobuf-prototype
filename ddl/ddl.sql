-- 人物情報を管理するテーブル
CREATE TABLE PEOPLE
(
    personId  NUMBER(6),
    name      VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL,
    isDeleted NUMBER(1,0) DEFAULT 0,
    CONSTRAINT address_books_pk PRIMARY KEY (personId)
);

-- 電話番号を管理するテーブル
CREATE TABLE PHONE_NUMBERS
(
    phoneId     NUMBER(6) GENERATED ALWAYS AS IDENTITY,
    personId    NUMBER(6) NOT NULL,
    phoneNumber VARCHAR(11) NOT NULL,
    phoneType   NUMBER(2) NOT NULL,
    isDeleted   NUMBER(1,0) DEFAULT 0,
    CONSTRAINT phone_numbers_pk PRIMARY KEY (personId, phoneId)
);
