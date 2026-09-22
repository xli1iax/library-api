package boot.repository.book;

import boot.model.Book;
import boot.repository.SpecificationProvider;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String KEY = "price";

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        BigDecimal maxPrice = new BigDecimal(params[1]);
        BigDecimal minPrice = new BigDecimal(params[0]);

        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get(KEY).as(BigDecimal.class), minPrice, maxPrice));
    }
}
