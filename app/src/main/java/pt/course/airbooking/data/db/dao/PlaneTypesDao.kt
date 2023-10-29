package pt.course.airbooking.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pt.course.airbooking.data.db.model.PlaneTypeDbModel

@Dao
interface PlaneTypesDao {

    @Query("SELECT * FROM plane_types")
    suspend fun getAllPlaneTypes(): List<PlaneTypeDbModel>

    @Query("SELECT * FROM plane_types WHERE id = :id")
    suspend fun getPlaneTypeById(id: Long): PlaneTypeDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaneTypes(planeTypes: List<PlaneTypeDbModel>)

}