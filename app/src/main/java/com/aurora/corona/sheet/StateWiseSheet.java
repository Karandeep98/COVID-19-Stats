

package com.aurora.corona.sheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.aurora.corona.Constants;
import com.aurora.corona.R;
import com.aurora.corona.model.casetime.Statewise;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StateWiseSheet extends BaseBottomSheet {

    public static final String TAG = "STATE_WISE_SHEET";
    @BindView(R.id.txt_all_total)
    TextView txtAllTotal;
    @BindView(R.id.txt_all_active)
    TextView txtAllActive;
    @BindView(R.id.txt_all_cured)
    TextView txtAllCured;
    @BindView(R.id.txt_all_deaths)
    TextView txtAllDeaths;
    @BindView(R.id.txt_title_state)
    AppCompatTextView txtTitleState;
    @BindView(R.id.txt_last_updated)
    AppCompatTextView txtLastUpdated;

    private Statewise statewise;

    public StateWiseSheet() {
    }

    @Nullable
    @Override
    public View onCreateContentView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_state_wise_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onContentViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            final Bundle bundle = getArguments();
            stringExtra = bundle.getString(Constants.STRING_EXTRA);
            statewise = gson.fromJson(stringExtra, Statewise.class);
            populateData();
        } else {
            dismissAllowingStateLoss();
        }
    }

    private void populateData() {
        String x="⬆";
        int deltaActive= Integer.parseInt(statewise.getDeltaconfirmed())-Integer.parseInt(statewise.getDeltarecovered())-Integer.parseInt(statewise.getDeltadeaths());
        if(deltaActive<0) x="⬇";
        txtTitleState.setText(statewise.getState());
        txtLastUpdated.setText(StringUtils.joinWith(" : ", "Last updated", statewise.getLastupdatedtime()));
        txtAllTotal.setText(statewise.getConfirmed()+" ⬆ "+statewise.getDeltaconfirmed());
        txtAllActive.setText(statewise.getActive()+" "+x+" "+Math.abs(deltaActive));
        txtAllCured.setText(statewise.getRecovered()+" ⬆ "+statewise.getDeltarecovered());
        txtAllDeaths.setText(statewise.getDeaths()+" ⬆ "+statewise.getDeltadeaths());
    }
}
