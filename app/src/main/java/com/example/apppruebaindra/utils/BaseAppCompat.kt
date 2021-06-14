package com.example.apppruebaindra.utils

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import com.example.apppruebaindra.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseAppCompat: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }


    protected fun nextActivity(activity: Class<*>) {
        this.nextActivity(activity, false)
    }

    protected fun nextActivity(activity: Class<*>, notDestroy: Boolean) {
        this.startActivity(Intent(this, activity))
        if (!notDestroy) {
            this.finish()
        }

    }

    protected fun nextData(activity: Class<*>, bundle: Bundle) {
        this.nextData(activity, bundle, false)
    }

    protected fun nextData(activity: Class<*>, bundle: Bundle, notDestroy: Boolean) {
        val intent = Intent(this, activity)
        intent.putExtras(bundle)
        this.startActivity(intent)
        if (!notDestroy) {
            this.finish()
        }
    }


    protected fun nextDataTransitionAnimation(activity: Class<*>, bundle: Bundle, options: ActivityOptionsCompat, notDestroy: Boolean) {
        val intent = Intent(this, activity)
        intent.putExtras(bundle)
        startActivity(intent, options.toBundle())
        if (!notDestroy) {
            this.finish()
        }
    }

    open fun snackBarFail(msj: String?, rlaContent: View) {
        val snackbar = Snackbar
            .make(rlaContent, msj!!, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_700))
        snackbar.show()
    }


}