package nikolas.springframework.spring6reactive.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nikolas.springframework.spring6reactive.config.DatabaseConfig;
import nikolas.springframework.spring6reactive.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

@DataR2dbcTest
@Import(DatabaseConfig.class)
public
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;


    @Test
    void testSaveNewCustomer() {
        customerRepository.save(getTestCustomer())
                .subscribe(customer -> System.out.println(customer.toString()));
    }

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println(objectMapper.writeValueAsString(getTestCustomer()));
    }

    public static Customer getTestCustomer(){
        return Customer.builder()
                .customerName("Kovacs Akos")
                .build();
    }

}