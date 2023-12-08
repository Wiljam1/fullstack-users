package kth.wiljam.fullstackusers.model;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @OneToOne
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", birthdate=" + birthdate +
                ", user=" + user +
                '}';
    }
}
