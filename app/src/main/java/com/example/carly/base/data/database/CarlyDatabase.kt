package com.example.carly.base.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carly.cars.data.repo.local.CarDao
import com.example.carly.cars.data.repo.local.entity.CarEntity

@Database(entities = [CarEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CarlyDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao

    companion object {
        //        @Volatile
//        private var inastance: CarlyDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context): CarlyDatabase {
//            return inastance ?: synchronized(LOCK) {
//                inastance ?: Builddatabase(context).also {
//                    inastance = it
//                }
//            }
//        }
//
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CarlyDatabase::class.java,
                "CarlyDB"
            ).build()
    }
}