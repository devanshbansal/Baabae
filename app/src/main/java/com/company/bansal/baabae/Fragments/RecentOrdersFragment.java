package com.company.bansal.baabae.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.company.bansal.baabae.Adapters.RecentOrderAdapter;
import com.company.bansal.baabae.HomeActivity;
import com.company.bansal.baabae.Models.SignUpForm;
import com.company.bansal.baabae.Models.Transaction;
import com.company.bansal.baabae.R;

import java.util.ArrayList;

public class RecentOrdersFragment extends BaseFragment {
    SignUpForm userForm;
    ArrayList<Transaction> recentOrders;
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
     public static void  refresh(ArrayList<Transaction> list){
        adapter.refreshEvents(list);
     }
}
