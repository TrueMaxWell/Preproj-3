package app.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @Override
    public String getAuthority() {
        return role;
    }

}
