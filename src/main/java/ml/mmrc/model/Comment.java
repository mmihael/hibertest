package ml.mmrc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Column(name = "id") @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private long id;
    @Column(name = "comment") private String comment;
    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name = "user_id") private User user;
    public Comment (String comment, User user) { this.comment = comment; this.user = user; }
}
