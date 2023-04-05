package com.toru.referead.ui.main


import com.toru.referead.ui.base.IIntent

sealed class MainIntent: IIntent {
    object InitialIntent : MainIntent()
    data class RequestBooksIntent(val searchQuery: String?) : MainIntent()
    object LoadSettingsInfoIntent : MainIntent()
    object TimeoutIntent : MainIntent()
    data class RequestGoodsInfoIntent(val decodedData: String?) : MainIntent()
    data class RequestNotificationIntent(val status: Int) : MainIntent()
//    data class SettingsLoadedInfoIntent(val settingsInfo: SettingsInfo?) : MainIntent()
//    data class ApplyRestrictionsIntent(val settingsInfo: SettingsInfo?) : MainIntent()
}

