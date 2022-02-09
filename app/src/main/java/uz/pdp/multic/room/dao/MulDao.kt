package uz.pdp.multic.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import uz.pdp.multic.room.entity.MultClass

@Dao
interface MulDao {

    @Insert(onConflict = REPLACE)
    fun addList(list: List<MultClass>)

}