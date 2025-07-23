package com.example.elibrary_management_system.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String mobile;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "student")
    private List<Book> books;
}
