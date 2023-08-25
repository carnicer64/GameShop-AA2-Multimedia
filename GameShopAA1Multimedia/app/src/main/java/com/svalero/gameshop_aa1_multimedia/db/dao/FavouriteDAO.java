package com.svalero.gameshop_aa1_multimedia.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.gameshop_aa1_multimedia.domain.Favourite;

import java.util.List;

@Dao
public interface FavouriteDAO {

    @Query("SELECT * FROM favourite WHERE idClient= :idClient")
    List<Favourite> getAll(long idClient);

    @Query("SELECT * FROM favourite WHERE idProduct =:idProduct AND idClient= :idClient")
    Favourite getFavById(long idProduct, long idClient);

    @Insert
    void insert(Favourite favourite);

    @Delete
    void delete(Favourite favourite);

    @Update
    void update(Favourite favourite);
}
