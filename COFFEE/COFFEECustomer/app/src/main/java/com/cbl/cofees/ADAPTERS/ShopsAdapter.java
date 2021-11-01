package com.cbl.cofees.ADAPTERS;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cbl.cofees.R;
import com.cbl.cofees.ViewLocationActivity;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class ShopsAdapter extends RecyclerView.Adapter<ShopsAdapter.RestaurantViewHolder> {
    private ArrayList<JsonObject> menues;
    private Context mContext;


    public ShopsAdapter(Context context, ArrayList<JsonObject> mmUsers) {
        mContext = context;
        menues = mmUsers;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shops_adapter, parent, false);
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
        TextView itemname, address;


        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            address = itemView.findViewById(R.id.address);
            itemname = itemView.findViewById(R.id.name);

        }

        public void bindItemToAdapter(final JsonObject menue) {

            address.setText(menue.get("address_one").getAsString()+" "+menue.get("address_two").getAsString());
            itemname.setText(menue.get("shop_name").getAsString());
            itemname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, ViewLocationActivity.class)
                            .putExtra("lat",Double.valueOf(menue.get("lat").getAsString()))
                            .putExtra("lon",Double.valueOf(menue.get("lon").getAsString()))
                            .putExtra("shopname",menue.get("shop_name").getAsString())
                    );
                }
            });

//

        }
    }


}

