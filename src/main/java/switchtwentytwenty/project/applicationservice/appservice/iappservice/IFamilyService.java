package switchtwentytwenty.project.applicationservice.appservice.iappservice;

import switchtwentytwenty.project.dto.outdto.OutFamilyDTO;
import switchtwentytwenty.project.dto.outdto.OutFamilyProfileDTO;
import switchtwentytwenty.project.dto.outdto.OutSystemRelationsDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.util.List;

public interface IFamilyService {

    /**
     * Get family profile
     * @param familyID - family ID
     * @return family profile: name, registration date, administratorID, administrator name.
     */
    OutFamilyProfileDTO getFamilyProfile(String familyID) throws ElementNotFoundException, InvalidEmailException, InvalidRelationTypeException, IOException;

    /**
     * Get the list of all possible relations in the system
     * @return an OutSystemRelationsDTO
     */
    OutSystemRelationsDTO getSystemRelations();

    List<OutFamilyDTO> getListOfFamilies() throws InvalidRelationTypeException, IOException, InvalidEmailException;

}
