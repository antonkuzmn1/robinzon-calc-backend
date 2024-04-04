package cloud.robinzon.backend.data.vpn.type

data class VpnTypeInsertForm(
    val name: String,
    val token: String
)

data class VpnTypeUpdateForm(
    val id: Long,
    val name: String,
    val token: String
)