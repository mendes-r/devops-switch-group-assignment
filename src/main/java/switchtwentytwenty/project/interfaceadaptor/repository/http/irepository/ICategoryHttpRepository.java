package switchtwentytwenty.project.interfaceadaptor.repository.http.irepository;

import switchtwentytwenty.project.dto.outdto.OutStandardCategoryDTO;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;

import java.util.List;

public interface ICategoryHttpRepository {

    /**
     * Get a list of standard categories from external source.
     *
     * @return The list of standard categories in OutStandardCategoryDTO format.
     */
    List<StandardCategoryDTO> getStandardCategories();
}
