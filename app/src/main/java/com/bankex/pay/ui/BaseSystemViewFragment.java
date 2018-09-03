package com.bankex.pay.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankex.pay.R;
import com.bankex.pay.widget.SystemView;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseSystemViewFragment extends DaggerFragment {

    protected SystemView systemView;

    public BaseSystemViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_system_view_create_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        systemView = view.findViewById(R.id.system_view);
    }

}
