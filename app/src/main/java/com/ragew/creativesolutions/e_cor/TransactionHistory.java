package com.ragew.creativesolutions.e_cor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistory extends Fragment {

    private ListView history_listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> historyArrayList;
    private String date;

    public TransactionHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        history_listView = view.findViewById(R.id.historyListView);
        historyArrayList = new ArrayList<>();

        date = getArguments().getString("date");
        //Toast.makeText(getContext(),String.valueOf(date),Toast.LENGTH_LONG).show();
        long longDate = Long.valueOf(date);

        Date date = new Date(longDate*1000L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-8"));
        String formattedDate = simpleDateFormat.format(date);

        historyArrayList.add(formattedDate);
        arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, historyArrayList);
        history_listView.setAdapter(arrayAdapter);

        return view;
    }

}
