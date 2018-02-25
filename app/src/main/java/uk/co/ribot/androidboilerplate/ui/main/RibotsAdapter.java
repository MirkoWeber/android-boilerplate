package uk.co.ribot.androidboilerplate.ui.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;
import uk.co.ribot.androidboilerplate.ui.ribot_detail.RibotDetailActivity;

public class RibotsAdapter extends RecyclerView.Adapter<RibotsAdapter.RibotViewHolder> {
    private final RequestManager glide;
    private List<Ribot> mRibots;

    @Inject
    public RibotsAdapter(RequestManager glide) {
        this.glide = glide;
        mRibots = new ArrayList<>();
    }

    public void setRibots(List<Ribot> ribots) {
        mRibots = ribots;
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        Ribot ribot = mRibots.get(position);
        //holder.hexColorView.setBackgroundColor(Color.parseColor(ribot.profile().hexColor()));
        holder.avatarImageView.setBackgroundColor(Color.parseColor(ribot.profile().hexColor()));
        if( ribot.profile().avatar()!=null) glide.load(ribot.profile().avatar()).into(holder.avatarImageView);
        holder.nameTextView.setText(String.format("%s %s",
                ribot.profile().name().first(), ribot.profile().name().last()));
        holder.emailTextView.setText(ribot.profile().email());



    }

    @Override
    public int getItemCount() {
        return mRibots.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //@BindView(R.id.view_hex_color) View hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_email) TextView emailTextView;
        @BindView(R.id.view_avatar) ImageView avatarImageView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            view.getContext().startActivity(RibotDetailActivity.getStartIntent(view.getContext(),mRibots.get(getLayoutPosition())));
        }
    }
}
