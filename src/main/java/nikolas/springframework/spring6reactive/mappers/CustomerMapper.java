package nikolas.springframework.spring6reactive.mappers;

import nikolas.springframework.spring6reactive.domain.Customer;
import nikolas.springframework.spring6reactive.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer (CustomerDTO dto);
    CustomerDTO customerToCustomerDto (Customer customer);
}
