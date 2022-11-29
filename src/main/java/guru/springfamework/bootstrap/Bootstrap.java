package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRespository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
        this.categoryRespository = categoryRespository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Categories Loaded = " + categoryRespository.count() );
    }

    private void loadCustomers() {
        Customer customer1 = Customer.builder()
                .firstName("Jef")
                .lastName("Patat")
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Louise")
                .lastName("Wanten")
                .build();

        Customer customer3 = Customer.builder()
                .firstName("Zoe")
                .lastName("Van De Walle")
                .build();

        Customer customer4 = Customer.builder()
                .firstName("Charlotte")
                .lastName("Van De Walle")
                .build();

        Customer customer5 = Customer.builder()
                .firstName("Monja")
                .lastName("Braet")
                .build();

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);

        System.out.println("Customers Loaded = " + customerRepository.count() );
    }
}
