package com.ismailgemalmaz.sanalparam.NavigationFragment.InvestmentFragment.TabLayoutFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class InvestmentViewPagerAdapter extends FragmentStatePagerAdapter {


    public InvestmentViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment selectedFragment;

        switch (position){
            case 0:
               selectedFragment=InvestmentCryptoMoneyFragment.newInstance();
                break;
            case 1:
                selectedFragment=InvestmentForeginCurrencyFragment.newInstance();
                break;

                default:
                return null;
        }

        return selectedFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence selectedTitle;
        switch (position){
            case 0:
                 selectedTitle="KRİPTO PARA ";
                break;
            case 1 :
                selectedTitle="DÖVİZ ";
                break;
            default:
                return null;
        }
        return selectedTitle;
    }
}
