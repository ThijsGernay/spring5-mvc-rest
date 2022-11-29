package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    public static final String LAST_NAME = "Patat";
    public static final String FIRST_NAME = "Jef";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() {

        Customer customer = Customer.builder()
                .lastName(LAST_NAME).firstName(FIRST_NAME).build();

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());

    }
}