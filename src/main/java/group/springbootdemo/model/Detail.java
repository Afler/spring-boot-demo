package group.springbootdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "details")
public class Detail {

    @Id
    @SequenceGenerator(name = "detailSeq", sequenceName = "detail_detail_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detailSeq")
    private int id;

    private int quantity;

    private double price;
}
