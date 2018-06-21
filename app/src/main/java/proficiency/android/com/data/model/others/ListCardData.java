package proficiency.android.com.data.model.others;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;


public class ListCardData {

    public String  mTitle;

    public List<ListData> listData;

    public ListCardData(String  mTitle, List<ListData> listData) {
        this.mTitle = mTitle;
        this.listData = listData;
    }
}
