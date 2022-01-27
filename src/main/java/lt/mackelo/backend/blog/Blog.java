package lt.mackelo.backend.blog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lt.mackelo.backend.person.Person;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table
public class Blog {

    @Id
    @SequenceGenerator(
            name = "blog_sequence",
            sequenceName = "blog_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "blog_sequence"
    )

    private Long id;
    private String title;
    @Column(length = 5000)
    private String text;

    @ManyToOne
    @JoinColumn(name="person_id", nullable = false)
    private Person person;

    public Blog() {
    }

    public Blog(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Blog(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
