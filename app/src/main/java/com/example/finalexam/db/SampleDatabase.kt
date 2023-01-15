package com.example.finalexam.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalexam.config.Config
import com.example.finalexam.dao.SampleDao
import com.example.finalexam.models.Question

//@Database(entities = [Question::class], version = Config.DATABASE_VERSION)
//abstract class SampleDatabase : RoomDatabase() {
//    abstract fun sampleDao(): SampleDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: SampleDatabase? = null
//
//        fun getDatabase(context: Context): SampleDatabase {
//
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    SampleDatabase::class.java,
//                    Config.DATABASE_NAME
//                ).allowMainThreadQueries().build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
//}