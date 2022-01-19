package com.api.spring_restapi.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ValidationPhoneNumberTest {

    private ValidationPhoneNumber underTest;

    @BeforeEach
    void setUp(){
        underTest = new ValidationPhoneNumber();
    }

    @ParameterizedTest
    @ValueSource(strings ={"1234567890","0312342546"})
    void isShouldPhoneNumberValid(String phone){
        //when
        boolean isPhoneValid = underTest.test(phone);
        //then
        assertThat(isPhoneValid).isEqualTo(true);

    }

}