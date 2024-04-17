package cloud.robinzon.backend.data.vm.resources.history

import cloud.robinzon.backend.data.fm.resources.FmEntity
import cloud.robinzon.backend.data.vm.resources.VmEntity
import cloud.robinzon.backend.security.user.resources.UserEntity
import jakarta.persistence.*
import java.sql.Timestamp

@Entity
class VmHistory(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val entity: VmEntity? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    val changeBy: UserEntity? = null,

    @Column(nullable = false)
    val timestamp: Timestamp = Timestamp(System.currentTimeMillis()),

    @Column(nullable = false, columnDefinition = "boolean default false")
    val deleted: Boolean = false,

    @Column(nullable = false, length = 50)
    val title: String = "",

    @Column(nullable = false)
    val description: String = "",

    @Column(nullable = false, length = 100)
    val name: String = "",

    @Suppress("unused") @Column(nullable = false)
    val cpu: Int = 0,

    @Suppress("unused") @Column(nullable = false)
    val ram: Int = 0,

    @Suppress("unused") @Column(nullable = false)
    val ssd: Int = 0,

    @Suppress("unused") @Column(nullable = false)
    val hdd: Int = 0,

    @Suppress("unused") @Column(nullable = false)
    val running: Boolean = false,

    @Suppress("unused") @ManyToOne
    @JoinColumn(nullable = false)
    val fmEntity: FmEntity? = null

) {
    constructor(entity: VmEntity, changeBy: UserEntity) : this(
        null,
        entity,
        changeBy,
        Timestamp(System.currentTimeMillis()),
        entity.deleted,
        entity.title,
        entity.description,
        entity.name,
        entity.cpu,
        entity.ram,
        entity.ssd,
        entity.hdd,
        entity.running,
        entity.fmEntity
    )
}