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

    @OneToOne(mappedBy = "borrower")
    private BorrowerDetails borrowerDetails;

}
