package nikolas.springframework.spring6reactive.repositories;

import nikolas.springframework.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {



}
