package switchtwentytwenty.project.applicationservice.irepository;

import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.exception.*;

public interface IPersonRepository {

    /**
     * Find all Persons from a given family.
     *
     * @param familyID - family ID.
     * @return list of family members
     */
    PersonList findByFamilyID(FamilyID familyID) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException;

    /**
     * Find element in PersonRepository by ID.
     *
     * @param id - Account's identification
     * @return element with same ID
     */
    Person findByID(Email id) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException;

    /**
     * Adds an Person instance to the repository.
     *
     * @param entity - Entity instance
     */
    void save(Person entity);

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    boolean existsByID(Email id);

    /**
     * Verifies if person with familyID and Vat exists.
     *
     * @param familyID - person's familyID.
     * @param vat - person's Vat.
     * @return true if person with familyID and Vat exists.
     */
    boolean existsByFamilyIDAndVat(FamilyID familyID, VAT vat);

    /**
     * Delete all data from repository
     */
    void deleteAll();

}
