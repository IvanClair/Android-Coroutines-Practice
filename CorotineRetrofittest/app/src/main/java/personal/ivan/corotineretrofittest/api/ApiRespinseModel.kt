package personal.ivan.corotineretrofittest.api

import com.squareup.moshi.Json

data class UBikeStation(
    /*
        Station Basic Information
     */
    @field:Json(name = "sno") val stationNumber: String?,
    @field:Json(name = "mday") val updateTimestamp: String?,
    @field:Json(name = "act") val closed: String?,
    val lat: String?,
    val lng: String?,
    /*
        Station Address Related Information
     */
    @field:Json(name = "sarea") val stationArea: String?,
    @field:Json(name = "sareaen") val stationAreaEn: String?,
    @field:Json(name = "sna") val stationName: String?,
    @field:Json(name = "snaen") val stationNameEn: String?,
    @field:Json(name = "ar") val address: String?,
    @field:Json(name = "aren") val addressEn: String?,
    /*
        Space Information
     */
    @field:Json(name = "tot") val totalParkingSpace: String?,
    @field:Json(name = "sbi") val availableForRental: String?,
    @field:Json(name = "bemp") val availableSpaceForReturn: String?
) {

    companion object {
        const val DATE_PATTERN_UPDATE = "yyyyMMddHHmmss"
    }
}