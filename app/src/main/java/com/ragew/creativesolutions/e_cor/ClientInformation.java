package com.ragew.creativesolutions.e_cor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientInformation extends Fragment {
    String fullName;
    String address;
    String contact;
    public ClientInformation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_information, container, false);
        //get all the client infromation fields
        ImageView clientPhoto = view.findViewById(R.id.clientPhoto);
        TextView clientName = view.findViewById(R.id.clientName);
        TextView clientAddress = view.findViewById(R.id.clientAddress);
        TextView clientContact = view.findViewById(R.id.clientContact);

        fullName = getArguments().getString("clientName");
        address = getArguments().getString("address");
        contact = getArguments().getString("contactNumber");

        clientName.setText(fullName);
        clientAddress.setText(address);
        clientContact.setText(contact);

        return view;
    }


}
