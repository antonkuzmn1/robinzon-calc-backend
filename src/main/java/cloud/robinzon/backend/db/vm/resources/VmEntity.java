package cloud.robinzon.backend.db.vm.resources;

import cloud.robinzon.backend.db.client.resources.ClientEntity;
import cloud.robinzon.backend.db.fm.resources.FmEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@Getter
@NoArgsConstructor
public class VmEntity {

    @Id
    @Column(length = 36)
    private String id;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private int cpu;

    @Column(nullable = false)
    private int ram;

    @Column(nullable = false)
    private int ssd;

    @Column(nullable = false)
    private int hdd;

    @Column(nullable = false)
    private boolean running;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FmEntity fmEntity;

    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Column(nullable = false)
    private boolean deleted;

    public VmEntity(String id,
                    String name,
                    int cpu,
                    int ram,
                    int ssd,
                    int hdd,
                    boolean running,
                    FmEntity fmEntity) {
        this.id = id;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.fmEntity = fmEntity;
    }

    public void update(String name,
                       int cpu,
                       int ram,
                       int ssd,
                       int hdd,
                       boolean running,
                       FmEntity fmEntity) {
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.fmEntity = fmEntity;
    }

    public void update2(String title,
                        String description) {
        this.title = title;
        this.description = description;
    }

}