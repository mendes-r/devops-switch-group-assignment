package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InPersonDTOTest {

    @Test
    @DisplayName("Test constructor")
    void testConstructor() {
        //act
        InPersonDTO inPersonDTO = new InPersonDTO();
        //assert
        assertNotNull(inPersonDTO);
    }

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        String name = "Constantino";
        String birthDate = "2020-05-01";
        String vat = "123456789";
        String houseNumber = "25";
        String street = "Rua Nova";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4521-856";
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("225463859");

        InPersonDTO inPersonDTO = new InPersonDTO();
        inPersonDTO.setName(name);
        inPersonDTO.setBirthDate(birthDate);
        inPersonDTO.setVat(vat);
        inPersonDTO.setHouseNumber(houseNumber);
        inPersonDTO.setStreet(street);
        inPersonDTO.setCity(city);
        inPersonDTO.setCountry(country);
        inPersonDTO.setZipCode(zipCode);
        inPersonDTO.setPhoneNumbers(phoneNumbers);

        //act
        String resultName = inPersonDTO.getName();
        String resultBirthDate = inPersonDTO.getBirthDate();
        String resultVat = inPersonDTO.getVat();
        String resultHouseNumber = inPersonDTO.getHouseNumber();
        String resultStreet = inPersonDTO.getStreet();
        String resultCity = inPersonDTO.getCity();
        String resultCountry = inPersonDTO.getCountry();
        String resultZipCode = inPersonDTO.getZipCode();
        List<String> resultPhoneNumbers = inPersonDTO.getPhoneNumbers();
        //assert
        assertEquals(name, resultName);
        assertEquals(birthDate, resultBirthDate);
        assertEquals(vat, resultVat);
        assertEquals(houseNumber, resultHouseNumber);
        assertEquals(street, resultStreet);
        assertEquals(city, resultCity);
        assertEquals(country, resultCountry);
        assertEquals(zipCode, resultZipCode);
        assertEquals(phoneNumbers, resultPhoneNumbers);
    }
}