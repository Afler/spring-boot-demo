package group.springbootdemo.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @SequenceGenerator(name = "orderSeq", sequenceName = "order_order_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderSeq")
    private int id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "detail_id")
    private Detail detail;

    @Min(value = 0, message = "Negative quantity")
    private int quantity;

    private Date date;

    @Min(value = 0, message = "Negative cost")
    private double cost;
}
