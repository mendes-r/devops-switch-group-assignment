package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.outdto.OutFamilyDTO;
import switchtwentytwenty.project.dto.outdto.OutFamilyProfileDTO;
import switchtwentytwenty.project.dto.outdto.OutSystemRelationsDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FamilyService implements IFamilyService {

    //Attributes
    @Autowired
    private final IFamilyRepository familyRepository;

    /**
     * Get family profile
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    public OutFamilyProfileDTO getFamilyProfile(String familyID) throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException {
        FamilyID id = new FamilyID(UUID.fromString(familyID));
        Family family = familyRepository.findByID(id);
        String familyName = family.getName().toString();
        String registrationDate = splitDate(family.getRegistrationDate().toString());
        String adminID = family.getAdministratorID().toString();
        return new OutFamilyProfileDTO(familyName,registrationDate,adminID);
    }

    /**
     * remove hours from family's registration date
     * @param completeRegistrationDate - full registration date
     * @return - only registration date
     */
    private String splitDate(String completeRegistrationDate){
        String[] split = completeRegistrationDate.split(" ");
        String onlyDate = split[0];
        return onlyDate;
    }

    /**
     * Get the list of all possible relations in the system
     * @return an OutSystemRelationsDTO
     */
    public OutSystemRelationsDTO getSystemRelations() {

        List<String> relationsList = getSystemRelationsList();

        return new OutSystemRelationsDTO(relationsList);
    }

    /**
     * Get the list of all possible relations in the system
     * @return a list of strings of all the relations from the system
     */
    private List<String> getSystemRelationsList() {

        List<String> systemRelations = new ArrayList<>();

        systemRelations.add(Constants.PARENT);
        systemRelations.add(Constants.CHILD);
        systemRelations.add(Constants.SPOUSE);
        systemRelations.add(Constants.SIBLING);
        systemRelations.add(Constants.UNCLE);
        systemRelations.add(Constants.NEPHEW);
        systemRelations.add(Constants.GRANDPARENT);
        systemRelations.add(Constants.GRANDCHILD);
        systemRelations.add(Constants.COUSIN);
        systemRelations.add(Constants.FRIEND);
        systemRelations.add(Constants.PARTNER);
        systemRelations.add(Constants.NOT_DEFINED);

        return systemRelations;
    }

    /**
     * Get the list of all the existing families in the system
     * @return List of families in the system
     * @throws InvalidRelationTypeException
     * @throws IOException
     * @throws InvalidEmailException
     */
    public List<OutFamilyDTO> getListOfFamilies() throws InvalidRelationTypeException, IOException, InvalidEmailException {
        List<OutFamilyDTO> familiesDTO =new ArrayList<>();
        List<Family> familyList = familyRepository.findAll();
        for(Family family: familyList) {
            OutFamilyDTO outFamilyDTO = new OutFamilyDTO(family.getName().toString(),
                                                        family.getID().toString());
            familiesDTO.add(outFamilyDTO);
        }
        return familiesDTO;
    }

}
