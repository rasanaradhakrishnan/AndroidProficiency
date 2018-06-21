package proficiency.android.com.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import proficiency.android.com.data.model.db.ListData;

public final class ListDataResponse {

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("rows")
    private List<ListData> listData;

}
