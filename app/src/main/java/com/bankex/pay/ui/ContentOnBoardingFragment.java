package com.bankex.pay.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankex.pay.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Denis Anisimov.
 */
public class ContentOnBoardingFragment extends Fragment {
    public Unbinder unbinder;

    public final static String ARG_ID = "param_id";
    public final static String ARG_TITLE = "param_title";
    public final static String ARG_DESC = "param_desc";

    public int paramID;
    public int paramTitle;
    public int paramDesc;

    ImageView imageOnboarding;
    TextView titleOnboarding;
    TextView descOnboarding;

    public static Fragment newInstance(int id, int title, int desc) {
        ContentOnBoardingFragment contentOnBoardingFragment = new ContentOnBoardingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        args.putInt(ARG_TITLE, title);
        args.putInt(ARG_DESC, desc);
        contentOnBoardingFragment.setArguments(args);
        return contentOnBoardingFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_content_onboarding, container, false);
        unbinder = ButterKnife.bind(this, view);
        imageOnboarding = view.findViewById(R.id.imageOnboarding);
        titleOnboarding = view.findViewById(R.id.titleOnboarding);
        descOnboarding = view.findViewById(R.id.descOnboarding);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments == null) {
            getActivity().finish();
            return;
        }
        paramID = arguments.getInt(ARG_ID);
        paramTitle = arguments.getInt(ARG_TITLE);
        paramDesc = arguments.getInt(ARG_DESC);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageOnboarding.setImageResource(paramID);
        titleOnboarding.setText(paramTitle);
        descOnboarding.setText(paramDesc);
    }
}
