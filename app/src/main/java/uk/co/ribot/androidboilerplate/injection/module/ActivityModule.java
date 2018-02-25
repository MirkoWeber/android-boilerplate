package uk.co.ribot.androidboilerplate.injection.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dagger.Module;
import dagger.Provides;
import uk.co.ribot.androidboilerplate.injection.ActivityContext;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }


    @Provides
    RequestManager provideRequestManager() {
        return Glide.with(mActivity.getApplicationContext());
    }
    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
