package com.example.apppruebaindra.utils

import android.view.View

/**
 * Created by Luis Lopez on 6/13/21.
 * Solera Mobile
 * Peru, Lima.
 */

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}