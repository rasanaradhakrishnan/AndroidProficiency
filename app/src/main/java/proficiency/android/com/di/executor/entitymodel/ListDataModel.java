package proficiency.android.com.di.executor.entitymodel;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;

public class ListDataModel {
    public String  mTitle;

    public List<ListData> listData;

    public ListDataModel(String  mTitle, List<ListData> listData) {
        this.mTitle = mTitle;
        this.listData = listData;
    }
}
