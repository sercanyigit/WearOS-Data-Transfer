package com.sercan.yigit.wearos.data.transfer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.Wearable
import com.sercan.yigit.wearos.data.transfer.ui.theme.WearOSDataTransferTheme
import java.util.Date

class MainActivity : ComponentActivity(), DataClient.OnDataChangedListener {

    private val dataClient by lazy { Wearable.getDataClient(this) }

    companion object {
        const val PATH_FOR_WEAR = "/path_for_wear"
        const val PATH_FOR_MOBILE = "/path_for_mobile"
        const val EXTRA_CURRENT_TIME = "extra_current_time"
        const val EXTRA_MESSAGE_FROM_WEAR = "extra_message_from_wear"
        const val EXTRA_MESSAGE_FROM_MOBILE = "extra_message_from_mobile"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var textfield by remember { mutableStateOf("") }
            WearOSDataTransferTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = textfield,
                        label = {
                            Text(text = "enter data")
                        },
                        onValueChange = {
                            textfield = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(30.dp)
                    )
                    Button(
                        onClick = {
                            sendDataToWearApp(data = textfield)
                        },
                        modifier = Modifier.fillMaxWidth().padding(30.dp).height(60.dp)
                    ) {
                        Text("Send Wear Data")
                    }


                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dataClient.addListener(this)
    }

    override fun onPause() {
        super.onPause()
        dataClient.removeListener(this)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this@MainActivity, WearDataListenerService::class.java)
        startService(intent)
    }

    private fun sendDataToWearApp(data: String) {
        val dataMapRequest = PutDataMapRequest.create(PATH_FOR_WEAR)
        val map = dataMapRequest.dataMap
        map.putString(EXTRA_MESSAGE_FROM_MOBILE, data)
        map.putLong(EXTRA_CURRENT_TIME, Date().time)
        val putDataRequest = dataMapRequest.asPutDataRequest()
        putDataRequest.setUrgent()
        dataClient.putDataItem(putDataRequest)
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        dataEvents.map { dataEvent ->
            Log.e("DATAEVENT", dataEvent.dataItem.toString())
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WearOSDataTransferTheme {

    }
}