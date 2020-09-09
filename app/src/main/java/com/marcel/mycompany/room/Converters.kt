package com.marcel.mycompany.room

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcel.mycompany.model.Worker

class Converters {
    companion object{
        @JvmStatic
        @TypeConverter
        fun convertListOfWorkers(list: List<Worker>):String{
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