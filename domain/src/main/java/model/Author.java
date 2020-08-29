package model;

import java.io.*;
import java.util.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "author")
@Getter
@Setter
@EqualsAndHashCode
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long author_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<> ();
}
