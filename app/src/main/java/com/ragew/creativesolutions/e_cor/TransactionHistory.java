package com.ragew.creativesolutions.e_cor;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionHistory extends Fragment {

    private ListView history_listView;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> historyArrayList;

    public TransactionHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        history_listView.findViewById(R.id.historyListView);
        historyArrayList = new ArrayList<String>();
        historyArrayList.add("01/12/99");
        historyArrayList.add("01/13/99");
        historyArrayList.add("Some date");
        historyArrayList.add("Another date");

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,historyArrayList);
        history_listView.setAdapter(arrayAdapter);

        return view;
    }

}
