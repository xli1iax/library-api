package boot.repository.book;

import boot.exception.SpecificationProviderNotFoundException;
import boot.model.Book;
import boot.repository.SpecificationProvider;
import boot.repository.SpecificationProviderManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> specificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return specificationProviders.stream()
                .filter(spec -> spec.getKey().equals(key))
                .findFirst()
                .orElseThrow(() -> new SpecificationProviderNotFoundException(
                        "Can't find specification provider for key: " + key));
    }
}
