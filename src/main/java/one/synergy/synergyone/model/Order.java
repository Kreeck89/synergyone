package one.synergy.synergyone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private OrderStatus status;
    private Double value;
    private Currency currency;

    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
