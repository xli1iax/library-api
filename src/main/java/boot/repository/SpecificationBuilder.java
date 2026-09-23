package boot.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, P> {
    Specification<T> buildSpecification(P searchParam);
}
