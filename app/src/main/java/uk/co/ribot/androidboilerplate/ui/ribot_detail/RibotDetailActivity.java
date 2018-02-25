package uk.co.ribot.androidboilerplate.ui.ribot_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

/**
 * android-boilerplate
 * Created by Nabztastic on 25.02.2018.
 */

public class RibotDetailActivity extends BaseActivity implements RibotDetailMvpView{
    private static final String RIBOT_ID = "RIBOT_ID";
    @Inject
    RibotDetailPresenter ribotDetailPresenter;
    @Inject
    RibotDetailsAdapter ribotDetailsAdapter;
    @BindView(R.id.avatar_large) ImageView avatarLargeImageView;
    @BindView(R.id.full_name) TextView fullNameTextView;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    public static Intent getStartIntent(Context context, Ribot ribot) {
        Intent intent = new Intent(context, RibotDetailActivity.class);
        intent.putExtra(RIBOT_ID, ribot);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        getSupportActionBar().hide(); // hides the title bar
        setContentView(R.layout.activity_ribot_detail);
        ButterKnife.bind(this);
        ribotDetailPresenter.attachView(this);
        mRecyclerView.setAdapter(ribotDetailsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            Ribot ribot = extras.getParcelable(RIBOT_ID);
            showRibot(ribot);


        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ribotDetailPresenter.detachView();
    }

    private void showRibot(Ribot ribot){
        avatarLargeImageView.setBackgroundColor(Color.parseColor(ribot.profile().hexColor()));
        Glide.with(this).load(ribot.profile().avatar()).into(avatarLargeImageView);
        fullNameTextView.setText(ribot.profile().name().first() + " " + ribot.profile().name().last());
        ribotDetailsAdapter.setRibotDetails(ribot,getApplicationContext());
        ribotDetailsAdapter.notifyDataSetChanged();

    }
}
