package com.example.book.service;
import com.example.book.dto.BookDTO;
import com.example.book.entity.BookEntity;
import com.example.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public void save(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
        BookEntity bookEntity = BookEntity.toSaveEntity(bookDTO);
        bookRepository.save(bookEntity);

    }

//    서비스 내에서만 Entity 로 변환시켜준 후, 해당 값을 리턴한다ㄱ
//    private BookEntity toSaveEntity(BookDTO bookDTO) {
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookName(bookDTO.getBookName());
//        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
//        bookEntity.setBookPrice(bookDTO.getBookPrice());
//        return bookEntity;
//    }

    public List<BookDTO> findAll() {
        List<BookEntity> bookEntityList = bookRepository.findAll();
        // List<BookEntity> -> List<BookDTO>
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (BookEntity bookEntity : bookEntityList) {

//            /*
//         1. Entity -> DTO 변환 메서드 호출(메서드는 BookDTO 에 정의)
//         2. 호출결과를 DTO 객체로 받음.
//         3. DTO 객체를 DTO 리스트에 추가
//         4. 반복문 종료 후 DTO 리스트를 리턴
//         */

            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            bookDTOList.add(bookDTO);

//            bookDTOList.add(BookDTO.toDTO(bookEntity));
//            ㄴ 위 두줄을 한 줄로 코딩 가능

        }
        return bookDTOList;
    }

    public BookDTO findById(Long id) {
         Optional<BookEntity> optionalBookEntity = bookRepository.findById(id);
        if(optionalBookEntity.isPresent()) {
            System.out.println("있다");
//            optional 객체에서 꺼내기
            BookEntity bookEntity = optionalBookEntity.get();
            // BookEntity -> BookDTO 변환
            BookDTO bookDTO = BookDTO.toDTO(bookEntity);
            return bookDTO;

//            return BookDTO.toDTO(optionalBookEntity.get());
//            ㄴ optional 객체에서 꺼내기 3줄 > 한줄로 코딩 가능

        }else {
            System.out.println("없다");
            return null;
        }

    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
//    ㄴ 리스트에 해당 값이 있을 경우 >있다, 없으면 없다


//    update > save 로 진행 , 이전 id 가 존재하면 update, id 가 없으면 save
    public void update(BookDTO bookDTO) {
        BookEntity bookEntity = BookEntity.toUpdateEntity(bookDTO);
        bookRepository.save(bookEntity);
    }

}
