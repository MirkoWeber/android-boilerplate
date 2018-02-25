package uk.co.ribot.androidboilerplate.ui.ribot_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.data.model.Ribot;

public class RibotDetailsAdapter extends RecyclerView.Adapter<RibotDetailsAdapter.RibotViewHolder> {
    private List<String> ribotDetails;
    private List<String> ribotDetailsPrefix;

    @Inject
    public RibotDetailsAdapter() {
        ribotDetails = new ArrayList<>();
        ribotDetailsPrefix = new ArrayList<>();
    }

    public void setRibotDetails(Ribot ribot, Context context) {
        ribotDetails.add(ribot.profile().email());
        ribotDetailsPrefix.add(context.getResources().getString(R.string.email_prefix));
        ribotDetails.add(ribot.profile().dateOfBirth().toLocaleString());
        ribotDetailsPrefix.add(context.getResources().getString(R.string.birthdate_prefix));
        ribotDetails.add(ribot.profile().bio());
        ribotDetailsPrefix.add(context.getResources().getString(R.string.bio_prefix));
    }

    @Override
    public RibotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ribot_detail, parent, false);
        return new RibotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RibotViewHolder holder, int position) {
        holder.prefixTextView.setText(ribotDetailsPrefix.get(position));
        holder.sufixTextView.setText(ribotDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return ribotDetails.size();
    }

    class RibotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //@BindView(R.id.view_hex_color) View hexColorView;
        @BindView(R.id.prefix) TextView prefixTextView;
        @BindView(R.id.sufix) TextView sufixTextView;

        public RibotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //if(ribotDetailsPrefix.get(getLayoutPosition()).equals(itemView.getResources().getString(R.string.email_prefix)))
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if(ribotDetailsPrefix.get(getLayoutPosition()).
                    equals(itemView.getResources().
                            getString(R.string.email_prefix))){
                final Intent intent = new Intent(Intent.ACTION_SENDTO);
                String mailto = "mailto:"+ribotDetails.get(getLayoutPosition());
                intent.setData(Uri.parse(mailto));
                view.getContext().startActivity(intent);
            }
        }
    }
}
