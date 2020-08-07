package thiengo.com.br.canalvinciusthiengo.data.dynamic

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import thiengo.com.br.canalvinciusthiengo.model.LastVideo

/*
 * Interface de configuração de acesso à tabela
 * LastVideo da persistência local, Room API.
 * */
@Dao
interface LastVideoDao {

    /**
     * Salva na base local o último vídeo
     * liberado em canal.
     *
     * Caso tenha conflito de identificadores
     * único (por ser o mesmo vídeo), então
     * salva os dados recém obtidos por cima dos
     * já presentes em base. É esse tipo de
     * estratégia que mantém a thumb, o título
     * e a descrição do vídeo em dia com o que
     * há disponível em canal no YouTube.
     *
     * @param lastVideo vídeo.
     */
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    fun insert( lastVideo: LastVideo )

    /**
     * Retorna o único LastVideo salvo em Room
     * (SQLite) ou null caso não tenha ainda
     * algum.
     *
     * @return o único LastVideo em base.
     */
    @Query( value = "SELECT * FROM LastVideo LIMIT 1" )
    fun get() : LastVideo?

    /**
     * Remove todos os dados LastVideo
     * presentes em base local. Isso deve ocorre
     * sempre antes de invocar o método
     * insert() para garantir que somente um
     * único conjunto de dados de "último
     * vídeo" permaneça no aplicativo.
     */
    @Query( value = "DELETE FROM LastVideo" )
    fun delete()
}