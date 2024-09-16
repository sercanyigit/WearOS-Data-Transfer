package com.sercan.yigit.wearos.data.transfer.presentation

import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.data.FreezableUtils
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import com.sercan.yigit.wearos.data.transfer.presentation.MainActivity.Companion.EXTRA_MESSAGE_FROM_MOBILE
import com.sercan.yigit.wearos.data.transfer.presentation.MainActivity.Companion.PATH_FOR_WEAR

class MobileDataListenerService : WearableListenerService(){
    private val TAG = "MobileDataListenerService"

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        super.onDataChanged(dataEvents)
        val events: List<DataEvent> = FreezableUtils.freezeIterable(dataEvents)
        dataEvents.close()
        for (event in events) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val path = event.dataItem.uri.path
                if (PATH_FOR_WEAR == path) {
                    val dataMapItem = DataMapItem.fromDataItem(event.dataItem)
                    val mobileDeviceMessage = dataMapItem.dataMap.getString(EXTRA_MESSAGE_FROM_MOBILE)
                    Toast.makeText(
                        this,
                        "Mobile device get message :$mobileDeviceMessage",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.i(TAG, "unknown path")
                }
            }
        }
    }
}
