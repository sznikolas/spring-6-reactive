package nikolas.springframework.spring6reactive.services;

import nikolas.springframework.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;

public interface BeerService {

    Flux<BeerDTO> listBeers();

}
