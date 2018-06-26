package proficiency.android.com.data.local.db;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;
import rx.Observable;


public interface DbHelper {

    Observable<List<ListData>> getAllListData();

    Observable<Boolean> isListDataEmpty();

    Observable<Boolean> saveListDataList(List<ListData> listDataList);
}
