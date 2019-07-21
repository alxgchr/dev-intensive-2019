package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.inputmethodservice.Keyboard
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService


fun Activity.hideKeyboard() {
    val view = currentFocus
    view?.let {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

//fun Activity.isKeyboardOpen(): Boolean {
//
//    val r = Rect()
//    val rootView = window.decorView
//    rootView.getWindowVisibleDisplayFrame(r)
//    val screenHeight = rootView.height
//    var heightDiff = screenHeight - (r.bottom - r.top)
//    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//    if (resourceId > 0) {
//        val size = resources.getDimensionPixelSize(resourceId)
//        heightDiff -= resources.getDimensionPixelSize(resourceId)
//    }
//    if (heightDiff > 100) {
//        return true
//    }
//    return false
//}

fun Activity.isKeyboardOpen(): Boolean {

    val rect = Rect()
    val rootView = window.decorView
    rootView.getWindowVisibleDisplayFrame(rect)
    val screenHeight = rootView.height
    val heightDiff = screenHeight - rect.bottom
    if (heightDiff > 100) {
        return true
    }
    return false
}

fun Activity.isKeyboardClosed() = !isKeyboardOpen()