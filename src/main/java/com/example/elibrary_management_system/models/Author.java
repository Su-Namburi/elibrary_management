package com.example.elibrary_management_system.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authorName;

    private String authorEmail;
    @CreationTimestamp
    private Date createdOn;

    //bidirectional mapping. can lead to cyclic dependency
    @OneToMany(mappedBy = "author")
    private List<Book> books;


}
