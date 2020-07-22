package thiengo.com.br.canalvinciusthiengo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import thiengo.com.br.canalvinciusthiengo.domain.PlayList

@Dao
interface PlayListDao {

    @Query("SELECT * FROM PlayList")
    fun getAll() : List<PlayList>?

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertAll( playLists: List<PlayList> )

    @Query( value = "DELETE FROM PlayList" )
    fun deleteAll()
}