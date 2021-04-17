package group.springbootdemo.model;

import lombok.*;

import javax.management.ConstructorParameters;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data //getters, setters, requireArgConstructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "sellers")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Seller extends User {

    @NotEmpty(message = "Empty name")
    private String name;

    @Min(value = 0, message = "Negative profit")
    private int sum_profit;

    //@ToString.Exclude//toString() without this field, otherwise StackOverflowError because of recursive dependency seller→customer→seller...
    @OneToMany(mappedBy = "seller", cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<Customer> customers;

    public Seller(@NotEmpty(message = "Empty name") String username,
                  @NotEmpty(message = "Empty password") String password,
                  boolean active,
                  Role role,
                  @NotEmpty(message = "Empty name") String name,
                  @Min(value = 0, message = "Negative profit") int sum_profit) {
        super(username, password, active, role);
        this.name = name;
        this.sum_profit = sum_profit;
    }
}
