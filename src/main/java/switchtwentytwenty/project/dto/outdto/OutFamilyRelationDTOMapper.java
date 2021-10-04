package switchtwentytwenty.project.dto.outdto;

import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;

public class OutFamilyRelationDTOMapper {

    //Attributes

    //Constructor Methods

    /**
     * Sole constructor.
     */
    private OutFamilyRelationDTOMapper() {}

    //Getter and Setters

    //Business Methods

    /**
     * From Domain to Data Transfer Object.
     *
     * @param familyRelation - Domain instance
     * @return Data Transfer Object
     */
    public static OutFamilyRelationDTO toDTO(FamilyRelation familyRelation) {
        String personID = familyRelation.getPersonID().toString();
        String kinID = familyRelation.getKinID().toString();
        String relationType = familyRelation.getRelationType().toString();
        return new OutFamilyRelationDTO(personID, kinID, relationType);
    }


    //Equals and HashCode
}
