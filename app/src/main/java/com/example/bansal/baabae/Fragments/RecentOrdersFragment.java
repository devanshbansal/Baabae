package com.example.bansal.baabae.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.bansal.baabae.Adapters.RecentOrderAdapter;
import com.example.bansal.baabae.HomeActivity;
import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.Models.SignUpForm;
import com.example.bansal.baabae.R;

import java.util.ArrayList;

public class RecentOrdersFragment extends BaseFragment {
    SignUpForm userForm;
    ArrayList<OrderSummary> recentOrders;
  public static RecentOrderAdapter  adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        userForm=HomeActivity.userForm;

        return inflater.inflate(R.layout.fragment_recentorders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

recentOrders = HomeActivity.recentOrders;
        ListView listView = (ListView) view.findViewById(R.id.lv_recent);

         adapter = new RecentOrderAdapter(getContext(),recentOrders);
                 listView.setAdapter(adapter);

    }
     public static void  refresh(ArrayList<OrderSummary> list){
        adapter.refreshEvents(list);
     }
}
