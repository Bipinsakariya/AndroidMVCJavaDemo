package com.example.mvcstrucherdemo.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvcstrucherdemo.Model.Item;
import com.example.mvcstrucherdemo.R;
import com.squareup.picasso.Picasso;

import java.util.List;



public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Activity context;
    List<Item> list;

    public ItemAdapter(Activity context, List<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_of_items, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.txt_itemtitle.setText(list.get(i).getTitle());
        viewHolder.txt_ownername.setText(list.get(i).getOwner().getDisplayName());
        Picasso.with(context).load(list.get(i).getOwner().getProfileImage()).into(viewHolder.iv_owner);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView txt_itemtitle;
        private final TextView txt_ownername;
        private final ImageView iv_owner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_itemtitle = (TextView) itemView.findViewById(R.id.txt_itemtitle);
            txt_ownername = (TextView) itemView.findViewById(R.id.txt_ownername);
            iv_owner = (ImageView) itemView.findViewById(R.id.iv_owner);

        }
    }
}
