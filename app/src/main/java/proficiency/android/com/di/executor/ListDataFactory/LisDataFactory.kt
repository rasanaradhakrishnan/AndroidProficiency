package proficiency.android.com.di.executor.ListDataFactory

import java.util.ArrayList

import proficiency.android.com.data.ListDataListener
import proficiency.android.com.data.model.api.ListDataResponse
import proficiency.android.com.data.model.db.ListData
import proficiency.android.com.di.executor.entitymodel.ListDataModel
import proficiency.android.com.di.executor.entitymodel.ListDataRowModel
import proficiency.android.com.di.executor.interactor.ListModelListener
import proficiency.android.com.ui.BuildConfig
import proficiency.android.com.ui.constants.ListConstants

/**
 * This class is mainly used to compose the model for the View layer
 * And also for any additional manipulations of data
 */
class LisDataFactory {

    fun generateListData(listDataResponse: ListDataResponse, listModelListener: ListModelListener) {
        val listDataRowModels = ArrayList<ListDataRowModel>()
        for (listDataModel in listDataResponse.listData!!) {
            if (listDataModel.description == null) {
                listDataModel.description = ""
            }
            if (listDataModel.title == null) {
                listDataModel.title = ""
            }
            if (listDataModel.imageHref == null) {
                listDataModel.imageHref = BuildConfig.BASE_URL
            }
            val dataRowModel = ListDataRowModel(listDataModel.title!!, listDataModel.description!!, listDataModel.imageHref!!)
            listDataRowModels.add(dataRowModel)
        }
        val listDataModel = ListDataModel(listDataResponse.title!!, listDataRowModels)
        listModelListener.onListDataAvailable(listDataModel)
    }

    fun generateListDataDb(listData: List<ListData>, listModelListener: ListModelListener) {
        val listDataRowModels = ArrayList<ListDataRowModel>()
        for (listDataModel in listData) {
            if (listDataModel.description == null) {
                listDataModel.description = ""
            }
            if (listDataModel.title == null) {
                listDataModel.title = ""
            }
            if (listDataModel.imageHref == null) {
                listDataModel.imageHref = BuildConfig.BASE_URL
            }
            val dataRowModel = ListDataRowModel(listDataModel.title!!, listDataModel.description!!, listDataModel.imageHref!!)
            listDataRowModels.add(dataRowModel)
        }
        val listDataModel = ListDataModel(ListConstants.NAME, listDataRowModels)
        listModelListener.onListDataAvailable(listDataModel)
    }

}
