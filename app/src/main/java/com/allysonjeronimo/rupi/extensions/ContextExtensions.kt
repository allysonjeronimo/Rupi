package com.allysonjeronimo.rupi.extensions

import android.content.Context

fun Context.resourceId(name: String) : Int{
    return this.resources.getIdentifier(
        name,
        "drawable",
        this.packageName
    )
}