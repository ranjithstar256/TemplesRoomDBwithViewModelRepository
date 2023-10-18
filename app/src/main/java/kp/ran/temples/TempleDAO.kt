package kp.ran.temples

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TempleDAO {

    @Insert
    suspend fun addTemple(temple: Temple)

    @Query("SELECT * FROM Temple")
    fun getAllLiveTemples(): LiveData<List<Temple>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(temple: Temple)

    @Update
    suspend fun update(temple: Temple)

    @Delete
    suspend fun delete(temple: Temple)

    /*@Query("SELECT location FROM Students where name like :namex")
    suspend fun getloc(namex:String)
*/

   /*  @Query("SELECT location FROM Students WHERE name LIKE :namex")
    suspend fun getloc(namex: String): String?
    //@Query("select * from Students where name like name")

    @Query("SELECT id FROM Students where name = :itm")
    suspend fun getId(itm:String):Int


*/
}