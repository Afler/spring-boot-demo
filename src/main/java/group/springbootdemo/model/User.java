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
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private int id;

    @NotEmpty(message = "Empty name")
    private String name;

    @Min(value = 1, message = "Wrong age")
    private int age;

    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    private List<Auto> autoList;

    // for foreign key injection should override setter
    public void setAutoList(List<Auto> autoList) {
        if (autoList != null){
            autoList.forEach(auto->{
                auto.setUser(this);
            });
        }
        this.autoList = autoList;
    }
}
