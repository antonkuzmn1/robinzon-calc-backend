package cloud.robinzon.backend.db.net.resources.history;

import cloud.robinzon.backend.db.net.resources.NetEntity;
import cloud.robinzon.backend.security.user.resources.UserEntity;
import cloud.robinzon.backend.tools.HistoryTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.IdClass;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor
@IdClass(NetHistoryKey.class)
public class NetHistory
        extends HistoryTemplate<NetEntity> {

    @Size(min = 5, max = 15)
    @Column(nullable = false, length = 50)
    private String domain;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String subnet;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String mask;

    @NonNull
    @Size(min = 7, max = 15)
    @Column(nullable = false, length = 15)
    private String dns1;

    @Size(min = 7, max = 15)
    @Column(length = 15)
    private String dns2;

    @Size(min = 7, max = 15)
    @Column(length = 15)
    private String dns3;

    @Column(nullable = false,
            columnDefinition = "boolean default false")
    private boolean cloud;

    public NetHistory(NetEntity entity,
                      UserEntity changeBy) {
        this.entity = entity;
        this.changeBy = changeBy;
        this.domain = entity.getDomain();
        this.subnet = entity.getSubnet();
        this.mask = entity.getMask();
        this.dns1 = entity.getDns1();
        this.dns2 = entity.getDns2();
        this.dns3 = entity.getDns3();
        this.cloud = entity.isCloud();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.deleted = entity.isDeleted();
    }

}