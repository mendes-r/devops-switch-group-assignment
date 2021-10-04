package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
public class OutFamilyProfileDTO extends RepresentationModel<OutFamilyProfileDTO> {

    //Attributes
    @Getter
    private String familyName;
    @Getter
    private String registrationDate;
    @Getter
    private String administratorID;
}
