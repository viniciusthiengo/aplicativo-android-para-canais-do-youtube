package thiengo.com.br.canalvinciusthiengo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo

@Dao
interface LastVideoDao {

    @Query( value = "SELECT * FROM LastVideo LIMIT 1" )
    fun get() : LastVideo?

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert( lastVideo: LastVideo )

    @Query( value = "DELETE FROM LastVideo" )
    fun delete()
}