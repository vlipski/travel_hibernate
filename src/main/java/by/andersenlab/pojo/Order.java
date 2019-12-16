package by.andersenlab.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity(name = "`order`")
public class Order implements Serializable {

    @Transient
    private final static long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Tour tour;

    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
    }
}
