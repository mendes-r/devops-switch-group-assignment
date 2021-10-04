package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestParam;

public interface IGetListOfFamilyMembersController {

    /**
     * Get list of the family members of a certain family.
     *
     * @param familyID - family ID
     * @return a list with the family members
     */
    ResponseEntity<Object> getListOfFamilyMembers(@RequestParam(name = "familyID") String familyID);

    }
