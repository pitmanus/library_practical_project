package model;

import javax.persistence.*;
import java.io.*;
import java.util.*;

import lombok.*;

@Entity
@Table(name = "borrower")
@Getter
@Setter
@EqualsAndHashCode
public class Borrower implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_borrower;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;



    @OneToMany(mappedBy = "borrower")
    private List<Borrow> borrows = new ArrayList<> ();

    @OneToOne
    @JoinColumn(name = "borrower_details_id")
    private BorrowerDetails borrowerDetails;

    public Borrower (long id_borrower, String firstName, String lastName) {
        this.id_borrower = id_borrower;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Borrower (String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Borrower () {
    }

    @Override
    public String toString () {
        return "Borrower{" +
                "id_borrower=" + id_borrower +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
