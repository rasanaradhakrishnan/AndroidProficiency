package proficiency.android.com.di.executor.ListDataFactory;
import java.util.ArrayList;
import java.util.List;

import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.data.model.db.ListData;
import proficiency.android.com.di.executor.entitymodel.ListDataModel;
import proficiency.android.com.di.executor.entitymodel.ListDataRowModel;
import proficiency.android.com.di.executor.interactor.ListModelListener;
import proficiency.android.com.ui.BuildConfig;
import proficiency.android.com.ui.constants.ListConstants;

/**
 * This class is mainly used to compose the model for the View layer
 *And also for any additional manipulations of data
 */
public class LisDataFactory {

    public void generateListData(ListDataResponse listDataResponse, ListModelListener listModelListener) {
        List<ListDataRowModel> listDataRowModels = new ArrayList<>();
        for(ListData listDataModel: listDataResponse.getListData()){
            if(listDataModel.description == null){
                listDataModel.description = "";
            }
            if(listDataModel.title == null){
                listDataModel.title ="";
            }
            if(listDataModel.imageHref == null){
                listDataModel.imageHref= BuildConfig.BASE_URL;
            }
            ListDataRowModel dataRowModel = new ListDataRowModel(listDataModel.title,listDataModel.description,listDataModel.imageHref);
            listDataRowModels.add(dataRowModel);
        }
        ListDataModel listDataModel = new ListDataModel(listDataResponse.getTitle(),listDataRowModels);
        listModelListener.onListDataAvailable(listDataModel);
    }

    public void generateListDataDb(List<ListData> listData, ListModelListener listModelListener) {
        List<ListDataRowModel> listDataRowModels = new ArrayList<>();
        for(ListData listDataModel: listData){
            if(listDataModel.description == null){
                listDataModel.description = "";
            }
            if(listDataModel.title == null){
                listDataModel.title ="";
            }
            if(listDataModel.imageHref == null){
                listDataModel.imageHref= BuildConfig.BASE_URL;
            }
            ListDataRowModel dataRowModel = new ListDataRowModel(listDataModel.title,listDataModel.description,listDataModel.imageHref);
            listDataRowModels.add(dataRowModel);
        }
        ListDataModel listDataModel = new ListDataModel(ListConstants.Companion.getNAME(),listDataRowModels);
        listModelListener.onListDataAvailable(listDataModel);
    }

}
