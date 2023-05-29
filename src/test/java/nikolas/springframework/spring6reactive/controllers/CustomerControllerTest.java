package nikolas.springframework.spring6reactive.controllers;

import nikolas.springframework.spring6reactive.domain.Customer;
import nikolas.springframework.spring6reactive.model.BeerDTO;
import nikolas.springframework.spring6reactive.model.CustomerDTO;
import nikolas.springframework.spring6reactive.repositories.CustomerRepository;
import nikolas.springframework.spring6reactive.repositories.CustomerRepositoryTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @Order(999)
    void testDeleteCustomer() {
        webTestClient.delete().uri(BeerController.BEER_PATH_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testDeleteCustomerIdNotFound() {
        webTestClient.delete().uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testPatchIdNotFound() {
        webTestClient.patch().uri(CustomerController.CUSTOMER_PATH_ID, 999)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateCustomer() {
        webTestClient.put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testUpdateCustomerBadRequest() {
        Customer testCustomer = CustomerRepositoryTest.getTestCustomer();
        testCustomer.setCustomerName("");

        webTestClient.put().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .body(Mono.just(testCustomer), CustomerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();

    }

    @Test
    void testUpdateCustomerIdNotFound() {
        webTestClient.put().uri(CustomerController.CUSTOMER_PATH_ID,999)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @Order(3)
    void testCreateNewCustomer() {
        webTestClient.post().uri(CustomerController.CUSTOMER_PATH)
                .body(Mono.just(CustomerRepositoryTest.getTestCustomer()), CustomerDTO.class)
                .header("Content-Type", "application/json")
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().location("http://localhost:8080/api/v2/customer/4");
    }

    @Test
    void testCreateNewCustomerBadData() {
        Customer testCustomer = CustomerRepositoryTest.getTestCustomer();
        testCustomer.setCustomerName("");

        webTestClient.post().uri(CustomerController.CUSTOMER_PATH, 1)
                .body(Mono.just(testCustomer), CustomerDTO.class)
                .header("Content-Type","application/json")
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Order(2)
    void getListCustomers() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(1)
    void getCustomerById() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectBody(CustomerDTO.class);
    }

    @Test
    void testGetByIdNotFound() {
        webTestClient.get().uri(CustomerController.CUSTOMER_PATH_ID,999)
                .exchange()
                .expectStatus().isNotFound();
    }
}