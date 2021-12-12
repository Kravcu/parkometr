package pl.edu.agh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "user.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
        @NamedQuery(name = "user.updatePassword", query = "UPDATE User u SET u.password = :password WHERE u.login = :login")
})
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String login;

    private String password;

    private String role;

    @Column(name = "section_number")
    private long sectionNumber;
}
