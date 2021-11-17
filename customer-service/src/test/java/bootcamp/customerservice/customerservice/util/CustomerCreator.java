package bootcamp.customerservice.customerservice.util;


import bootcamp.customerservice.customerservice.documents.Customer;
import bootcamp.customerservice.customerservice.documents.CustomerType;

public class CustomerCreator {

    public static Customer createValidCustomer(){
        CustomerType ct = new CustomerType("617ee26c3063e75966d09a8e","TP-01", "Personal");

        return Customer.builder()
                .id("617ee981e22159302f88c88a")
                .name("Tony Sanchez")
                .customerIdentityType("DNI")
                .customerIdentityNumber("41526374")
                .customerType(ct)
                .build();

    }

    public static Customer createCustomerToBeSaved(){
        CustomerType ct = new CustomerType("617ee26c3063e75966d09a8e","TP-01", "Personal");
        return Customer.builder()
                .name("Tony Sanchez")
                .customerIdentityType("DNI")
                .customerIdentityNumber("41526374")
                .customerType(ct)
                .build();
    }

    public static Customer createCustomerToBeUpdated(){
        CustomerType ct = new CustomerType("617ee26c3063e75966d09a77","TP-02", "Empresarial");
        return Customer.builder()
                .name("John Sanchez")
                .customerIdentityNumber("41526375")
                .customerType(ct)
                .build();
    }

    public static Customer createCustomerUpdated(){
        CustomerType ct = new CustomerType("617ee26c3063e75966d09a77","TP-02", "Empresarial");
        return Customer.builder()
                .id("617ee981e22159302f88c88a")
                .name("John Sanchez")
                .customerIdentityType("DNI")
                .customerIdentityNumber("41526375")
                .customerType(ct)
                .build();
    }

}
