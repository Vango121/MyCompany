package com.marcel.mycompany

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.marcel.mycompany.screens.workers.Worker

class Converters {
    @TypeConverter
    fun convertListOfWorkers(list: List<Worker>):String{
        val gson = Gson()
        val converted = gson.toJson(list)
        return converted
    }
   @TypeConverter
   fun jsonToListWorkers(json: String):List<Worker>{
       val listType = object : TypeToken<List<String?>?>() {}.type
       return Gson().fromJson(json,listType)
   }
}