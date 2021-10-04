package switchtwentytwenty.project.interfaceadaptor.repository.http.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ExternalStandardCategoryDTOWrapperIII {

    @Getter
    @Setter
    private List<ExternalStandardCategoryDTOIII> outputCategoryDTOList;

    public ExternalStandardCategoryDTOWrapperIII() {
        outputCategoryDTOList = new ArrayList<>();
    }
}
