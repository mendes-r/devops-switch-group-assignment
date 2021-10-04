package switchtwentytwenty.project.dto.indto;

import lombok.Getter;
import lombok.Setter;

public class InPaymentDTO {

    //Attributes
    @Getter
    @Setter
    private String personID;
    @Setter
    @Getter
    private String designation;
    @Setter
    @Getter
    private String categoryID;
    @Setter
    @Getter
    private double amount;
    @Setter
    @Getter
    private String date;


    //Builder Inner Class
    public static class PaymentDTOBuilder {
        //Builder attributes
        private final InPaymentDTO dto;

        public PaymentDTOBuilder() {
            this.dto = new InPaymentDTO();
        }

        public InPaymentDTO.PaymentDTOBuilder withPersonID(String id) {
            dto.setPersonID(id);
            return this;
        }

        public InPaymentDTO.PaymentDTOBuilder withDesignation(String designation) {
            dto.setDesignation(designation);
            return this;
        }

        public InPaymentDTO.PaymentDTOBuilder withAmount(double amount) {
            dto.setAmount(amount);
            return this;
        }

        public InPaymentDTO.PaymentDTOBuilder withDate(String date) {
            dto.setDate(date);
            return this;
        }

        public InPaymentDTO.PaymentDTOBuilder withCategoryID(String categoryID) {
            dto.setCategoryID(categoryID);
            return this;
        }

        public InPaymentDTO build() {
            return this.dto;

        }
    }


}
