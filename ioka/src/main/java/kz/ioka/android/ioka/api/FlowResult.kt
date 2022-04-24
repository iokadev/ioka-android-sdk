package kz.ioka.android.ioka.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class FlowResult : Parcelable {

    @Parcelize
    object Cancelled : FlowResult()

    @Parcelize
    object Succeeded : FlowResult()

    @Parcelize
    class Failed(val cause: String) : FlowResult()

}