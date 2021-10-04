package switchtwentytwenty.project.dto.outdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@AllArgsConstructor
public class OutStandardCategoryDTO extends RepresentationModel<OutStandardCategoryDTO> {

    //Attributes
    @Getter
    private String designation;
    @Getter
    private String id;
    @Getter
    private String parentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutStandardCategoryDTO)) return false;
        OutStandardCategoryDTO that = (OutStandardCategoryDTO) o;
        return Objects.equals(designation, that.designation) && Objects.equals(id, that.id) && Objects.equals(parentID, that.parentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash( designation, id, parentID);
    }
}
