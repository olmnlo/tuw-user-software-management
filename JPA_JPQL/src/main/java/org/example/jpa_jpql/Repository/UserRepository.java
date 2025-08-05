package org.example.jpa_jpql.Repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.jpa_jpql.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    User findUserByUsername(@NotBlank(message = "username is required") @Size(min = 4, message = "username length must be at least 4 chars") String username);

    @Query("select u from User u where u.username=:username and u.password=:password")
    User findUserByUsernameAndPassword(String username, String password);

    @Query("select u from User u where u.email=:email")
    User findUserByEmail(String email);

    @Query("select u from User u where u.role=:role")
    List<User> getUsersByRole(String role);

    List<User> findUserByAgeGreaterThanEqual(Integer ageIsGreaterThan);
}
