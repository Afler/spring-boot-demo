package group.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data //getters, setters, requireArgConstructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @SequenceGenerator(name = "customerSeq", sequenceName = "customer_customer_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSeq")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Min(value = 0, message = "Wrong status")
    private int status;

    @NotEmpty(message = "Empty name")
    private String fname;
}
