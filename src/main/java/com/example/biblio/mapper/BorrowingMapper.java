package com.example.biblio.mapper;

import com.example.biblio.dto.BorrowingDto;
import com.example.biblio.entities.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "status", target = "status")
    BorrowingDto toDto(Borrowing borrowing);
}
