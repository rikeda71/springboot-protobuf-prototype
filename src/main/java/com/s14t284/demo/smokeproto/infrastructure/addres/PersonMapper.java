package com.s14t284.demo.smokeproto.infrastructure.addres;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PersonMapper {

    @Select("""
            SELECT
                personId,
                name,
                email,
                isDeleted
            FROM
                PEOPLE
            WHERE personId = #{personId}
            AND isDeleted = 0
            """
    )
        // 下記のように書いて Domain Entity に直接マッピングすることができる
        // @ResultType(value = Person.class)
    PersonDto findById(int personId);

    @Select("""
            SELECT
                personId,
                name,
                email,
                isDeleted
            FROM
                PEOPLE
            WHERE personId = #{personId}
            AND isDeleted = 0
            FOR UPDATE WAIT 1
            """
    )
    PersonDto findByIdWithLock(int personId);

    @Insert("""
            INSERT INTO PERSON (
                personId,
                name,
                email,
                isDeleted
            ) VALUES (
                #{personDto.personId},
                #{personDto.name},
                #{personDto.email},
                0
            )
            """)
    void insert(PersonDto personDto);

    @Update("""
            UPDATE PERSON SET
                name  = #{personDto.name},
                email = #{personDto.email},
                isDeleted = #{personDto.isDeleted},
            WHERE
                personId = #{personDto.personId}
            """)
    void update(PersonDto personDto);
}
