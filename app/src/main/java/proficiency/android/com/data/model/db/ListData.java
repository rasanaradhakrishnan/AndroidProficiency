package proficiency.android.com.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "listdata")
public class ListData {

    @Expose
    @SerializedName("title")
    @ColumnInfo(name = "title")
    public String title;

    @Expose
    @PrimaryKey
    public Long id;

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    public String description;

    @Expose
    @SerializedName("imageHref")
    @ColumnInfo(name = "imageHref")
    public String imageHref;

}
