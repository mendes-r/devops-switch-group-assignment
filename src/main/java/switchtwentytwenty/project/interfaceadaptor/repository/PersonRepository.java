package switchtwentytwenty.project.interfaceadaptor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.assembler.PersonDomainDataAssembler;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.dto.todomaindto.JpaToDomainPersonDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.jpa.PersonJPARepository;

import java.util.Optional;

@Service
public class PersonRepository implements IPersonRepository {

    @Autowired
    PersonJPARepository repository;
    @Autowired
    PersonDomainDataAssembler assembler;

    /**
     * Adds an Person instance to the repository.
     *
     * @param person - person instance
     */
    public void save(Person person) {
        PersonJPA personJPA = assembler.toData(person);
        repository.save(personJPA);
    }

    /**
     * Verifies if the id exists in the repository.
     *
     * @param id - entity's id
     * @return false, if id isn't used in the app
     */
    public boolean existsByID(Email id) {
        return this.repository.existsById(id.toString());
    }

    /**
     * Find element in PersonRepository by ID.
     *
     * @param id - Account's identification
     * @return element with same ID
     */
    @Transactional
    public Person findByID(Email id) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException {
        Optional<PersonJPA> personJPA = this.repository.findById(id.toString());
        if (!personJPA.isPresent()) {
            throw new ElementNotFoundException("Person not found");
        }
        JpaToDomainPersonDTO dto = assembler.toDomain(personJPA.get());
        return PersonFactory.create(dto);
    }

    /**
     * Find all Persons from a given family.
     *
     * @param familyID - family ID.
     * @return list of family members
     */
    @Transactional
    public PersonList findByFamilyID(FamilyID familyID)
            throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        Iterable<PersonJPA> personJPAList = this.repository.findAllByFamilyID(familyID.toString());
        PersonList result = new PersonList();
        for (PersonJPA personJPA : personJPAList) {
            JpaToDomainPersonDTO dto = assembler.toDomain(personJPA);
            result.add(PersonFactory.create(dto));
        }
        return result;
    }

    /**
     * Verifies if person with familyID and Vat exists.
     *
     * @param familyID - person's familyID.
     * @param vat - person's Vat.
     * @return true if person with familyID and Vat exists.
     */
    @Transactional
    public boolean existsByFamilyIDAndVat(FamilyID familyID, VAT vat) {
        return this.repository.existsByFamilyIDAndVat(familyID.toString(), vat.toString());
    }

    /**
     * Delete all data from repository
     */
    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }

}
