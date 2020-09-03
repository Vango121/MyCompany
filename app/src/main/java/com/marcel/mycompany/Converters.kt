package com.marcel.mycompany

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcel.mycompany.screens.workers.Worker

class Converters {
    companion object{
        @JvmStatic
        @TypeConverter
        fun convertListOfWorkers(list: List<Worker>):String{
            Log.i("convert",Gson().toJson(list))
            return Gson().toJson(list)
        }
        @JvmStatic
        @TypeConverter
        fun jsonToListWorkers(json: String):List<Worker>{
            val listType = object : TypeToken<List<Worker>>(){}.type
            return Gson().fromJson(json,listType)
        }
    }

}