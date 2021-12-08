package com.ismailgemalmaz.kriptoparam.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ismailgemalmaz.kriptoparam.R;
import com.ismailgemalmaz.kriptoparam.ui.dashboard.CryptoModel;

import java.util.ArrayList;
import java.util.Map;

public class HomeFragment extends Fragment {

    HomeFragmentAdapter homeFragmentAdapter;
    FirebaseFirestore firebaseFirestore;
    ArrayList<String> arrayListCurrency;
    ArrayList<Double> arrayListPrice;
    ArrayList<Object> arrayListEposta;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    RecyclerView recyclerViewHome;
    Object eposta;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        arrayListCurrency = new ArrayList<>();
        arrayListPrice = new ArrayList<>();
        arrayListEposta = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        readData();
        recyclerViewHome = view.findViewById(R.id.recyclerViewHome);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        homeFragmentAdapter = new HomeFragmentAdapter(arrayListCurrency, arrayListPrice, arrayListEposta, getContext());
        recyclerViewHome.setAdapter(homeFragmentAdapter);
    }


    public void readData() {
        CollectionReference reference = firebaseFirestore.collection("CryptoMoney");
        reference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Toast.makeText(getContext(), "HATA OLUŞTU", Toast.LENGTH_LONG).show();
                }

                if (value != null) {
                    for (DocumentSnapshot snapshot : value.getDocuments()) {
                        Map<String, Object> data = snapshot.getData();
                        String currency = (String) data.get("currency");
                        Double price = (Double) data.get("price");
                        eposta = (String) data.get("ePosta");
                        arrayListCurrency.add(currency);
                        arrayListPrice.add(price);
                        arrayListEposta.add(eposta);
                        homeFragmentAdapter.notifyDataSetChanged();

                    }

                }
            }
        });

    }

}