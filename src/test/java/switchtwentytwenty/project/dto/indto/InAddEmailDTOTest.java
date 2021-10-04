package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.dto.indto.InAddEmailDTO;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class InAddEmailDTOTest {

    @Test
    @DisplayName("Test dto and lombok getters  - successful case")
    void createDTOandGets() {
        //arrange

        String emailToAddExpected = "tiagoSecond@gmail.com";

        //act
        InAddEmailDTO dto = new InAddEmailDTO( "tiagoSecond@gmail.com");


        String emailToaddResult = dto.getEmailToAdd();

        //assert
        assertNotNull(dto);

        assertEquals(emailToaddResult, emailToAddExpected);

    }

    @Test
    @DisplayName("Test equals  - successful case")
    void testEquals() {
        //arrange
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");
        InAddEmailDTO secondDto = new InAddEmailDTO("tiago@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals  - successful case")
    void testEqualsWithSameDTO() {
        //arrange
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test equals with different person  - Unsuccessful case")
    void testDiferentDtos() {
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");

        InAddEmailDTO secondDto = new InAddEmailDTO("tiago2@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with different person  - Unsuccessful case")
    void testDiferentPersonIdOnDtos() {
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");

        InAddEmailDTO secondDto = new InAddEmailDTO("tiagoTT@gmail.com");

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals with a null  - Unsuccessful case")
    void testEqualsOnNullDto() {
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals on diferent objects  - successful case")
    void testEqualsOnADifferentObject() {
        //arrange
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");

        BigDecimal n = new BigDecimal(12);

        //act
        boolean result = dto.equals(n);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test same hash code case  - successful case")
    void testHashCodeOnDtos() {
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");

        InAddEmailDTO secondDto = new InAddEmailDTO("tiago@gmail.com");
        //act
        int dtoHashCode = dto.hashCode();
        int secondDtoHashCode = secondDto.hashCode();
        //assert
        assertEquals(dtoHashCode, secondDtoHashCode);
    }

    @Test
    @DisplayName("Test same hash code case  - Unsuccessful case")
    void testDiferentHashCodeOnDtos() {
        //arrange
        InAddEmailDTO dto = new InAddEmailDTO("tiago@gmail.com");
        InAddEmailDTO secondDto = new InAddEmailDTO("tiagoTT@gmail.com");
        //act
        int dtoHashCode = dto.hashCode();
        int secondDtoHashCode = secondDto.hashCode();
        //assert
        assertNotEquals(dtoHashCode, secondDtoHashCode);
    }


}