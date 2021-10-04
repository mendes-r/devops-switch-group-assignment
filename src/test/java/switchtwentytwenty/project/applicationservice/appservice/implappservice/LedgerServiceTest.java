package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LedgerServiceTest {

    @InjectMocks
    LedgerService ledgerServiceMock;
    @Mock
    Person personMock;
    @Mock
    PersonRepository personRepositoryMock;

    @Test
    @DisplayName("Get list of movements between dates: invalid account ID")
    void getListMovementsInvalidAccountID() throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException {
        //arrange
        String personID = "margaret_hamilton@gmail.com";
        String accountID = UUID.randomUUID().toString();
        String startDate = "2020-12-12";
        String endDate = "2021-01-30";
        //arrange Mock
        Mockito.when(personRepositoryMock.findByID(new Email(personID))).thenReturn(personMock);
        Mockito.when(personMock.isMyAccount(new AccountID(UUID.fromString(accountID)))).thenReturn(false);
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> ledgerServiceMock.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate));
    }
}
