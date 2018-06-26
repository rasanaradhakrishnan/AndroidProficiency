package proficiency.android.com.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import proficiency.android.com.data.model.db.ListData

class ListDataResponse {

    @Expose
    @SerializedName("title")
    val title: String? = null

    @Expose
    @SerializedName("rows")
    val listData: List<ListData>? = null
}
