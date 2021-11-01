package com.cbl.cofees.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cbl.cofees.R;
import com.cbl.cofees.SECUREPREFERANCE.SystemPreferances;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView total;
    Toolbar toolbar;
    SystemPreferances session;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        session=new SystemPreferances(getActivity());
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        total=root.findViewById(R.id.total);
        toolbar=getActivity().findViewById(R.id.toolbar);

        homeViewModel.getTotal().observe(this, new Observer<Integer>() {

            @Override
            public void onChanged(Integer integer) {
                total.setText(""+integer);
                toolbar.setTitle(session.ShopName());
            }
        });
        return root;
    }
}