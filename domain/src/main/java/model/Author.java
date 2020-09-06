package model;

import java.util.*;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long author_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "place_of_birth", nullable = false)
    private String placeOfBirth;

    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<> ();

    public Author (String firstName, String lastName, String placeOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;
    }

    public Author (long author_id, String firstName, String lastName, String placeOfBirth) {
        this.author_id = author_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.placeOfBirth = placeOfBirth;

    }

    @Override
    public String toString () {
        return "Author{" +
                "author_id=" + author_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                '}';
    }
}
