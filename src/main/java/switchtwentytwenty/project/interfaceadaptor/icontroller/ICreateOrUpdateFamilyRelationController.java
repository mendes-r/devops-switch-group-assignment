package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.dto.indto.InFamilyRelationDTO;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidDateException;

public interface ICreateOrUpdateFamilyRelationController {

    /**
     * Creates a family relation between two persons.
     *
     * @param familyIdentifier - family identifier
     * @param info
     * @return
     */
    ResponseEntity<Object> createOrUpdateFamilyRelation(String familyIdentifier, InFamilyRelationDTO info) throws InvalidDateException, ElementNotFoundException;

}
