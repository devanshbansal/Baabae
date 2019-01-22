package com.example.bansal.baabae.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.bansal.baabae.Adapters.ProductListAdapter;
import com.example.bansal.baabae.Checkout;
import com.example.bansal.baabae.HomeActivity;
import com.example.bansal.baabae.Models.OrderItem;
import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.Models.SignUpForm;
import com.example.bansal.baabae.Models.Transaction;
import com.example.bansal.baabae.Products.Cigarette;
import com.example.bansal.baabae.R;
import com.example.bansal.baabae.Utility.UtilityFunctions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    TextView text;
    OrderSummary summary;
    SignUpForm userForm;
    ProductListAdapter adapter;
    Button confirm;
    String orderDetails;
    Button add;
    Cigarette obj[];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();     // Getting database reference
        final DatabaseReference myRef = database.getReference( );
        userForm=HomeActivity.userForm;

        obj=new Cigarette[5];
        obj[0] = new Cigarette("Marlboro Advance",15);
        obj[1]= new Cigarette("Marlboro Light",15);
        obj[2]=new Cigarette("Marlboro Red",15);                  // Adding 5 Cigarettes brands
        obj[3]=new Cigarette("Marlboro Methanol",15);
        obj[4]=new Cigarette("Kings Light",15);

        ListView listView = (ListView) view.findViewById(R.id.lv);
        adapter = new ProductListAdapter(getContext(),obj);               // Finding views
        listView.setAdapter(adapter);
        confirm=view.findViewById(R.id.place);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:    //Action on the order confirmation
                                String id = UtilityFunctions.generateUniqueID();
                                Transaction trans = new Transaction(id,UtilityFunctions.generateTimeStamp(),summary);
                                myRef.child("transactions").child("pending").child(id).setValue(trans);
                                myRef.child("orders").child("users").child(userForm.getNumber()).child(id).setValue(trans);
                                adapter.summary = new OrderSummary();
                                Intent i = new Intent(getActivity(),Checkout.class);
                                startActivity(i);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                summary = adapter.summary;
                Integer total=0;
                orderDetails="";
                ArrayList<OrderItem> items = summary.getItems();
                for(int i=0;i<summary.getItems().size();i++){

                    total=total+Integer.parseInt(items.get(i).getPrice())*Integer.parseInt(items.get(i).getQuantity());
                    orderDetails=orderDetails+items.get(i).getName()+": "+items.get(i).getQuantity()+"\n";
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Your order details are:\n"+orderDetails+"\n"+"Total price is: "+total.toString()).setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        text=view.findViewById(R.id.total);


    }

    public void AddItem(View v){
        ViewParent parent=v.getParent();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
