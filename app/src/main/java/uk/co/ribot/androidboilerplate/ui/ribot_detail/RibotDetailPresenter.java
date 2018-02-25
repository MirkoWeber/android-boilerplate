package uk.co.ribot.androidboilerplate.ui.ribot_detail;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import uk.co.ribot.androidboilerplate.data.DataManager;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

/**
 * android-boilerplate
 * Created by Nabztastic on 25.02.2018.
 */

public class RibotDetailPresenter extends BasePresenter<RibotDetailMvpView> {
    private final DataManager mDataManager;
    private Disposable mDisposable;

    @Inject
    public RibotDetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RibotDetailMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mDisposable != null) mDisposable.dispose();
    }

}
