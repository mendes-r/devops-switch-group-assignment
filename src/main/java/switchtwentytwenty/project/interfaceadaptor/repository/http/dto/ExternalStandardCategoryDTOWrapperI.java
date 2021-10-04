package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
public class ExternalStandardCategoryDTOWrapperI {

    private List<ExternalStandardCategoryDTOI> categoryDTOs;


    public List<ExternalStandardCategoryDTOI> getCategoryDTOs() {
        return categoryDTOs;
    }


}
