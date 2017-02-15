package edu.washington.swalden1.quizdroid;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class OverviewFragment extends Fragment {

    private static final String ARG_TOPIC = "topic";

    private int topic;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(int param1) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TOPIC, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.topic = getArguments().getInt(ARG_TOPIC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_overview, container, false);

        Log.i(MainActivity.TAG, "in overview fragment oncreateview");

        TextView ot = (TextView) v.findViewById(R.id.overview_topic);
        TextView od = (TextView) v.findViewById(R.id.overview_description);

        ot.setText(QuizApp.getInstance().getTopics()[topic].getTitle());
        od.setText(QuizApp.getInstance().getTopics()[topic].getLongDescription());

        return v;
    }
}
