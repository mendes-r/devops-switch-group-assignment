
package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OutPersonDTOTest {

    @Test
    @DisplayName("Get main email")
    void getDescription() {
        //arrange

        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        String getMainMail= "maria@gmail.com";
        String getName = "Maria";
        String getFamilyId = "asdf";
        //act
        String firstResult = dto.getMainEmail();
        String secondResult = dto.getName();
        String thirdResult = dto.getFamilyID();
        //assert
        assertEquals(getMainMail,firstResult);
        assertEquals(getName,secondResult);
        assertEquals(getFamilyId,thirdResult);


    }

    @Test
    @DisplayName("Test Equals for the same dto")
    void testEqualsForTheSameDto() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(dto);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test Equals for the null case")
    void testEqualsForNull() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for the diferent objects")
    void testEqualsDiferentObject() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        BigDecimal number = new BigDecimal(10);
        //act
        boolean result = dto.equals(number);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for the dtos with diferent names")
    void testEqualsDiferentNames() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Miguel","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for dtos with different emails")
    void testEqualsDiferentMainEmails() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Maria","mariaSecond@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test Equals for dtos with different emails")
    void testEqualsDiferentFamilyIds() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Maria","mariaSecond@gmail.com","asdf2");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertFalse(result);
    }




    @Test
    @DisplayName("Test Equals for similar dtos")
    void testEqualsTheSimilarDtos() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }


    @Test
    void testHashCodeForSameDtos() {

        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    void testHashCodeForDiferentDtos() {

        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("MariaF","maria@gmail.com","asdf");
        //act
        int hashCodeDto = dto.hashCode();
        int hashCodeSecondDto = secondDto.hashCode();

        //assert
        assertNotEquals(hashCodeDto,hashCodeSecondDto);
    }

    @Test
    @DisplayName("Test Equals for similar dtos")
    void testEqualsTheSimilarDtosSameEmail() {
        //arrange
        OutPersonDTO dto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        OutPersonDTO secondDto = new OutPersonDTO("Maria","maria@gmail.com","asdf");
        //act
        boolean result = dto.equals(secondDto);
        //assert
        assertTrue(result);
    }


}
