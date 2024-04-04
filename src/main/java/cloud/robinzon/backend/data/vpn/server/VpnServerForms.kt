package cloud.robinzon.backend.data.vpn.server

import cloud.robinzon.backend.data.net.resources.NetEntity
import cloud.robinzon.backend.data.vpn.type.resources.VpnTypeEntity

data class VpnServerInsertForm(
    val title: String,
    val description: String,
    val ip: String,
    val publicKey: String,
    val netEntity: NetEntity,
    val vpnTypeEntity: Set<VpnTypeEntity>,
    val token: String
)

data class VpnServerUpdateForm(
    val id: Long,
    val title: String,
    val description: String,
    val ip: String,
    val publicKey: String,
    val netEntity: NetEntity,
    val vpnTypeEntity: Set<VpnTypeEntity>,
    val token: String
)