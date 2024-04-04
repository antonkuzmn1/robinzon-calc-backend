package cloud.robinzon.backend.security.user

data class UserInsertForm(
    val username: String,
    val rawPassword: String,
    val fullName: String,
    val title: String,
    val description: String,
    val token: String
)

data class UserUpdateForm(
    val id: Long,
    val username: String,
    val rawPassword: String,
    val fullName: String,
    val title: String,
    val description: String,
    val token: String
)