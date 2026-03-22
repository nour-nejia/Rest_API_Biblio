package com.example.biblio.mapper;

import com.example.biblio.dto.BookDto;
import com.example.biblio.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "authorName")
    BookDto toDto(Book book);

    Book toEntity(BookDto dto);
}
