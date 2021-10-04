package switchtwentytwenty.project.dto.todomaindto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.domain.aggregate.category.Category;
import switchtwentytwenty.project.domain.aggregate.category.CategoryFactory;
import switchtwentytwenty.project.domain.share.designation.CategoryDesignation;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JpaToDomainLedgerDTOTest {

    @Test
    @DisplayName("Test to get ledgerID")
    void testToGetLedgerId() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO = new JpaToDomainLedgerDTO(ledgerID);
        //act
        LedgerID result = jpaToDomainLedgerDTO.getId();
        //assert
        assertEquals(ledgerID, result);
    }

    @Test
    @DisplayName("Test to get transaction list")
    void testToGetTransactionList() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        List<Transaction> transactionList = new ArrayList<>();
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO = new JpaToDomainLedgerDTO(ledgerID);
        //act
        List<Transaction> result = jpaToDomainLedgerDTO.getTransactionList();
        //assert
        assertEquals(transactionList, result);
    }

    @Test
    @DisplayName("HashCode test")
    void testEqualsHashCode() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        //act
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO1 = new JpaToDomainLedgerDTO(ledgerID1);
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO2 = new JpaToDomainLedgerDTO(ledgerID1);
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO3 = new JpaToDomainLedgerDTO(ledgerID2);
        //assert
        assertEquals(jpaToDomainLedgerDTO1, jpaToDomainLedgerDTO2);
        assertEquals(jpaToDomainLedgerDTO1.hashCode(), jpaToDomainLedgerDTO2.hashCode());
        assertNotEquals(jpaToDomainLedgerDTO1.hashCode(), jpaToDomainLedgerDTO3.hashCode());
    }

    @Test
    @DisplayName("Same JpaToDomainLedgerDTO")
    void sameJpaToDomainLedgerDTO() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        //act
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO1 = new JpaToDomainLedgerDTO(ledgerID1);
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO2 = new JpaToDomainLedgerDTO(ledgerID1);
        //assert
        assertEquals(jpaToDomainLedgerDTO1, jpaToDomainLedgerDTO2);
    }

    @Test
    @DisplayName("Not same JpaToDomainLedgerDTO")
    void notSameJpaToDomainLedgerDTO() {
        //arrange
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        //act
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO1 = new JpaToDomainLedgerDTO(ledgerID1);
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO2 = new JpaToDomainLedgerDTO(ledgerID2);
        //assert
        assertNotEquals(jpaToDomainLedgerDTO1, jpaToDomainLedgerDTO2);
    }

    @Test
    @DisplayName("Not equal objects")
    void jpaToDomainLedgerDTONotEqualToCategory() {
        //arrange
        String id = UUID.randomUUID().toString();
        CategoryID categoryID = new CategoryID(id);
        CategoryDesignation categoryDesignation = new CategoryDesignation("Food");
        Category category = CategoryFactory.create(categoryDesignation, categoryID, null);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO = new JpaToDomainLedgerDTO(ledgerID);
        //act
        boolean result = jpaToDomainLedgerDTO.equals(category);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same instance")
    void sameInstance() {
        //arrange
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        JpaToDomainLedgerDTO jpaToDomainLedgerDTO = new JpaToDomainLedgerDTO(ledgerID);
        //act
        JpaToDomainLedgerDTO sameJpaToDomainLedgerDTO = jpaToDomainLedgerDTO;
        //assert
        assertEquals(sameJpaToDomainLedgerDTO, jpaToDomainLedgerDTO);
    }
}