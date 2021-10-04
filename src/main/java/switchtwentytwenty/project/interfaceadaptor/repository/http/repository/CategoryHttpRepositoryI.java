package switchtwentytwenty.project.interfaceadaptor.repository.http.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.toservicedto.StandardCategoryDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.http.irepository.ICategoryHttpRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.ExternalCategoryMapperI;
import switchtwentytwenty.project.interfaceadaptor.repository.http.dto.ExternalStandardCategoryDTOWrapperI;

import java.util.Collections;
import java.util.List;

@Repository
public class CategoryHttpRepositoryI implements ICategoryHttpRepository {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Get a list of standard categories from group I api.
     *
     * @return The list of standard categories in OutStandardCategoryDTO format.
     */
    public List<StandardCategoryDTO> getStandardCategories() {
        ResponseEntity<ExternalStandardCategoryDTOWrapperI> response;
        try {
            response = restTemplate
                    .getForEntity(Constants.URL_CATEGORIES_GROUP_I, ExternalStandardCategoryDTOWrapperI.class);
        } catch (Exception exception) {
            return Collections.emptyList();
        }
        if (response.getBody() == null) {
            return Collections.emptyList();
        }
        return ExternalCategoryMapperI.toStandardDTOList(response.getBody().getCategoryDTOs());
    }
}
