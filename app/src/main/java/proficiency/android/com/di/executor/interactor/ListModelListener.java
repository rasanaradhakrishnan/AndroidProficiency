package proficiency.android.com.di.executor.interactor;

import proficiency.android.com.di.executor.entitymodel.ListDataModel;

/**
 * This interface can be mainly used for composing the model
 * which can be readily consumed by the view layer.
 * Currently our use case does not have any such requirement.
 * Designed for future enhancements
 */
public interface ListModelListener {

    void onListDataAvailable(ListDataModel listDataModel);

    void onError(String message);
}
