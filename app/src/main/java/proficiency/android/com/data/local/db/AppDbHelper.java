package proficiency.android.com.data.local.db;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import proficiency.android.com.data.model.db.ListData;
import rx.Observable;


@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<ListData>> getAllListData() {
        return Observable.fromCallable(new Callable<List<ListData>>() {
            @Override
            public List<ListData> call() throws Exception {
                return mAppDatabase.listDataDao().loadAll();
            }
        });
    }

    @Override
    public Observable<Boolean> isListDataEmpty() {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return mAppDatabase.listDataDao().loadAll().isEmpty();
            }
        });
    }

    @Override
    public Observable<Boolean> saveListDataList(final List<ListData> listDataList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.listDataDao().insertAll(listDataList);
                return true;
            }
        });
    }

}
