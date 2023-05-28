package nikolas.springframework.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import nikolas.springframework.spring6reactive.mappers.BeerMapper;
import nikolas.springframework.spring6reactive.model.BeerDTO;
import nikolas.springframework.spring6reactive.repositories.BeerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public Flux<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .map(beerMapper::beerToBeerDto);
    }
}
