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

    public BorrowerDetails (long id_borrower_details, String address, String tel, String email) {
        this.id_borrower_details = id_borrower_details;
        this.address = address;
        this.tel = tel;
        this.email = email;

    }

    public BorrowerDetails (String address, String tel, String email) {
        this.address = address;
        this.tel = tel;
        this.email = email;

    }

    public BorrowerDetails () {
    }

    @Override
    public String toString () {
        return "BorrowerDetails{" +
                "id_borrower_details=" + id_borrower_details +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
