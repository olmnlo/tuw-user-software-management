package org.example.jpa_jpql.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name is required")
    @Size(min = 4, message = "name length must be at least 4 chars")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotBlank(message = "username is required")
    @Size(min = 4, message = "username length must be at least 4 chars")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 8, max = 20, message = "password length must be at least 8 max 20 length")
    @Column(columnDefinition = "varchar(20) not null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "password is week it must has at least one upper/lower case one digit one special character")
    private String password;

    @NotBlank(message = "email is required")
    @Email(message = "email is not valid")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String email;

    @NotBlank(message = "role is required")
    @Pattern(regexp = "(user|admin)", message = "role must be user or admin")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "age is required")
    @Positive(message = "age must be positive number")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
