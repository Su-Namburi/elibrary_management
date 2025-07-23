package com.example.elibrary_management_system.dtos;

import com.example.elibrary_management_system.models.Author;
import com.example.elibrary_management_system.models.Book;
import com.example.elibrary_management_system.models.Genre;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {

    //from Book
    private String title;
    private Genre genre;
    //from Student
    private String authorName;

    @Email
    private String authorEmail;

    public Book toBook() {

        return Book
                .builder()
                .title(title)
                .genre(genre)
                .author(Author
                        .builder()
                        .authorName(authorName)
                        .authorEmail(authorEmail)
                        .build())
                .build();
    }
}
