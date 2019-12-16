package by.andersenlab.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Tour implements Serializable {

    @Transient
    private final static long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Date dateFrom;

    @Column
    private Date dateTo;

    @Column
    private String name;

    @OneToMany(mappedBy = "tour")
    private List<Order> orderList;
}
