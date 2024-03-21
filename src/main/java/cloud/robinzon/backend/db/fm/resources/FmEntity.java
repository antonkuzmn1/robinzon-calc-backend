package cloud.robinzon.backend.db.fm.resources;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class FmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String specifications;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean vm;

    @ManyToOne
    @JoinColumn
    private ClientEntity client;

    @Setter
    @Column(nullable = false)
    private boolean deleted;

    public FmEntity(String name,
                    String ip,
                    String title,
                    String specifications,
                    String description,
                    int price,
                    boolean vm,
                    ClientEntity client) {
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
        this.client = client;
    }

    public void update(String name,
                       String ip,
                       String title,
                       String specifications,
                       String description,
                       int price,
                       boolean vm) {
        this.name = name;
        this.ip = ip;
        this.title = title;
        this.specifications = specifications;
        this.description = description;
        this.price = price;
        this.vm = vm;
    }
}
