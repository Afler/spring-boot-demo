package group.springbootdemo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data //getters, setters, requireArgConstructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @SequenceGenerator(name = "sellerSeq", sequenceName = "seller_seller_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sellerSeq")
    private int id;

    @NotEmpty(message = "Empty name")
    private String fname;

    @Min(value = 0, message = "Negative profit")
    private int sum_profit;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude //toString() without this field, otherwise StackOverflowError
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
