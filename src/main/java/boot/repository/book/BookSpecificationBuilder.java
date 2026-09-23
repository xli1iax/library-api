package boot.repository.book;

import boot.dto.BookSearchParametersDto;
import boot.model.Book;
import boot.repository.SpecificationBuilder;
import boot.repository.SpecificationProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book,
        BookSearchParametersDto> {
    private static final String TITLE_KEY = "title";
    private static final String PRICE_KEY = "price";
    private static final String AUTHOR_KEY = "author";

    private final SpecificationProviderManager<Book> specificationProviderManager;

    @Override
    public Specification<Book> buildSpecification(BookSearchParametersDto searchParam) {
        Specification<Book> spec = Specification.unrestricted();

        if (searchParam.authors() != null && searchParam.authors().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(AUTHOR_KEY)
                    .getSpecification(searchParam.authors()));
        }

        if (searchParam.title() != null && !searchParam.title().isEmpty()) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(TITLE_KEY)
                    .getSpecification(new String[]{searchParam.title()}));
        }

        if (searchParam.prices() != null && searchParam.prices().length > 0) {
            spec = spec.and(specificationProviderManager
                    .getSpecificationProvider(PRICE_KEY)
                    .getSpecification(searchParam.prices()));
        }

        return spec;
    }
}
