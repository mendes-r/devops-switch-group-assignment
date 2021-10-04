package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.id.AccountIDList;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.dto.outdto.OutAccountDTO;
import switchtwentytwenty.project.dto.todomaindto.VOFamilyDTO;
import switchtwentytwenty.project.dto.todomaindto.VOPersonDTO;
import switchtwentytwenty.project.exception.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountServiceIT {

    @Autowired
    IAccountService accountService;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    IFamilyAndMemberService familyAndMemberService;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("create Family Cash Account: Successful case")
    void createFamilyCashAccount_SuccessfulCase() throws Exception {

        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email personID = new Email("oleolaf@gmail.com");
        double cashAmount = 10;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(familyID,new LedgerID(UUID.randomUUID()),personID,new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Rua Dragões", "23", "4123-677", "Porto", "Portugal");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VAT vat = new VAT("285437321");
        BirthDate birthDate = new BirthDate("1998-12-12");
        PersonName name = new PersonName("Ana");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,personID,family.getID(),ledgerID);
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);
        //act and assert
        accountService.createFamilyCashAccount(personID.toString(), cashAmount, designation);
        Family familySaved = familyRepository.findByID(familyID);
        AccountID cashAccountID = familySaved.getCashAccountID();
        Account account = accountRepository.findByID(cashAccountID);
        assertNotNull(account);
    }

    @Test
    @DisplayName("create Family Cash Account: invalid amount")
    void createFamilyCashAccount_InvalidAmount() throws Exception {
        //arrange
        UUID familyID = UUID.randomUUID();
        double cashAmount = -10;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Rua Dragões", "23", "4123-677", "Porto", "Portugal");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VAT vat = new VAT("285437321");
        BirthDate birthDate = new BirthDate("1998-12-12");
        PersonName name = new PersonName("Ana");
        Email admin = new Email("oleola@gmail.com");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,admin,family.getID(),ledgerID);
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        //act and assert
        assertThrows(AccountNotCreatedException.class, () -> accountService.createFamilyCashAccount(admin.toString(), cashAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: invalid designation")
    void createFamilyCashAccount_InvalidDesignation() throws Exception {
        //arrange
        UUID familyID = UUID.randomUUID();
        double cashAmount = 10;
        String designation = "c@sh";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        Address address = new Address("Rua Dragões", "23", "4123-677", "Porto", "Portugal");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VAT vat = new VAT("285437321");
        BirthDate birthDate = new BirthDate("1998-12-12");
        PersonName name = new PersonName("Ana");
        String admin = "oleola@gmail.com";
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,new Email(admin),family.getID(),ledgerID);
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> accountService.createFamilyCashAccount(admin, cashAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: invalid familyID")
    void createFamilyCashAccount_InvalidFamily() throws Exception {
        //arrange
        UUID familyID = UUID.randomUUID();
        double cashAmount = 10;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //act and assert
        assertThrows(NullPointerException.class, () -> accountService.createFamilyCashAccount(null, cashAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: add second cash account to family")
    void createFamilyCashAccount_FamilyAlreadyHasAAccount() throws Exception {
        //arrange
        UUID familyID = UUID.randomUUID();
        double cashAmount = 10;
        String designation = "cash";
        String secondDesignation = "vacations";
        Address address = new Address("Rua Dragões", "23", "4123-677", "Porto", "Portugal");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        VAT vat = new VAT("285437321");
        BirthDate birthDate = new BirthDate("1998-12-12");
        PersonName name = new PersonName("Ana");
        String admin = "oleola@gmail.com";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(admin),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,new Email(admin),family.getID(),ledgerID);
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);
        FamilyID familyIDSaved = new FamilyID(familyID);
        //act and assert
        accountService.createFamilyCashAccount(admin, cashAmount, designation);
        Family familySaved = familyRepository.findByID(familyIDSaved);
        AccountID cashAccountID = familySaved.getCashAccountID();
        Account account = accountRepository.findByID(cashAccountID);
        accountRepository.save(account);
        //assertNotNull(account);
        assertThrows(IllegalArgumentException.class,
                () -> accountService.createFamilyCashAccount(admin, cashAmount, secondDesignation));
    }

    @Test
    @DisplayName("create Personal Cash Account: Successful case")
    void createPersonalCashAccount_SuccessfulCase() throws Exception {
        //arrange
        String personEmail = "alanturing@hotmail.com";
        Email personID = new Email("alanturing@hotmail.com");
        double cashAmount = 10;
        String designation = "cash";

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,personID,familyID,new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        //act & assert
        accountService.createPersonalCashAccount(personEmail, cashAmount, designation);
        Person personSaved = personRepository.findByID(personID);
        AccountIDList accountIDList = personSaved.getAccountIDList();
        AccountID cashAccountID = accountIDList.getID(0);
        Account account = accountRepository.findByID(cashAccountID);
        assertNotNull(account);
    }

    @Test
    @DisplayName("create Family Cash Account: invalid amount")
    void createPersonalCashAccount_InvalidAmount() throws Exception {
        //arrange
        String personEmail = "alanturing@hotmail.com";
        Email personID = new Email("alanturing@hotmail.com");
        double cashAmount = -10;
        String designation = "cash";

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,personID,familyID,new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        //act and assert
        assertThrows(AccountNotCreatedException.class, () -> accountService.createPersonalCashAccount(personEmail, cashAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: invalid designation")
    void createPersonalCashAccount_InvalidDesignation() throws Exception {
        //arrange
        String personEmail = "alanturing@hotmail.com";
        Email personID = new Email("alanturing@hotmail.com");
        double cashAmount = 10;
        String designation = "c@sh";

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658541"));

        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name,birthDate,vat,address,telephoneNumberList,personID,familyID,new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> accountService.createPersonalCashAccount(personEmail, cashAmount, designation));

    }


    @Test
    @DisplayName("Check my Family Account Balance: Successful case")
    void getFamilyCashAccountBalance() throws Exception {
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 10;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        family.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        familyRepository.save(family);

        MoneyValue expected = new MoneyValue(new BigDecimal(10));
        //act
        MoneyValue result = accountService.getCashAccountBalance(familyAdminID, accountID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check my Personal Cash Account Balance: Successful case")
    void getFamilyMemberCashAccountBalance() throws Exception {
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 100;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);


        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        person.addAccountID(accountID);
        accountRepository.save(cashAccount);
        personRepository.save(person);

        MoneyValue expected = new MoneyValue(new BigDecimal(100));
        //act
        MoneyValue result = accountService.getCashAccountBalance(familyAdminID, accountID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check a family Member Cash Account Balance: Successful case")
    void getAnotherFamilyMemberCashAccountBalance() throws Exception{
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(familyAdminID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email personTwoID = new Email(familyAdminID);
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1995-01-22");
        PersonName nameTwo = new PersonName("Alan Turing");
        VAT vatTwo = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameTwo,birthDateTwo,vatTwo,addressTwo,telephoneNumberListTwo,personTwoID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember= PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        familyMember.addAccountID(accountID);
        this.accountRepository.save(cashAccount);
        personRepository.save(familyMember);

        MoneyValue expected = new MoneyValue(new BigDecimal(102));
        //act
        MoneyValue result = accountService.getCashAccountBalance(familyAdminID, accountID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check my Bank Account Balance: Unsuccessful case")
    void getBalanceFromANoneCashAccount() throws Exception {
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        Account bankSavingsAccount = AccountFactory.createBankAccount(accountID, accountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        person.addAccountID(accountID);
        this.accountRepository.save(bankSavingsAccount);
        this.personRepository.save(person);
        //act e assert
        assertThrows(IllegalArgumentException.class, () -> accountService.getCashAccountBalance(familyAdminID, accountID.toString()));
    }

    @Test
    @DisplayName("Check balance from a none existing account: Unsuccessful case")
    void getBalanceFromANoneExistingAccount() throws Exception {
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        AccountID accountID = new AccountID(UUID.randomUUID());

        //act e assert
        assertThrows(ElementNotFoundException.class, () -> accountService.getCashAccountBalance(familyAdminID, accountID.toString()));
    }

    @Test
    @DisplayName("Get cash account balance from a none existing person: Unsuccessful case")
    void getCashAccountBalanceFromANonExistingPerson() throws Exception {

        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 100;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email("oleola@gmail.com"),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        person.addAccountID(accountID);
        this.accountRepository.save(cashAccount);

        String otherPersonID = "oliJones@gmail.com";

        //act e assert
        assertThrows(ElementNotFoundException.class, () -> accountService.getCashAccountBalance(otherPersonID, accountID.toString()));
    }

    @Test
    @DisplayName("Check balance from a another Family Cash Account: Unsuccessful case")
    void getAnotherFamilyCashAccountBalance() throws Exception{
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(familyAdminID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        // Creating second family
        String secondFamilyAdminID = "jackJones@gmail.com";
        UUID secondFamilyID = UUID.randomUUID();
        VOFamilyDTO familyDTO2 = new VOFamilyDTO(new FamilyID(secondFamilyID),new LedgerID(UUID.randomUUID()),new Email(secondFamilyAdminID),new FamilyName("Jones"));
        Family secondFamily = FamilyFactory.create(familyDTO2);
        familyRepository.save(secondFamily);

        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email personTwoID = new Email(secondFamilyAdminID);
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1995-01-22");
        PersonName nameTwo = new PersonName("Alan Turing");
        VAT vatTwo = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameTwo,birthDateTwo,vatTwo,addressTwo,telephoneNumberListTwo,personTwoID,secondFamily.getID(),new LedgerID(UUID.randomUUID()));
        Person secondPerson= PersonFactory.create(otherDTO);
        personRepository.save(secondPerson);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        secondFamily.addAccountID(accountID);
        accountRepository.save(cashAccount);
        familyRepository.save(secondFamily);

        //act e assert
        assertThrows(IllegalArgumentException.class, () -> accountService.getCashAccountBalance(familyAdminID, accountID.toString()));


    }

    @Test
    @DisplayName("Check Cash Account Balance from another Person on another Family: Unsuccessful case")
    void getCashAccountBalanceFromAnotherPersonInFamily() throws Exception{
        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(familyAdminID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        // Creating second family
        String secondFamilyAdminID = "jackJones@gmail.com";
        UUID secondFamilyID = UUID.randomUUID();
        VOFamilyDTO familyDTO2 = new VOFamilyDTO(new FamilyID(secondFamilyID),new LedgerID(UUID.randomUUID()),new Email(secondFamilyAdminID),new FamilyName("Jones"));
        Family secondFamily = FamilyFactory.create(familyDTO2);
        familyRepository.save(secondFamily);


        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email personTwoID = new Email(secondFamilyAdminID);
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1995-01-22");
        PersonName nameTwo = new PersonName("Alan Turing");
        VAT vatTwo = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameTwo,birthDateTwo,vatTwo,addressTwo,telephoneNumberListTwo,personTwoID,secondFamily.getID(),new LedgerID(UUID.randomUUID()));
        Person secondPerson= PersonFactory.create(otherDTO);
        personRepository.save(secondPerson);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        secondPerson.addAccountID(accountID);
        this.accountRepository.save(cashAccount);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> accountService.getCashAccountBalance(familyAdminID, accountID.toString()));
    }

    @Test
    @DisplayName("Check Cash Account Balance when I am not the administrator: Unsuccessful case")
    void getCashAccountBalanceWhenIamNotTheAdmin() throws Exception {

        //arrange
        String familyAdminID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(familyAdminID),new FamilyName("Churchill"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email personID = new Email(familyAdminID);
        Address address1 = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate1 = new BirthDate("1995-01-22");
        PersonName name1 = new PersonName("Alan Turing");
        VAT vat1 = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(name1,birthDate1,vat1,address1,telephoneNumberList1,personID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        String personTwoId = "jackJones@gmail.com";
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email personTwoID = new Email(personTwoId);
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1995-01-22");
        PersonName nameTwo = new PersonName("Alan Turing");
        VAT vatTwo = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameTwo,birthDateTwo,vatTwo,addressTwo,telephoneNumberListTwo,personTwoID,family.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember = PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        familyMember.addAccountID(accountID);
        this.accountRepository.save(cashAccount);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> accountService.getCashAccountBalance(personTwoId, accountID.toString()));
    }

    @Test
    @DisplayName("Check Cash Account Balance when I am not the administrator: Unsuccessful case")
    void getCashAccountBalanceWhenIamNotTheFamilyAdministrator() throws Exception{
        //arrange
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",null,"martymcfly@gmail.com","Hamilton");
        familyAndMemberService.startFamily(dto);
        Person marty = personRepository.findByID(new Email("martymcfly@gmail.com"));

        PersonDTO georgeDTO = new PersonDTO.PersonDTOBuilder()
                .withName("George")
                .withEmail("george@hotmail.com")
                .withBirthDate("1968-01-22")
                .withFamilyID(marty.getFamilyID().toString())
                .withCity("Hill Valley")
                .withHouseNumber("25")
                .withStreet("Road of Dreams")
                .withZipCode("4125-886")
                .withVat("278113826")
                .withCountry("Porto")
                .build();
        familyAndMemberService.addFamilyMember(georgeDTO);

        String michaelID = "michael@hotmail.com";
        PersonDTO michaelDTO = new PersonDTO.PersonDTOBuilder()
                .withName("Michael")
                .withEmail(michaelID)
                .withBirthDate("1968-01-22")
                .withFamilyID(marty.getFamilyID().toString())
                .withCity("Hill Valley")
                .withHouseNumber("25")
                .withStreet("Road of Dreams")
                .withZipCode("4125-886")
                .withVat("241612985")
                .withCountry("Porto")
                .build();
        familyAndMemberService.addFamilyMember(michaelDTO);
        Person michael = personRepository.findByID(new Email(michaelID));

        MoneyValue amountValue = new MoneyValue(new BigDecimal(10));
        AccountDesignation accountDesignation = new AccountDesignation("MICHAEL ACCOUNT");
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount michaelCashAccount = (CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        michael.addAccountID(accountID);
        this.accountRepository.save(michaelCashAccount);
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> accountService.getCashAccountBalance(michaelID, accountID.toString()));
    }

    @Test
    @DisplayName("Check Cash Account Balance when I am not the administrator: Unsuccessful case")
    void getCashAccountBalanceFromARelative() throws Exception {
        //arrange
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton","1904-12-01","213025086","14","Logical Street",
                "Porto","Portugal","2100-345",null,"martymcfly@gmail.com","Hamilton");
        familyAndMemberService.startFamily(dto);
        Person marty = personRepository.findByID(new Email("martymcfly@gmail.com"));

        PersonDTO georgeDTO = new PersonDTO.PersonDTOBuilder()
                .withName("George")
                .withEmail("george@hotmail.com")
                .withBirthDate("1968-01-22")
                .withFamilyID(marty.getFamilyID().toString())
                .withCity("Hill Valley")
                .withHouseNumber("25")
                .withStreet("Road of Dreams")
                .withZipCode("4125-886")
                .withVat("278113826")
                .withCountry("Porto")
                .build();
        familyAndMemberService.addFamilyMember(georgeDTO);

        PersonDTO michaelDTO = new PersonDTO.PersonDTOBuilder()
                .withName("Michael")
                .withEmail("michael@hotmail.com")
                .withBirthDate("1968-01-22")
                .withFamilyID(marty.getFamilyID().toString())
                .withCity("Hill Valley")
                .withHouseNumber("25")
                .withStreet("Road of Dreams")
                .withZipCode("4125-886")
                .withVat("241612985")
                .withCountry("Porto")
                .build();
        familyAndMemberService.addFamilyMember(michaelDTO);
        Person michael = personRepository.findByID(new Email("michael@hotmail.com"));

        MoneyValue amountValue = new MoneyValue(new BigDecimal(10));
        AccountDesignation accountDesignation = new AccountDesignation("michael cash account");
        AccountID accountID = new AccountID(UUID.randomUUID());

        CashAccount michaelCashAccount =(CashAccount) AccountFactory.createCashAccount(accountID, accountDesignation,amountValue);
        michael.addAccountID(accountID);
        this.personRepository.save(michael);
        this.accountRepository.save(michaelCashAccount);

        MoneyValue expected = new MoneyValue(new BigDecimal(10));
        //act
        MoneyValue result = accountService.getCashAccountBalance(marty.getID().toString(), accountID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check a child Cash Account Balance: Successful case")
    void checkChildCashAccountBalance() throws Exception {
        //arrange
        String parentID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(parentID),new FamilyName("Churchill"));
        Family familyParentChild = FamilyFactory.create(familyDTO);
        familyRepository.save(familyParentChild);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email dadID = new Email(parentID);
        Address addressDad = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateDad = new BirthDate("1995-01-22");
        PersonName nameDad = new PersonName("Alan Turing");
        VAT vatDad = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(nameDad,birthDateDad,vatDad,addressDad,telephoneNumberList1,dadID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        String childID = "jackJones@gmail.com";
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email kidID = new Email(childID);
        Address addressKid = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateKid = new BirthDate("1995-01-05");
        PersonName nameKid = new PersonName("Alan Turing");
        VAT vatKid = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameKid,birthDateKid,vatKid,addressKid,telephoneNumberListTwo,kidID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember= PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        //creating a new relationship based on the relationType parentDenomination.
        String parentDenomination = Constants.PARENT;
        familyAndMemberService.createFamilyRelation(parentID, childID, familyID.toString(), parentDenomination);

        //Creating child a new CashAccount with a initialAmount of 10.5 euros.
        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        CashAccount cashAccount =(CashAccount) AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        familyMember.addAccountID(accountID2);
        accountRepository.save(cashAccount);
        String id2 = cashAccount.getID().toString();
        //arrange

        MoneyValue expected = new MoneyValue(new BigDecimal(cashAmount));
        MoneyValue result = accountService.checkChildCashAccountBalance(parentID, childID, id2);
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check a child Cash Account Balance: Unsuccessful case, no relationship found")
    void checkChildCashAccountBalance_NoRelationshipFound() throws Exception {

        //arrange
        String parentID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(parentID),new FamilyName("Churchill"));
        Family familyParentChild = FamilyFactory.create(familyDTO);
        familyRepository.save(familyParentChild);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email dadID = new Email(parentID);
        Address addressDad = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateDad = new BirthDate("1995-01-22");
        PersonName nameDad = new PersonName("Alan Turing");
        VAT vatDad = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(nameDad,birthDateDad,vatDad,addressDad,telephoneNumberList1,dadID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        String childID = "jackJones@gmail.com";
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email kidID = new Email(childID);
        Address addressKid = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateKid = new BirthDate("1995-01-22");
        PersonName nameKid = new PersonName("Alan Turing");
        VAT vatKid = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameKid,birthDateKid,vatKid,addressKid,telephoneNumberListTwo,kidID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember= PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        //Creating child a new CashAccount with a initialAmount of 10.5 euros.
        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        familyMember.addAccountID(accountID2);
        accountRepository.save(cashAccount);
        String id2 = cashAccount.getID().toString();
        //arrange and assert

        assertThrows(IllegalArgumentException.class, () -> accountService.checkChildCashAccountBalance(parentID, childID, id2));
    }

    @Test
    @DisplayName("Check a child Cash Account Balance: Cash Account does not exist")
    void checkChildCashAccountBalance_CashAccountDoesNotExist() throws Exception {

        //arrange
        String parentID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(parentID),new FamilyName("Churchill"));
        Family familyParentChild = FamilyFactory.create(familyDTO);
        familyRepository.save(familyParentChild);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email dadID = new Email(parentID);
        Address addressDad = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateDad = new BirthDate("1995-01-22");
        PersonName nameDad = new PersonName("Alan Turing");
        VAT vatDad = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(nameDad,birthDateDad,vatDad,addressDad,telephoneNumberList1,dadID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        String childID = "jackJones@gmail.com";
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email kidID = new Email(childID);
        Address addressKid = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateKid = new BirthDate("1995-01-22");
        PersonName nameKid = new PersonName("Alan Turing");
        VAT vatKid = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameKid,birthDateKid,vatKid,addressKid,telephoneNumberListTwo,kidID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember= PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        //creating a new relationship based on the relationType parentDenomination.
        String parentDenomination = Constants.PARENT;
        familyAndMemberService.createFamilyRelation(parentID, childID, familyID.toString(), parentDenomination);

        //Creating child a new CashAccount with a initialAmount of 10.5 euros.
        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        familyMember.addAccountID(accountID2);
        accountRepository.save(cashAccount);
        String id2 = "fakeID";

        //assert
        assertThrows(IllegalArgumentException.class, () -> accountService.checkChildCashAccountBalance(parentID, childID, id2));
    }

    @Test

    @DisplayName("Check a child Cash Account Balance: The Account is not a Cash Account")
    void checkChildCashAccountBalance_NotACashAccount() throws Exception {
        //arrange
        String parentID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String designation = "cash";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(parentID),new FamilyName("Churchill"));
        Family familyParentChild = FamilyFactory.create(familyDTO);
        familyRepository.save(familyParentChild);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email dadID = new Email(parentID);
        Address addressDad = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateDad = new BirthDate("1995-01-22");
        PersonName nameDad = new PersonName("Alan Turing");
        VAT vatDad = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(nameDad,birthDateDad,vatDad,addressDad,telephoneNumberList1,dadID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        String childID = "jackJones@gmail.com";
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberListTwo.add(new TelephoneNumber("225658541"));
        Email kidID = new Email(childID);
        Address addressKid = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateKid = new BirthDate("1995-01-22");
        PersonName nameKid = new PersonName("Alan Turing");
        VAT vatKid = new VAT("123456789");

        VOPersonDTO otherDTO = new VOPersonDTO(nameKid,birthDateKid,vatKid,addressKid,telephoneNumberListTwo,kidID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person familyMember= PersonFactory.create(otherDTO);
        personRepository.save(familyMember);

        //creating a new relationship based on the relationType parentDenomination.
        String parentDenomination = Constants.PARENT;
        familyAndMemberService.createFamilyRelation(parentID, childID, familyID.toString(), parentDenomination);

        //Creating child a new CashAccount with a initialAmount of 10.5 euros.
        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(designation);
        AccountID accountID2 = new AccountID(UUID.randomUUID());
        AccountID bankSavingsAccountID = new AccountID(UUID.randomUUID());

        Account bankSavingsAccount = AccountFactory.createBankAccount(bankSavingsAccountID, accountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        String notCashAccountID = bankSavingsAccount.getID().toString();

        familyMember.addAccountID(accountID2);
        familyMember.addAccountID(bankSavingsAccountID);
        accountRepository.save(cashAccount);

        //assert
        assertThrows(ElementNotFoundException.class, () -> accountService.checkChildCashAccountBalance(parentID, childID, notCashAccountID));
    }


    @Test

    @DisplayName("Obtain the list of accounts from a person")
    void getAccountList() throws Exception {
        //arrange
        String parentID = "oleola@gmail.com";
        UUID familyID = UUID.randomUUID();
        double cashAmount = 102;
        String cashDesignation = "cash";
        String bankDesignation = "bank";
        VOFamilyDTO familyDTO = new VOFamilyDTO(new FamilyID(familyID),new LedgerID(UUID.randomUUID()),new Email(parentID),new FamilyName("Churchill"));
        Family familyParentChild = FamilyFactory.create(familyDTO);
        familyRepository.save(familyParentChild);

        TelephoneNumberList telephoneNumberList1 = new TelephoneNumberList();
        telephoneNumberList1.add(new TelephoneNumber("225658541"));
        Email dadID = new Email(parentID);
        Address addressDad = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateDad = new BirthDate("1995-01-22");
        PersonName nameDad = new PersonName("Alan Turing");
        VAT vatDad = new VAT("123456789");

        VOPersonDTO voPersonDTO = new VOPersonDTO(nameDad,birthDateDad,vatDad,addressDad,telephoneNumberList1,dadID,familyParentChild.getID(),new LedgerID(UUID.randomUUID()));
        Person person= PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        //Creating  new CashAccount with a initialAmount of 10.5 euros.
        MoneyValue amountValue = new MoneyValue(new BigDecimal(cashAmount));
        AccountDesignation accountDesignation = new AccountDesignation(cashDesignation);
        AccountDesignation bankAccountDesignation = new AccountDesignation(bankDesignation);

        AccountID cashAccountID = new AccountID(UUID.randomUUID());
        AccountID bankSavingsAccountID = new AccountID(UUID.randomUUID());

        Account bankSavingsAccount = AccountFactory.createBankAccount(bankSavingsAccountID, bankAccountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(cashAccountID, accountDesignation,amountValue);


        person.addAccountID(cashAccountID);
        person.addAccountID(bankSavingsAccountID);
        personRepository.save(person);
        accountRepository.save(cashAccount);
        accountRepository.save(bankSavingsAccount);

        OutAccountDTO firstAccount = new OutAccountDTO(cashAccountID.toString(),"Cash");
        OutAccountDTO secondAccount = new OutAccountDTO(bankSavingsAccountID.toString(),"Bank");

        List<OutAccountDTO> expectedlist = new ArrayList();
        expectedlist.add(firstAccount);
        expectedlist.add(secondAccount);
        Person newPerson = personRepository.findByID(dadID);



        //act
        List <OutAccountDTO> resultList = accountService.getListOfAccounts(newPerson.getID().toString());

        //assert
        assertTrue(newPerson.isMyAccount(cashAccountID));
        assertTrue(newPerson.isMyAccount(bankSavingsAccountID));
        assertEquals(expectedlist,resultList);


    }

}








