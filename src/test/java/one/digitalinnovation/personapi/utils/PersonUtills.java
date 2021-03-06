package one.digitalinnovation.personapi.utils;

import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.dto.request.PhoneDTO;
import one.digitalinnovation.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtills {

    private static final String FIRST_NAME = "Artur";
    private static final String LAST_NAME = "Logan";
    private static final String CPF_NUMBER = "345.263.123-30";
    private static final long PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.now();

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .phones(Collections.singletonList(PhoneUtills.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity(){
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPF_NUMBER)
                .phones(Collections.singletonList(PhoneUtills.createFakeEntity()))
                .build();
    }
}
