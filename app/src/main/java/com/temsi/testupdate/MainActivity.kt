package com.temsi.testupdate

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.dcastalia.localappupdate.DownloadApk
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1001


    val URL = "https://github.com/temsiPatrin/testestUpdate/releases/download/v2/app-debug.apk"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            checkWriteExternalStoragePermission()
        }
    }

    private fun checkWriteExternalStoragePermission() {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            /** If we have permission than we can Start the Download the task  */
            downloadTask()
        } else {
            /** If we don't have permission than requesting  the permission  */
            requestWriteExternalStoragePermission()
        }
    }

    private fun downloadTask() {
        val downloadApk = DownloadApk(this)
        downloadApk.startDownloadingApk(URL)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE && grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            downloadTask();
        } else {
            Toast.makeText(this, "Permission Not Granted.", Toast.LENGTH_SHORT).show();
        }
    }

    private fun requestWriteExternalStoragePermission() {
        if (ActivityCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE
            )
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                MY_PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE
            )
        }
    }

}