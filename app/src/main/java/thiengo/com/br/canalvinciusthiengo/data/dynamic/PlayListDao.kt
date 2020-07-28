package thiengo.com.br.canalvinciusthiengo.data.dynamic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import thiengo.com.br.canalvinciusthiengo.model.PlayList

/*
 * Interface de configuração de acesso à tabela
 * PlayList da persistência local, Room API.
 * */
@Dao
interface PlayListDao {

    /**
     * Salva na base local todas as PlayLists do
     * canal obtidas até então.
     *
     * Caso tenha conflito de identificadores
     * único (por ser a mesma PlayList), então
     * salva os dados recém obtidos por cima dos
     * já presentes em base. É esse tipo de
     * estratégia que mantém o título da PlayList
     * em dia com o que há disponível em canal
     * no YouTube.
     *
     * @param playLists lista não mutável de
     * PlayLists.
     */
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insertAll( playLists: List<PlayList> )

    /**
     * Retorna todas as PlayLists salvas em Room
     * (SQLite) ou null caso não tenha ainda
     * alguma PlayList em base.
     *
     * @return lista não mutável de PlayLists em
     * base.
     */
    @Query("SELECT * FROM PlayList")
    fun getAll() : List<PlayList>?

    /**
     * Remove todos os dados PlayList
     * presentes em base local. Isso ocorre
     * sempre antes de invocar o método
     * insertAll() para garantir que somente
     * o conjunto mais atual de PlayLists
     * do canal permaneça no aplictivo.
     */
    @Query( value = "DELETE FROM PlayList" )
    fun deleteAll()
}