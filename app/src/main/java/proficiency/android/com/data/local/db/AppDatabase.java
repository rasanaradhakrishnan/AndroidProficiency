package proficiency.android.com.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import proficiency.android.com.data.local.db.dao.ListDataDao;


@Database(entities = {ListDataDao.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ListDataDao listDataDao();
}
