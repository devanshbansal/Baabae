package com.example.bansal.baabae.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bansal.baabae.Adapters.HomeListAdapter;
import com.example.bansal.baabae.Adapters.ProductListAdapter;
import com.example.bansal.baabae.Checkout;
import com.example.bansal.baabae.HomeActivity;
import com.example.bansal.baabae.Models.OrderItem;
import com.example.bansal.baabae.Models.OrderSummary;
import com.example.bansal.baabae.Models.SignUpForm;
import com.example.bansal.baabae.Models.Transaction;
import com.example.bansal.baabae.Products.Benson;
import com.example.bansal.baabae.Products.Cigarette;
import com.example.bansal.baabae.Products.Classic;
import com.example.bansal.baabae.Products.Davidoff;
import com.example.bansal.baabae.Products.Dunhill;
import com.example.bansal.baabae.Products.Goldflake;
import com.example.bansal.baabae.Products.Marlboro;
import com.example.bansal.baabae.R;
import com.example.bansal.baabae.Utility.UtilityFunctions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    TextView text;
    OrderSummary summary;
    EditText address;
    SignUpForm userForm;
    Button cart;
    public ArrayList<String> selectedBrands = new ArrayList<>();
    HomeListAdapter adapter;
    ArrayList<String[]> branditems = new ArrayList<>();
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
        cart=view.findViewById(R.id.cart);
        cart.setEnabled(false);
        cart.setVisibility(View.INVISIBLE);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenConfirmDialog();
            }
        });


        branditems.add(Marlboro.items);
        branditems.add(Benson.items);
        branditems.add(Classic.items);
        branditems.add(Davidoff.items);
        branditems.add(Dunhill.items);
        branditems.add(Goldflake.items);

        obj=new Cigarette[5];
        obj[0] = new Cigarette("Marlboro Advance",15);
        obj[1]= new Cigarette("Marlboro Light",15);
        obj[2]=new Cigarette("Marlboro Red",15);                  // Adding 5 Cigarettes brands
        obj[3]=new Cigarette("Marlboro Methanol",15);
        obj[4]=new Cigarette("Kings Light",15);

        ListView listView = (ListView) view.findViewById(R.id.lv);
        //   address = view.findViewById(R.id.address);
        final String[] brandlist = new String[6];
        brandlist[0]="Marlboro";
        brandlist[1]="Benson";
        brandlist[2]="Classic";
        brandlist[3]="Davidoff";
        brandlist[4]="Dunhill";
        brandlist[5]="Goldflake";


        adapter = new HomeListAdapter(getContext(),brandlist);               // Finding views
        listView.setAdapter(adapter);
        // confirm=view.findViewById(R.id.place);

//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case DialogInterface.BUTTON_POSITIVE:    //Action on the order confirmation
//                                String id = UtilityFunctions.generateUniqueID();
//                                Transaction trans = new Transaction(id,UtilityFunctions.generateTimeStamp(),address.getText().toString(),summary);
//                                myRef.child("transactions").child("pending").child(id).setValue(trans);
//                                myRef.child("orders").child("users").child(userForm.getNumber()).child(id).setValue(trans);
//                                adapter.summary = new OrderSummary();
//                                adapter.setButtonsVisible();
//                                Intent i = new Intent(getActivity(),Checkout.class);
//                                startActivity(i);
//                                break;
//
//                            case DialogInterface.BUTTON_NEGATIVE:
//                                break;
//                        }
//                    }
//                };
//                summary = adapter.summary;
//                orderDetails="";
//                ArrayList<OrderItem> items = summary.getItems();
//                for(int i=0;i<summary.getItems().size();i++){
//                    orderDetails=orderDetails+items.get(i).getName()+"\n";
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Your order details are:\n"+orderDetails+"\n").setPositiveButton("Yes", dialogClickListener)
//                        .setNegativeButton("No", dialogClickListener).show();
//            }
//        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //   Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.product_list_dialog_adapter);
                final String brandName = brandlist[position];
                final Integer brandPosition = position;

                dialog.setTitle(brandName);
                ListView listView = (ListView) dialog.findViewById(R.id.lv_dialog);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        dialog.dismiss();
                        selectedBrands.add(brandName+" "+branditems.get(brandPosition)[position]);
                        cart.setVisibility(View.VISIBLE);
                        cart.setEnabled(true);
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        dialog.dismiss();
                                   OpenConfirmDialog();
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        break;
                                }
                            }
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("You are just few steps away from getting the cigarettes. Continue?").setPositiveButton("PLACE ORDER", dialogClickListener)
                                .setNegativeButton("ADD MORE", dialogClickListener).show();



                    }
                });
                ProductListAdapter productListAdapter = new ProductListAdapter(getContext(),branditems.get(position));// Finding views
                listView.setAdapter(productListAdapter);
                dialog.show();
            }
        });


    }

    public void AddItem(View v){
        ViewParent parent=v.getParent();
    }

    public void OpenConfirmDialog(){
        Dialog d = new Dialog(getContext());
        d.setContentView(R.layout.confirm_order_dialog);
        TextView detailView = d.findViewById(R.id.textView9);
        String details="Shopping Cart Details:"+"\n";
        for(int i=0;i<selectedBrands.size();i++){
            details=details+""+selectedBrands.get(i)+" ";

        }
        detailView.setText(details);
        d.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
