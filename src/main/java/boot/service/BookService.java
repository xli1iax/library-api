package boot.service;

import boot.dto.BookDto;
import boot.dto.BookSearchParametersDto;
import boot.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto findById(Long id);

    void deleteById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto bookDto);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
