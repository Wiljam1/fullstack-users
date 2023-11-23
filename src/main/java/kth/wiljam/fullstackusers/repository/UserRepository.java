package kth.wiljam.fullstackusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import kth.wiljam.fullstackusers.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPatientProfileIsNotNull();
    List<User> findByStaffProfileIsNotNull();

    User findByUsername(String username);
}
