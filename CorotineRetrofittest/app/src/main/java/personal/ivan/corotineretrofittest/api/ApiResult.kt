package personal.ivan.corotineretrofittest.api

/**
 * @property status [ApiStatus]
 * @property data   response model from the API
 */
data class ApiResult<out T>(
    val status: ApiStatus,
    val data: T?
) {
    companion object {

        /**
         * Success
         */
        fun <T> success(data: T?): ApiResult<T> =
            ApiResult(
                status = ApiStatus.SUCCESS,
                data = data
            )

        /**
         * Fail
         */
        fun <T> fail(): ApiResult<T> =
            ApiResult(
                status = ApiStatus.FAIL,
                data = null
            )

        /**
         * Loading
         */
        fun <T> loading(): ApiResult<T> =
            ApiResult(
                status = ApiStatus.LOADING,
                data = null
            )
    }
}

enum class ApiStatus {
    SUCCESS,
    FAIL,
    LOADING,
}