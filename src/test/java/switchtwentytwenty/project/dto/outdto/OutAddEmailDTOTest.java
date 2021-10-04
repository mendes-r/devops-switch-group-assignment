package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OutAddEmailDTOTest {

    @Test
    @DisplayName("Create a AddEmailDTO successfully")
    void createAOutAddEmailDTOSuccess() {
        //arrange
        String inputedEmail = "tiagoSecond@gmail.com";
        String personID = "tiagoFirst@gmail.com";
        //act
        OutAddEmailDTO dto = new OutAddEmailDTO(personID,inputedEmail);
        //assert
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Get email from dto successfully")
    void getInputEmail() {

        String personId = "tiago@gmail.com";
        String inputedEmail = "tiagoSecond@gmail.com";

        String expectedPersonId = "tiago@gmail.com";
        String expectedInputedEmail = "tiagoSecond@gmail.com";


        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);

        String resultPersonId = dto.getPersonID();
        String resultInputedEmail = dto.getInputEmail();
        //assert
        assertEquals(expectedInputedEmail, resultInputedEmail);
        assertEquals(expectedPersonId,resultPersonId);

    }

    @Test
    void getInputEmailTest() {

        String expected = "tiagoTwo@gmail.com";
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);
        String result = dto.getInputEmail();
        assertNotEquals(expected, result);

    }

    @Test
    @DisplayName("Verify two equal dto successfully")
    void equalsSameDToTest() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test equals with null- successfully")
    void equalsNullTest() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify two equal dto successfully")
    void testEqualsForDiferentDTos() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);
        String secondInputedEmail = "tiagoReS@gmail.com";
        String secondPersonId = "tiagoRe@gmail.com";

        OutAddEmailDTO secondDto = new OutAddEmailDTO(secondPersonId,secondInputedEmail);

        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify equals for diferent objects - successfully")
    void testEqualsForDiferentObjects() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);

        BigDecimal number = new BigDecimal(10);

        //act
        boolean result = dto.equals(number);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Verify two similar dto - successfully")
    void testEqualsForSimilarDTo() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);

        String secondInputedEmail = "tiagoR@gmail.com";
        String secondPersonId = "tiago@gmail.com";

        OutAddEmailDTO secondDto = new OutAddEmailDTO(secondPersonId,secondInputedEmail);
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Verify hash code for different dto - successfully")
    void testHashCodeForDifferentDTo() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);

        String secondInputedEmail = "tiagoRes@gmail.com";
        String secondPersonId = "tiagoRe@gmail.com";

        OutAddEmailDTO secondDto = new OutAddEmailDTO(secondPersonId,secondInputedEmail);
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertNotEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    @DisplayName("Verify hash code for similar dto - successfully")
    void testHashCodeForSimilarDTo() {

        //arrange
        String inputedEmail = "tiagoR@gmail.com";
        String personId = "tiago@gmail.com";

        OutAddEmailDTO dto = new OutAddEmailDTO(personId,inputedEmail);

        String secondInputedEmail = "tiagoR@gmail.com";
        String secondPersonId = "tiago@gmail.com";

        OutAddEmailDTO secondDto = new OutAddEmailDTO(secondPersonId,secondInputedEmail);
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto,hashCodeSecondDto);
    }











}