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
    @JoinColumn(name = "id_book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    public Borrow (long id_borrow, LocalDate dateOfBorrow) {
        this.id_borrow = id_borrow;
        this.dateOfBorrow = dateOfBorrow;

    }

    public Borrow (LocalDate dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public Borrow () {
    }

    @Override
    public String toString () {
        return "Borrow{" +
                "id_borrow=" + id_borrow +
                ", dateOfBorrow=" + dateOfBorrow +
                ", book=" + book +
                ", borrower=" + borrower +
                '}';
    }
}
