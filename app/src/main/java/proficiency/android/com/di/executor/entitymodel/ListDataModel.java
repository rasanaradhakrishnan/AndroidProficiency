package proficiency.android.com.di.executor.entitymodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;

public class ListDataModel  {
    private String  mTitle;

    private List<ListDataRowModel> listData;

    public ListDataModel(String  mTitle, List<ListDataRowModel> listData) {
        this.mTitle = mTitle;
        this.listData = listData;
    }

    public String getmTitle() {
        return mTitle;
    }

    public List<ListDataRowModel> getListData() {
        return listData;
    }
}
