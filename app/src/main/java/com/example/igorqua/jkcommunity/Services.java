package com.example.igorqua.jkcommunity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.igorqua.jkcommunity.CustomElements.ServiceAdapter;
import com.example.igorqua.jkcommunity.CustomElements.ServiceItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class Services extends Fragment {

    protected static List<ServiceItem> services = new ArrayList<>();
    protected static ServiceAdapter serviceAdapter;

    public Services() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("jk_servises")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        services.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                services.add(new ServiceItem(document.getId().toString(), document.getData().toString()));
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                serviceAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        View root_view = inflater.inflate(R.layout.fragment_services, container, false);
        ListView  listView = (ListView) root_view.findViewById(R.id.services_list);
        serviceAdapter = new ServiceAdapter(root_view.getContext(), (ArrayList<ServiceItem>) services);
        listView.setAdapter(serviceAdapter);
        return root_view;
    }

}
