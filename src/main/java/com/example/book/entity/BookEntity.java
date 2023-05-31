package com.example.book.entity;

import com.example.book.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.thymeleaf.model.IStandaloneElementTag;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "book_table")
@ToString
public class BookEntity {
//    TEST 코드 위해 > @ToString  // 원래는 안해도 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String bookName;

    @Column(length = 20)
    private String bookAuthor;

    @Column
    private int bookPrice;

    // 기본 생성자를 private 으로 > 다른곳에서 생성됨을 방지
//    private BookEntity() {
//
//    }

    //    ** 직접 클래스로 접근하여 사용가능한 메서드  static ㄱ
    public static BookEntity toSaveEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;

    }

    public static BookEntity toUpdateEntity(BookDTO bookDTO) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookDTO.getId());
        bookEntity.setBookName(bookDTO.getBookName());
        bookEntity.setBookAuthor(bookDTO.getBookAuthor());
        bookEntity.setBookPrice(bookDTO.getBookPrice());
        return bookEntity;
    }
}



