package nikolas.springframework.spring6reactive.mappers;

import nikolas.springframework.spring6reactive.domain.Beer;
import nikolas.springframework.spring6reactive.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);
}
