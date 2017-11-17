package ml.mmrc.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
public class User {
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
    @Column(name = "email") private String email;
    @Column(name = "password") private String password;
    @Column(name = "created_at") private Timestamp createdAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("{id: %d, email: %s, password: %s, createdAt: %s}", id, email, password, createdAt.toString()) +
                comments.stream().map(c -> String.format("\n\t{id: %d, comment: %s}", c.getId(), c.getComment())).collect(Collectors.joining(""));
    }
}
