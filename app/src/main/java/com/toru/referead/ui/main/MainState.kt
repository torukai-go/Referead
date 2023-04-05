package com.toru.referead.ui.main

import com.toru.referead.ui.base.IState

sealed class MainState : IState {
    object InitialState : MainState()
    object IdleState : MainState()
    data class LoadingState(val network: String?) : MainState()
    data class EnableScannerState(val enabled: Boolean) : MainState()
    data class UpdateCommercialNetworkState(val network: String?) : MainState()
    data class  ContentNotFound(val network: String?, val noPrice: Boolean = false) : MainState()
//    object ErrorLoadingSettingsState : MainState()
//    data class ErrorState(val message: String?, val network: String?) : MainState()
//    data class ContentState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockMNState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockIRState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockPDState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockCardState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentStockMultipackState(val info: GoodsInfo?, val network: String?) : MainState()
//    data class ContentPlacementState(val info: GoodsInfo?, val network: String?, val prevState: MainState) : MainState()
}
