package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "Jef";
    public static final String LAST_NAME = "Patat";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);

    }

    @Test
    public void getAllCustomers() {

        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3, customerDTOS.size());

    }

    @Test
    public void getCustomerByLastName() {

        Customer customer = Customer.builder()
                .id(ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build();

        when(customerRepository.findByLastName(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);

        assertEquals(LAST_NAME, customerDTO.getLastName());
        assertEquals(FIRST_NAME, customerDTO.getFirstName());

    }
}