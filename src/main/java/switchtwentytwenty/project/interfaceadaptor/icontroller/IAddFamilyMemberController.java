package switchtwentytwenty.project.interfaceadaptor.icontroller;

import org.springframework.http.ResponseEntity;

import switchtwentytwenty.project.dto.indto.InPersonDTO;

public interface IAddFamilyMemberController {


    /**
     * Allows to add a new family Member.
     * @param info - InPersonDTO with person data.
     * @return An response entity that informs the operation success.
     */
    ResponseEntity<Object> addFamilyMember(InPersonDTO info);
}
