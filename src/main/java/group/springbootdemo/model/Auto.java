package group.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "autos")
public class Auto {

    @Id
    @SequenceGenerator(name = "autoSeq", sequenceName = "autos_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autoSeq")
    private int id;

    private String model;
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
