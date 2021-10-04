package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IPersonService;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.dto.outdto.OutAddEmailDTO;
import switchtwentytwenty.project.dto.outdto.OutPersonDTO;
import switchtwentytwenty.project.dto.outdto.OutUserProfileDTO;
import switchtwentytwenty.project.exception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PersonService implements IPersonService {

    //Attributes

    @Autowired
    private final IPersonRepository personRepository;


    //Getter and Setters

    //Business Method

    /**
     * Method to add an Email to my account.
     *
     * @param personId   - user's unique ID
     * @param emailToAdd - Email that i want to add to my profile
     * @return true if your ID exists and if the email to add is valid and unique
     * @throws InvalidEmailException
     */
    public OutAddEmailDTO addEmailToProfile(String personId, String emailToAdd)
            throws InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException {
        Email personID = new Email(personId);
        Email inputEmail = new Email(emailToAdd);
        Person person = personRepository.findByID(personID);
        person.addEmail(inputEmail);
        personRepository.save(person);
        return new OutAddEmailDTO(person.getID().toString(), inputEmail.toString());
    }

    /**
     * Finds person given his ID and gets their profile information.
     *
     * @param personId - person's ID (main email)
     * @return the person profile
     * @throws ElementNotFoundException - person not found
     */

    public OutUserProfileDTO getUserProfile(String personId)
            throws InvalidEmailException, InvalidDateException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException {
        Email parseId = new Email(personId);
        Person person = personRepository.findByID(parseId);
        return person.getProfile();
    }

    /**
     * Gets list with all the family members of a certain family.
     *
     * @param familyID- family ID
     * @return list with members of a certain family
     * @throws InvalidDateException       in case the Date is invalid
     * @throws InvalidEmailException      in case the email is invalid
     * @throws InvalidVATException        in case the VAT number is invalid
     * @throws InvalidPersonNameException in case the Name is invalid
     */
    public List<OutPersonDTO> getListOfFamilyMembers(String familyID) throws InvalidDateException, InvalidEmailException, InvalidVATException, InvalidPersonNameException {
        FamilyID famID = new FamilyID(UUID.fromString(familyID));
        List<OutPersonDTO> familyMembers = new ArrayList<>();
        PersonList members = personRepository.findByFamilyID(famID);
        List<Person> users = members.getPersonList();

        for (Person person : users) {
            OutPersonDTO user = new OutPersonDTO(person.getName().toString(), person.getMainEmail().toString(), person.getFamilyID().toString());
            familyMembers.add(user);
        }
        return familyMembers;
    }
}


