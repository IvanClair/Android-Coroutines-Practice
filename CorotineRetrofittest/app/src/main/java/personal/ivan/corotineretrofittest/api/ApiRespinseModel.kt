package personal.ivan.corotineretrofittest.api

import com.squareup.moshi.Json

data class UBikeStation(
    /*
        Station Basic Information
     */
    @Json(name = "sno") val stationNumber: String?,
    @Json(name = "mday") val updateTimestamp: String?,
    @Json(name = "act") val closed: String,
    val lat: String?,
    val lng: String?,
    /*
        Station Address Related Information
     */
    @Json(name = "sarea") val stationArea: String?,
    @Json(name = "sareaen") val stationAreaEn: String?,
    @Json(name = "sna") val stationName: String?,
    @Json(name = "snaen") val stationNameEn: String?,
    @Json(name = "ar") val address: String?,
    @Json(name = "aren") val addressEn: String?,
    /*
        Space Information
     */
    @Json(name = "tot") val totalParkingSpace: String?,
    @Json(name = "sbi") val availableForRental: String?,
    @Json(name = "bemp") val availableSpaceForReturn: String?
) {

    companion object {
        const val DATE_PATTERN_UPDATE = "yyyyMMddHHmmss"
    }
}