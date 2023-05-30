package com.example.book.repository;

import com.example.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
// <Entity 이름, id 컬럼 타입>
//    자동 string 화로 @reposi 필요 없음

}
