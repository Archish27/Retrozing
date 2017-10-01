package com.markdevelopers.retrozing.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.markdevelopers.retrozing.R;
import com.markdevelopers.retrozing.common.Config;
import com.markdevelopers.retrozing.data.remote.models.DataModel;
import com.markdevelopers.retrozing.ui.widgets.BaseTextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Archish on 2/23/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    ArrayList<DataModel> data;
    ItemUpdateListener commander;

    public MainAdapter(ArrayList<DataModel> data, ItemUpdateListener commander) {
        this.data = data;
        this.commander = commander;
    }

    public interface ItemUpdateListener {
        void onItemCardClicked(DataModel dataModel);
    }
    public void animateTo(List<DataModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<DataModel> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final DataModel model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<DataModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final DataModel model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<DataModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final DataModel model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public DataModel removeItem(int position) {
        final DataModel model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, DataModel model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final DataModel model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        MainViewHolder holder = new MainViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
        holder.tvDescription.setText(data.get(position).getDescription());
        Picasso.with(holder.ivImage.getContext()).load(Config.MEDIA + data.get(position).getImage()).into(holder.ivImage, new Callback() {
            @Override
            public void onSuccess() {
                holder.pgProgress.setVisibility(View.GONE);
                holder.ivImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        BaseTextView tvName;
        BaseTextView tvDescription;
        CircleImageView ivImage;
        CardView cdData;
        ProgressBar pgProgress;

        public MainViewHolder(View itemView) {
            super(itemView);
            tvName = (BaseTextView) itemView.findViewById(R.id.tvName);
            tvDescription = (BaseTextView) itemView.findViewById(R.id.tvDescription);
            ivImage = (CircleImageView) itemView.findViewById(R.id.ivImage);
            pgProgress = (ProgressBar) itemView.findViewById(R.id.pgProgress);
            cdData = (CardView) itemView.findViewById(R.id.cdData);
            cdData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (commander != null)
                        commander.onItemCardClicked(data.get(getAdapterPosition()));
                }
            });
        }
    }
}
