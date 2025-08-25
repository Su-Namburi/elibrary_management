package com.example.elibrary_management_system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties("student")
    private User user;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @OneToMany(mappedBy = "student")
    //@JsonIgnoreProperties("student")
    @JsonIgnore
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties("student")
    private List<Book> books;
}
