package group.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data //getters, setters, constructor(finalField1, finalField2 ...), hashCode(), toString(), equals()
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "jpaSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jpaSeq")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Empty name")
    private String name;

    @Column(name = "age")
    @Min(value = 1, message = "Wrong age")
    private int age;
}
