package com.juanlucena.sdos.webserviceScreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanlucena.sdos.R;
import com.juanlucena.sdos.model.Task;
import com.juanlucena.sdos.model.WebserviceObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebserviceRecyclerViewAdapter extends RecyclerView.Adapter<WebserviceRecyclerViewAdapter.AdminRecyclerViewAdapterViewHolder>{

    private List<WebserviceObject> webserviceObjectList;

    public WebserviceRecyclerViewAdapter(List<WebserviceObject> webserviceObjectList){
        this.webserviceObjectList = webserviceObjectList;
    }

    public static class AdminRecyclerViewAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewBusiness)TextView textViewBusiness;
        @BindView(R.id.textViewZip)TextView textViewZip;
        @BindView(R.id.textViewPhone)TextView textViewPhone;
        @BindView(R.id.textViewItem)TextView textViewItem;


        public AdminRecyclerViewAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    public WebserviceRecyclerViewAdapter.AdminRecyclerViewAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_admin_recyclerview, parent, false);
        return new AdminRecyclerViewAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WebserviceRecyclerViewAdapter.AdminRecyclerViewAdapterViewHolder holder, int position) {

        holder.textViewBusiness.setText(webserviceObjectList.get(position).getBusiness());
        holder.textViewZip.setText(webserviceObjectList.get(position).getZipcode());
        holder.textViewPhone.setText(webserviceObjectList.get(position).getPhone1());
        holder.textViewItem.setText(webserviceObjectList.get(position).getItem());

    }

    @Override
    public int getItemCount() {
        return webserviceObjectList.size();
    }
}
