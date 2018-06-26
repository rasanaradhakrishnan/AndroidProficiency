package proficiency.android.com.di.executor.entitymodel

class ListDataModel(private val mTitle: String, val listData: List<ListDataRowModel>) {

    fun getmTitle(): String {
        return mTitle
    }
}
