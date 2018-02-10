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


/**
 * A simple {@link Fragment} subclass.
 */
public class ClientInformation extends Fragment {
    private ImageView clientPhoto;
    private TextView clientName;
    private TextView clientAddress;
    private TextView clientContact;

    public ClientInformation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_client_information, container, false);
        //get all the client infromation fields
        clientPhoto = view.findViewById(R.id.clientPhoto);
        clientName = view.findViewById(R.id.clientName);
        clientAddress= view.findViewById(R.id.clientAddress);
        clientContact= view.findViewById(R.id.clientContact);
        return view;
    }


}
