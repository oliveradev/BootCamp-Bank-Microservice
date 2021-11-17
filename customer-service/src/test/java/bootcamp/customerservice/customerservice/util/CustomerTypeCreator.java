package bootcamp.customerservice.customerservice.util;

import bootcamp.customerservice.customerservice.documents.CustomerType;

public class CustomerTypeCreator {

    public static CustomerType createValidCustomerType(){
        return CustomerType.builder()
                .id("617ee25b3063e75966d09a8c")
                .code("TP-04")
                .name("PYME")
                .build();
    }

    public static CustomerType createCustomerTypeToBeSaved(){
        return CustomerType.builder()
                .code("TP-04")
                .name("PYME")
                .build();
    }
}
