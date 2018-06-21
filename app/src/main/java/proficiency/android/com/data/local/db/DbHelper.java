package proficiency.android.com.data.local.db;

import java.util.List;

import io.reactivex.Observable;
import proficiency.android.com.data.model.db.ListData;


public interface DbHelper {

    Observable<List<ListData>> getAllListData();

    Observable<Boolean> isListDataEmpty();

    Observable<Boolean> saveListData(ListData listData);

    Observable<Boolean> saveListDataList(List<ListData> listDataList);
}
