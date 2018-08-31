package one.synergy.synergyone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Sex sex;
    private Integer itn;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> order;
}
