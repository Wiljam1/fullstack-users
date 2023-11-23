package kth.wiljam.fullstackusers.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "STAFF")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialty;

    private boolean isDoctor;

    @OneToOne
    private User user;
    /*
    @OneToMany(mappedBy = "performer")
    @JsonManagedReference(value = "doctor-observations")
    private Set<Observation> observations;
    */
    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
