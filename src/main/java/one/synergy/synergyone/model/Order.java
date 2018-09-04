package one.synergy.synergyone.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
//@Builder
@DynamicUpdate
@NoArgsConstructor
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
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

}
