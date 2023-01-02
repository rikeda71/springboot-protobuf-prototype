package com.s14t284.demo.smokeproto.infrastructure.addres;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PhoneNumberMapper {

    @Select("""
            SELECT
                phoneId,
                phoneNumber,
                phoneType
            FROM
                PHONE_NUMBERS
            WHERE
                isDeleted = 0
                AND personId = #{personId}
            """)
    List<PhoneNumberDto> findByPersonId(int personId);

    @Insert("""
            INSERT INTO PHONE_NUMBERS (
                personId,
                phoneType,
                
                isDeleted
            ) VALUES (
                #{phoneNumberDto.personId},
                #{phoneNumberDto.phoneNumber},
                #{phoneNumberDto.phoneType}
                0
            )
            """)
    void insert(PhoneNumberDto phoneNumberDto);

    @Update("""
            UPDATE PHONE_NUMBERS SET
                phoneNumber = #{phoneNumberDto.phoneNumber},
                phoneType   = #{phoneNumberDto.phoneType},
                isDeleted   = #{phoneNumberDto.isDeleted},
            WHERE
                personId = #{phoneNumberDto.personId}
                phoneId  = #{phoneNumberDto.phoneId}
            """)
    void update(PhoneNumberDto phoneNumberDto);
}
