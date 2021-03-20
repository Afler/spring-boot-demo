package group.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private int id;

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

    private int status;
}
