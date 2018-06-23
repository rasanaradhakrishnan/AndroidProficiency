package proficiency.android.com.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import proficiency.android.com.data.local.db.dao.ListDataAppDao;
import proficiency.android.com.data.model.db.ListData;


@Database(entities = {ListData.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ListDataAppDao listDataDao();
}
