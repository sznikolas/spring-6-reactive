package nikolas.springframework.spring6reactive.repositories;

import nikolas.springframework.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.Optional;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {

}
