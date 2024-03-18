package com.example.carly.cars.data.repo.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.carly.cars.data.repo.local.entity.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {
    @Insert
    fun insert(carEntity: CarEntity)

    @Query("SELECT * FROM car ORDER BY id DESC")
    fun getAll(): Flow<List<CarEntity>>

    @Query("SELECT count(id) FROM car")
    fun getCount(): Int

    @Query("SELECT * FROM car WHERE isSelected = 1")
    fun getSelectedFlow(): Flow<List<CarEntity>>

    @Query("SELECT * FROM car WHERE isSelected = 1")
    fun getSelected(): List<CarEntity>

    @Update
    fun update(carEntity: CarEntity)
    @Delete
    fun delete(car: CarEntity)
}
