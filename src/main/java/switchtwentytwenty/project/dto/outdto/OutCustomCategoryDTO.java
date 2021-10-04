package switchtwentytwenty.project.dto.outdto;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

public class OutCustomCategoryDTO extends RepresentationModel<OutCustomCategoryDTO> {

    @Getter
    private String designation;

    /**
     * Sole constructor.
     *
     * @param designation of the created category
     */
    public OutCustomCategoryDTO(String designation) {
        this.designation = designation;
    }
}
