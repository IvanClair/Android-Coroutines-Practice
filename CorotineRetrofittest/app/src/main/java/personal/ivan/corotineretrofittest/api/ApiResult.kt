package personal.ivan.corotineretrofittest.api

/**
 * @property status [ApiStatus]
 * @property data   response model from the API
 */
data class ApiResult<out T>(
    val status: ApiStatus,
    val data: T?
)

enum class ApiStatus {
    SUCCESS,
    FAIL,
    LOADING,
}