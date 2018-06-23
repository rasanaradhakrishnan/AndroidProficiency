package proficiency.android.com.data.local.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;

@Dao
public interface ListDataAppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ListData listData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ListData> listData);

    @Query("SELECT * FROM ListData")
    List<ListData> loadAll();
}
