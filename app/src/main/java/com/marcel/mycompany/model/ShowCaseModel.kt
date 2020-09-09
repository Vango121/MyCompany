package com.marcel.mycompany.model

import android.view.View
import javax.inject.Inject

data class ShowCaseModel @Inject constructor(val title:String, val content:String, val view:View) {
}