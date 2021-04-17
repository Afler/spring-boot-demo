package group.springbootdemo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data //getters, setters, requireArgConstructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "customers")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    @NotEmpty(message = "Empty name")
    private String name;

    @Min(value = 0, message = "Wrong status")
    private int status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Customer(@NotEmpty(message = "Empty name") String username,
                    @NotEmpty(message = "Empty password") String password,
                    boolean active,
                    Role role,
                    @NotEmpty(message = "Empty name") String name,
                    @Min(value = 0, message = "Wrong status") int status,
                    Seller seller) {
        super(username, password, active, role);
        this.name = name;
        this.status = status;
        this.seller = seller;
    }
}
