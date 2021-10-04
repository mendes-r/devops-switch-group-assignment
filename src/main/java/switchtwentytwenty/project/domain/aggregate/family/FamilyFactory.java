package switchtwentytwenty.project.domain.aggregate.family;

import switchtwentytwenty.project.dto.todomaindto.JpaToDomainFamilyDTO;
import switchtwentytwenty.project.dto.todomaindto.VOFamilyDTO;

public abstract class FamilyFactory {

    //Attributes Constructor methods

    /**
     * Create Family
     *
     * @param valueObjects - value objects
     */
    public static Family create(VOFamilyDTO valueObjects) {
       Family family = new Family(valueObjects.getFamilyID());
       family.setAdministratorID(valueObjects.getAdministratorID());
       family.setLedgerID(valueObjects.getLedgerID());
       family.setName(valueObjects.getName());
       return family;
    }

    /**
     * Create Family
     *
     * @param dto - dto
     */
    public static Family create(JpaToDomainFamilyDTO dto) {
        Family family = new Family(dto.getFamilyID());
        family.setAdministratorID(dto.getAdministratorID());
        family.setLedgerID(dto.getLedgerID());
        family.setRegistrationDate(dto.getRegistrationDate());
        family.addAccountID(dto.getAccountID());
        family.setFamilyID(dto.getFamilyID());
        family.setFamilyRelationList(dto.getFamilyRelation());
        family.setName(dto.getName());
        return family;
    }
}