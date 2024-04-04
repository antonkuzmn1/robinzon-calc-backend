package cloud.robinzon.backend.data.vpn.user

import cloud.robinzon.backend.data.vpn.server.resources.VpnServerEntity
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity

data class VpnUserInsertForm(
    val vpnServerEntity: VpnServerEntity,
    val vpnTypeEntity: VpnTypeEntity,
    val ip: String,
    val username: String,
    val password: String,
    val fullName: String,
    val title: String,
    val description: String,
    val token: String
)

data class VpnUserUpdateForm(
    val id: Long,
    val vpnServerEntity: VpnServerEntity,
    val vpnTypeEntity: VpnTypeEntity,
    val ip: String,
    val username: String,
    val password: String,
    val fullName: String,
    val title: String,
    val description: String,
    val token: String
)