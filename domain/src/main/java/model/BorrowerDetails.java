package model;

import javax.persistence.*;
import java.io.*;

import lombok.*;

@Entity
@Table(name = "borrower_details")
@Getter
@Setter
@EqualsAndHashCode
public class BorrowerDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_borrower_details;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String email;

    @OneToOne
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

}
