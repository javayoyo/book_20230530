package com.example.book;

import com.example.book.dto.BookDTO;
import com.example.book.service.BookService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Assertions 클래스가 가지고 있는 모든 static 메서드를 가져오겠다.
import static org.assertj.core.api.Assertions.*;

@SpringBootTest

public class BookTest {
    @Autowired
    private BookService bookService;

    // 도서 등록 테스트

    /**
     * 1. 신규 도서 데이터 생성
     * 2. save 메서드 호출해서 저장 처리
     * 3. 저장한 데이터의 id 값을 가져오고
     * 4. 해당 id 로 DB 에서 조회릃 한 뒤
     * 5. 1번에서 만든 객체의 책제목 값과 4번에서 조회한 객체의 책제목 값이
     *   일치하는지를 판단하여
     * 6. 일치하면 테스트 성공, 일치하지 않으면 테스트 실패
    **/

    private BookDTO newBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName("test book");
        bookDTO.setBookAuthor("test author");
        bookDTO.setBookPrice(10000);
        return bookDTO;
    }

    //
    @Test
    public void bookSaveTest() {
        BookDTO bookDTO = newBook(); // 테스트용 데이터 준비
        Long savedId = bookService.save(bookDTO); // 저장을 위해 메서드 호출 후 id 값 가져옴
        // id 로 조회
        BookDTO findDTO = bookService.findById(savedId);
        // 테스트용 데이터의 제목과 조회한 데이터의 제목이 일치하는지 확인
        assertThat(bookDTO.getBookName()).isEqualTo(findDTO.getBookName());



    }

}
