package cloud.robinzon.backend.db.vm.resources.history;

import cloud.robinzon.backend.db.fm.resources.FmEntity;
import cloud.robinzon.backend.db.vm.resources.VmEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@SuppressWarnings("unused")
@Entity
@IdClass(VmHistoryKey.class)
public class VmHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VmEntity vmEntity;

    @Id
    @CreationTimestamp
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
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    public VmHistory(VmEntity vmEntity,
                     String name,
                     Integer cpu,
                     Integer ram,
                     Integer ssd,
                     Integer hdd,
                     Boolean running,
                     FmEntity fmEntity,
                     UserEntity changeBy,
                     boolean isNew) {
        this.vmEntity = vmEntity;
        this.name = name;
        this.cpu = cpu;
        this.ram = ram;
        this.ssd = ssd;
        this.hdd = hdd;
        this.running = running;
        this.title = isNew ? "" : vmEntity.getTitle();
        this.description = isNew ? "" : vmEntity.getDescription();
        this.fmEntity = fmEntity;
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VmHistory(VmEntity vmEntity,
                     String title,
                     String description,
                     UserEntity changeBy) {
        this.vmEntity = vmEntity;
        this.name = vmEntity.getName();
        this.cpu = vmEntity.getCpu();
        this.ram = vmEntity.getRam();
        this.ssd = vmEntity.getSsd();
        this.hdd = vmEntity.getHdd();
        this.running = vmEntity.isRunning();
        this.title = title;
        this.description = description;
        this.fmEntity = vmEntity.getFmEntity();
        this.changeBy = changeBy;
        this.deleted = false;
    }

    public VmHistory(VmEntity vmEntity,
                     UserEntity changeBy) {
        this.vmEntity = vmEntity;
        this.name = vmEntity.getName();
        this.cpu = vmEntity.getCpu();
        this.ram = vmEntity.getRam();
        this.ssd = vmEntity.getSsd();
        this.hdd = vmEntity.getHdd();
        this.running = vmEntity.isRunning();
        this.title = vmEntity.getTitle();
        this.description = vmEntity.getDescription();
        this.fmEntity = vmEntity.getFmEntity();
        this.changeBy = changeBy;
        this.deleted = true;
    }

    public VmHistory() {
    }

    public VmEntity getVmEntity() {
        return vmEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public int getCpu() {
        return cpu;
    }

    public int getRam() {
        return ram;
    }

    public int getSsd() {
        return ssd;
    }

    public int getHdd() {
        return hdd;
    }

    public boolean isRunning() {
        return running;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

}