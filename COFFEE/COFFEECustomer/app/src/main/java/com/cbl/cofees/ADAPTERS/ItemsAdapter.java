package com.cbl.cofees.ADAPTERS;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbl.cofees.R;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.RestaurantViewHolder> {
    private ArrayList<JsonObject> menues;
    private Context mContext;
    FragmentManager fragmentmanager;


    public ItemsAdapter(Context context, ArrayList<JsonObject> mmUsers, FragmentManager fragmentmanager) {
        mContext = context;
        menues = mmUsers;
        this.fragmentmanager = fragmentmanager;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_adapter, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindItemToAdapter(menues.get(holder.getAdapterPosition()));
        final JsonObject element = menues.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return menues.size();
    }


    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView itemname, price;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            price = itemView.findViewById(R.id.price);
            itemname = itemView.findViewById(R.id.name);

        }

        public void bindItemToAdapter(final JsonObject menue) {

            price.setText(menue.get("price").getAsString());
            itemname.setText(menue.get("price").getAsString());

//

        }
    }


}

