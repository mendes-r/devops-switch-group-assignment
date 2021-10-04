package switchtwentytwenty.project.dto.toservicedto;

import switchtwentytwenty.project.dto.indto.InPersonDTO;

public class PersonDTOMapper {

    /**
     * Private empty constructor.
     */
    private PersonDTOMapper() {
    }

    /**
     * Map to person dto.
     * @param inPersonDTO - in person dto.
     * @return The person dto.
     */
    public static PersonDTO mapToDTO(InPersonDTO inPersonDTO) {
        return new PersonDTO.PersonDTOBuilder()
                .withName(inPersonDTO.getName())
                .withEmail(inPersonDTO.getEmail())
                .withBirthDate(inPersonDTO.getBirthDate())
                .withVat(inPersonDTO.getVat())
                .withHouseNumber(inPersonDTO.getHouseNumber())
                .withStreet(inPersonDTO.getStreet())
                .withZipCode(inPersonDTO.getZipCode())
                .withCity(inPersonDTO.getCity())
                .withCountry(inPersonDTO.getCountry())
                .withPhoneNumbers(inPersonDTO.getPhoneNumbers())
                .withFamilyID(inPersonDTO.getFamilyID())
                .build();
    }
}
