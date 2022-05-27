package com.example.sunmadinepal.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.sunmadinepal.R
import com.mobsandgeeks.saripaar.ValidationError
import org.joda.time.Days
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormatter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}

fun FragmentActivity.windowWidth(): Int {
    val displayRectangle = Rect()
    window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
    return displayRectangle.width()
}

private var progressDialog: AlertDialog? = null
fun FragmentActivity.showProgressDialog() {
    val view = layoutInflater.inflate(R.layout.progress_dialog, null)
    progressDialog = AlertDialog.Builder(this)
        .setView(view)
        .setCancelable(false)
        .create()
    progressDialog?.show()
    //Dialog window is set only after it is shown
    progressDialog?.window?.setLayout(
        (windowWidth() * 0.70).toInt(),
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
}

fun hideProgressDialog() {
    progressDialog?.dismiss()
}

fun FragmentActivity.showToast(message  :String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun FragmentActivity.onErrorTextFieldValidator(errors: MutableList<ValidationError>?) {
    errors?.let {
        for (error in errors) {
            val view = error.view
            val message = error.getCollatedErrorMessage(this)

            when (view) {
                is AppCompatEditText -> {
                    view.error = message
                }
                is AppCompatAutoCompleteTextView -> {
                    view.error = message
                }
                else -> {
                    showToast(message)
                }
            }
        }
    }
}


fun dateDifferenceBetweenTwoDays(startDate : String, endDate :String): Int {

    val format = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)
    try {
        val sDate = format.parse(startDate)
        val eDate = format.parse(endDate)
        return Days.daysBetween(LocalDate(sDate), LocalDate(eDate)).days
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return 0
}

@SuppressLint("SimpleDateFormat")
fun currentDateFormat(): String {
    val calendar = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("yyyy/MM/dd")
    return simpleDateFormat.format(calendar.time)
}

fun FragmentActivity.changeStatusBarIconTextColor(boolean: Boolean) {
    WindowCompat.getInsetsController(window!!, window!!.decorView)?.apply {
        isAppearanceLightStatusBars = boolean
    }
}

fun FragmentActivity.changeStatusBarColor(){
    window?.statusBarColor = ContextCompat.getColor(this, android.R.color.white)

}