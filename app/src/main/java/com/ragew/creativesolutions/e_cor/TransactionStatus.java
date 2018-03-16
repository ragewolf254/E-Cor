package com.ragew.creativesolutions.e_cor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ragew.creativesolutions.e_cor.Utils.SuperTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionStatus extends Fragment {
    private ImageView plannerPhoto;
    private TextView plannerName;
    private TextView plannerAddress;
    private TextView plannerContact;
    private TextView eventStatus;

    private String fullName;
    private String address;
    private String contactNumber;
    private String status;

    public TransactionStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_status, container, false);
        plannerPhoto = view.findViewById(R.id.plannerPhoto);
        plannerName = view.findViewById(R.id.plannerName);
        plannerAddress = view.findViewById(R.id.plannerAddress);
        plannerContact = view.findViewById(R.id.plannerContact);
        eventStatus = view.findViewById(R.id.eventStatus);

        fullName = getArguments().getString("plannerName");
        address = getArguments().getString("plannerAddress");
        contactNumber = getArguments().getString("plannerContact");
        status = getArguments().getString("eventStatus");

        plannerName.setText(fullName);
        plannerAddress.setText(address);
        plannerContact.setText(contactNumber);
        if (status.equals("0")){
            eventStatus.setText("DONE");
        } else {
            eventStatus.setText("On Going");
        }

        return view;

    }

}
