package com.sercan.yigit.wearos.data.transfer

import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.data.FreezableUtils
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.WearableListenerService
import com.sercan.yigit.wearos.data.transfer.MainActivity.Companion.EXTRA_MESSAGE_FROM_WEAR
import com.sercan.yigit.wearos.data.transfer.MainActivity.Companion.PATH_FOR_MOBILE

class WearDataListenerService : WearableListenerService() {
    private val TAG = "WearListenerService"

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        super.onDataChanged(dataEvents)
        val events: List<DataEvent> = FreezableUtils.freezeIterable(dataEvents)
        dataEvents.close()
        for (event in events) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val path = event.dataItem.uri.path
                if (PATH_FOR_MOBILE == path) {
                    val dataMapItem = DataMapItem.fromDataItem(event.dataItem)
                    val wearDeviceMessage = dataMapItem.dataMap.getString(EXTRA_MESSAGE_FROM_WEAR)
                    Toast.makeText(
                        this,
                        "Wear device get message :$wearDeviceMessage",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.i(TAG, "unknown path")
                }
            }
        }
    }
}
