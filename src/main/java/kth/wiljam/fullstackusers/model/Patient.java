package kth.wiljam.fullstackusers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date birthdate;

    @OneToOne
    private User user;

    /*
    @OneToMany(mappedBy = "patient")
    @JsonManagedReference(value = "patient-observations")
    private Set<Observation> observations;
    */
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
    /*
    public Set<Observation> getObservations() {
        return observations;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }
     */
}
