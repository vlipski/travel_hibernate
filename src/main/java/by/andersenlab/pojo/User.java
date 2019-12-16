package by.andersenlab.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User implements Serializable {

    @Transient
    private final static long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String password;

    @Column
    private String login;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;

    public User(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public User(Long id, String password, String login) {
        this.id = id;
        this.password = password;
        this.login = login;
    }
}
