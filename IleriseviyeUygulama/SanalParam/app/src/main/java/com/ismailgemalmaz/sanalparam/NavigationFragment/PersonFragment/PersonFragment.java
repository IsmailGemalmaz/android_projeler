package com.ismailgemalmaz.sanalparam.NavigationFragment.PersonFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ismailgemalmaz.sanalparam.CryptoAccount;
import com.ismailgemalmaz.sanalparam.R;

public class PersonFragment extends Fragment {

    Button cryptoAccount,foreginCurrencyAccount;

    public static PersonFragment newInstance(){

        return new PersonFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.person_fragment,container,false);
       cryptoAccount= rootView.findViewById(R.id.crypto_account);
       foreginCurrencyAccount=rootView.findViewById(R.id.foregin_currency_account);
       buttonOnclick();//hesaplar butonu  oncilick
        return rootView;
    }

    private void buttonOnclick(){
        cryptoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CryptoAccount.class);
                startActivity(intent);
            }
        });
    }
}
