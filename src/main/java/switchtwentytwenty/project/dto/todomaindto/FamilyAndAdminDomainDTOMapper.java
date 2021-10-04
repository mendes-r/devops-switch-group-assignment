package switchtwentytwenty.project.dto.todomaindto;

public abstract class FamilyAndAdminDomainDTOMapper {

    public static VOPersonDTO createVOPersonDTO(FamilyAndAdminVODTO vo){
        VOPersonDTO voPersonDTO = new VOPersonDTO();
        voPersonDTO.setAddress(vo.getAddress());
        voPersonDTO.setBirthDate(vo.getBirthDate());
        voPersonDTO.setEmail(vo.getEmail());
        voPersonDTO.setLedgerID(vo.getPersonLedgerID());
        voPersonDTO.setFamilyID(vo.getFamilyID());
        voPersonDTO.setName(vo.getPersonName());
        voPersonDTO.setVat(vo.getVat());
        voPersonDTO.setPhoneNumbers(vo.getPhoneNumbers());
        return voPersonDTO;
    }

    public static VOFamilyDTO createVOFamilyDTO(FamilyAndAdminVODTO vo){
        VOFamilyDTO voFamilyDTO = new VOFamilyDTO();
        voFamilyDTO.setLedgerID(vo.getFamilyLedgerID());
        voFamilyDTO.setFamilyID(vo.getFamilyID());
        voFamilyDTO.setAdministratorID(vo.getEmail());
        voFamilyDTO.setName(vo.getFamilyName());
        return voFamilyDTO;
    }
}
