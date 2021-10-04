package switchtwentytwenty.project.dto.indto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter(AccessLevel.PROTECTED)
@Getter
public class InPersonDTO {

    //Attribute
    private String email;
    private String name;
    private String birthDate;
    private String vat;
    private String houseNumber;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private List<String> phoneNumbers;
    private String familyID;
}
