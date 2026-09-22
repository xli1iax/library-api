package boot.service;

import boot.dto.BookDto;
import boot.dto.BookSearchParametersDto;
import boot.dto.CreateBookRequestDto;
import boot.exception.EntityNotFoundException;
import boot.mapper.BookMapper;
import boot.model.Book;
import boot.repository.SpecificationBuilder;
import boot.repository.book.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    private final SpecificationBuilder<Book, BookSearchParametersDto> bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toBook(bookDto);
        return bookMapper.toBookDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public BookDto findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Can't find Book with id:" + id)
        );
        return bookMapper.toBookDto(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Book not found with id: " + id));

        bookMapper.updateBookFromDto(bookDto, book);

        return bookMapper.toBookDto(book);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters) {
        Specification<Book> specification = bookSpecificationBuilder
                .buildSpecification(searchParameters);
        return bookRepository.findAll(specification)
                .stream()
                .map(bookMapper::toBookDto)
                .toList();
    }
}
