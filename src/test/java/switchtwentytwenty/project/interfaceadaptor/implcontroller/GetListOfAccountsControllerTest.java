package switchtwentytwenty.project.interfaceadaptor.implcontroller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.outdto.OutAccountDTO;
import switchtwentytwenty.project.interfaceadaptor.icontroller.IGetListOfAccountsController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GetListOfAccountsControllerTest {

    @Autowired
    IGetListOfAccountsController controller;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @Autowired
    IAccountService accountService;


    @BeforeEach
    public void before() {
        personRepository.deleteAll();

    }


    @Test
    void getListOfAccountsController() throws Exception {
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String personId = "bones@gmail.com";


        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", johnPhoneNumbers, personId, "Hamilton");
        familyAndMemberService.startFamily(dto);
        Person marty = personRepository.findByID(new Email(personId));

        MoneyValue initialAmountValue = new MoneyValue(new BigDecimal(initialAmount));
        AccountDesignation initialAccountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());
        Account account = AccountFactory.createCashAccount(accountID, initialAccountDesignation, initialAmountValue);
        marty.addAccountID(accountID);
        personRepository.save(marty);
        accountRepository.save(account);

        Person newPerson = personRepository.findByID(marty.getID());

        OutAccountDTO firstAccount = new OutAccountDTO(account.getID().toString(), account.getDesignation().toString());
        List<OutAccountDTO> expectedList = new ArrayList();
        expectedList.add(firstAccount);

        ResponseEntity expected = new ResponseEntity(expectedList, HttpStatus.OK);

        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(newPerson.getID().toString());

        //assert
        assertEquals(expected, result);
    }


    @Test
    void getListOfAccountsFromANonExistingPerson() throws Exception {
        //arrange
        double initialAmount = 50;
        String designation = "Cash";

        String personId = "bones@gmail.com";

        //act
        ResponseEntity<Object> result = controller.getListOfAccountsController(personId);
        int expected = 422;

        //assert
        assertEquals(expected, result.getStatusCodeValue());
    }


}