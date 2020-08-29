package model;

import javax.persistence.*;
import java.io.*;
import java.time.*;

import lombok.*;

@Entity
@Table(name = "borrow")
@Getter
@Setter
@EqualsAndHashCode
public class Borrow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_borrow;
    @Column(name = "date_of_borrow", nullable = false)
    private LocalDate dateOfBorrow;

    @ManyToOne
    @JoinTable(name = "id_book")
    private Book book;

    @ManyToOne
    @JoinTable(name = "id_borrower")
    private Borrower borrower;

}
