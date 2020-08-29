package model;

import javax.persistence.*;
import java.io.*;
import java.time.*;
import java.util.*;

import lombok.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@EqualsAndHashCode
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;
    @Column(nullable = false)
    private String isbn;
    @Enumerated(EnumType.STRING)
    @Column(name = "book_genres")
    private GenresOfBooks genresOfBooks;
    @Column(name = "number_of_pages", nullable = false)
    private int numberOfPages;
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Borrow> borrows = new ArrayList<> ();
}
