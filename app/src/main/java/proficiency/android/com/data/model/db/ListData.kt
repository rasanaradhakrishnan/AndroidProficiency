package proficiency.android.com.data.model.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "listdata")
class ListData {

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title: String? = null

    @Expose
    @PrimaryKey
    var id: Long? = null

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null

    @Expose
    @SerializedName("imageHref")
    @ColumnInfo(name = "imageHref")
    var imageHref: String? = null

}
