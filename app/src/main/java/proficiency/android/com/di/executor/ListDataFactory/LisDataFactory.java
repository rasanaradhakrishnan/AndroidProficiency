package proficiency.android.com.di.executor.ListDataFactory;
import proficiency.android.com.data.ListDataListener;
import proficiency.android.com.data.model.api.ListDataResponse;
import proficiency.android.com.di.executor.entitymodel.ListDataModel;
import proficiency.android.com.di.executor.interactor.ListModelListener;

/**
 * This class is mainly used to compose the model for the View layer
 *And also for any additional manipulations of data
 */
public class LisDataFactory {

    public void generateCartModel(ListDataResponse listDataResponse, ListModelListener listModelListener) {
        ListDataModel listDataModel = new ListDataModel(listDataResponse.getTitle(),listDataResponse.getListData());
        listModelListener.onListDataAvailable(listDataModel);
    }
}
