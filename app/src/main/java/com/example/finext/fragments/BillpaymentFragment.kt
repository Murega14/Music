package com.example.finext.fragments

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import com.example.finext.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BillpaymentFragment : Fragment() {
    private lateinit var database: DatabaseReference
    private var billAmount by mutableStateOf("")
    private var dueDate by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = FirebaseDatabase.getInstance().reference
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column {
                    Text("Bill Amount: $billAmount")
                    Text("Due Date: $dueDate")
                    Button(onClick = { saveBillData() }) {
                        Text("Save Bill")
                    }
                }
            }
        }
    }

    private fun saveBillData() {
        val billId = database.child("bills").push().key
        val billData = mapOf(
            "amount" to billAmount,
            "dueDate" to dueDate
        )

        if (billId != null) {
            database.child("bills").child(billId).setValue(billData)
                .addOnSuccessListener {
                    // Data saved successfully
                    setNotification(requireContext())
                }
                .addOnFailureListener {
                    // Error occurred while saving data
                }
        }
    }

    private fun setNotification(context: Context) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dueDateObj = LocalDate.parse(dueDate, formatter)
        val notificationDate = dueDateObj.minusDays(2)

        val channelId = "bill_notification_channel"
        val notificationId = 1

        val channel = NotificationChannel(
            channelId,
            "Bill Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Bill Reminder")
            .setContentText("Your bill is due in 2 days.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(requireContext())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        )  {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
