package group.springbootdemo.model;

import lombok.*;

import javax.management.ConstructorParameters;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data //getters, setters, requireArgConstructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Empty name")
    private String fname;

    @Min(value = 0, message = "Negative profit")
    private int sum_profit;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude //toString() without this field, otherwise StackOverflowError because of recursive dependency seller→customer→seller...
    private List<Customer> customers;

    public void setCustomers(List<Customer> customers) {
        if (customers != null) {
            customers.forEach(customer -> {
                customer.setSeller(this);
            });
        }

        this.customers = customers;
    }
}
