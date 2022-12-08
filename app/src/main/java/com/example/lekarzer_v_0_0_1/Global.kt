package com.example.lekarzer_v_0_0_1

import android.app.Application

public class Global : Application() {
    companion object {
        @JvmField
        var global_token: String = "defaultValue"
    }
}