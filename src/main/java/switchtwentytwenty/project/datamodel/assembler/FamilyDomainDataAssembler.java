package switchtwentytwenty.project.datamodel.assembler;

import org.springframework.stereotype.Service;
import switchtwentytwenty.project.datamodel.*;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.constant.Constants;

import switchtwentytwenty.project.domain.share.familydata.FamilyName;

import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.todomaindto.JpaToDomainFamilyDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;
import switchtwentytwenty.project.util.Util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FamilyDomainDataAssembler {

    /**
     * Method to FamilyJPA from Family
     *
     * @param family
     * @return
     */
    public FamilyJPA toData(Family family) {

        FamilyJPA familyJPA = new FamilyJPA(family.getID().toString(), family.getRegistrationDate().toString(),
                family.getName().toString(), family.getAdministratorID().toString(),
                family.getLedgerID().toString());
        String account;
        if (family.getCashAccountID() != null) {
            account = family.getCashAccountID().toString();
            familyJPA.setCashAccountID(account);
        }
        return familyJPA;
    }

    /**
     * Method to return a Family from a FamilyJPA
     *
     * @param familyJPA
     * @return
     * @throws InvalidEmailException
     */
    public JpaToDomainFamilyDTO toDomain(FamilyJPA familyJPA) throws InvalidEmailException, IOException, InvalidRelationTypeException {
        FamilyID familyID = new FamilyID(UUID.fromString(familyJPA.getId()));
        FamilyName familyName = new FamilyName(familyJPA.getName());
        Email adminID = new Email(familyJPA.getAdministratorID());
        LedgerID ledgerID = new LedgerID(UUID.fromString(familyJPA.getLedgerID()));
        Date date = Util.stringToDate(familyJPA.getRegistrationDate(), Constants.REGISTRATION_DATE_FORMAT);
        RegistrationDate registrationDate = new RegistrationDate();
        registrationDate.setDate(date);
        JpaToDomainFamilyDTO dto = new JpaToDomainFamilyDTO();
        dto.setFamilyID(familyID);
        dto.setName(familyName);
        dto.setAdministratorID(adminID);
        dto.setLedgerID(ledgerID);
        dto.setRegistrationDate(registrationDate);
        String accountID = familyJPA.getCashAccountID();

        if (accountID != null) {
            AccountID accountid = new AccountID(UUID.fromString(accountID));
            dto.setAccountID(accountid);
        }
        List<FamilyRelation> familyRelationList = getFamilyRelations(familyJPA);
        dto.setFamilyRelation(familyRelationList);
        return dto;
    }

    private List<FamilyRelation> getFamilyRelations(FamilyJPA familyJPA) throws InvalidEmailException, IOException, InvalidRelationTypeException {
        List<FamilyRelation> familyRelationList = new ArrayList<>();
        List<FamilyRelationJPA> familyRelations = familyJPA.getFamilyRelationList();
        if (!(familyRelations.isEmpty() || familyRelations == null)) {
            for (FamilyRelationJPA jpa : familyRelations) {
                FamilyRelationIDJPA familyRelationJPAId = jpa.getId();
                String relationType = jpa.getRelationType();
                String personId = familyRelationJPAId.getPersonID();
                String kinId = familyRelationJPAId.getKinID();
                RelationType relationT = RelationType.getInstance(relationType);
                Email personID = new Email(personId);
                Email kinID = new Email(kinId);
                FamilyRelation familyRelation = new FamilyRelation(personID, kinID, relationT);
                familyRelationList.add(familyRelation);
            }
        }
        return familyRelationList;
    }
}
