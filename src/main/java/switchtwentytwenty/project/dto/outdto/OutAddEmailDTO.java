package switchtwentytwenty.project.dto.outdto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutAddEmailDTO extends RepresentationModel<OutAddEmailDTO> {


    //Attributes
    @Getter
    private String personID;

    @Getter
    private String inputEmail;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutAddEmailDTO)) return false;
        OutAddEmailDTO that = (OutAddEmailDTO) o;
        return Objects.equals(personID, that.personID) && Objects.equals(inputEmail, that.inputEmail);

    }

    @Override
    public int hashCode() {
        return Objects.hash(personID,inputEmail);
    }

}
