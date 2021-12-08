package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.TabLayoutFragment.InvestmentViewPagerAdapter;
import com.ismailgemalmaz.sanalparam.R;

public class InvestmentFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;

    public static InvestmentFragment newInstance(){

        return new InvestmentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.investment_fragment,container,false);

        tabLayout= rootView.findViewById(R.id.investment_person_tablayout);
       viewPager=rootView.findViewById(R.id.investment_person_viewPager);

      //FRAGMENT ADPTER
       InvestmentViewPagerAdapter viewPagerAdapter=new InvestmentViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }


}
