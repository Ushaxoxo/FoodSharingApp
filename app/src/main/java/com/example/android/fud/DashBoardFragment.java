package com.example.android.fud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class DashBoardFragment extends Fragment {

    CircleImageView donate, ngo_agent;
    ConstraintLayout need_food;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_dashboard, container, false);

        // For Vibrations
        Vibrator vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        donate = root.findViewById(R.id.donate_button);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(80);
                Intent intent  = new Intent(getActivity(), DonateActivity.class);
                startActivity(intent);
            }
        });
        ngo_agent = root.findViewById(R.id.ngo_agent_button);
        ngo_agent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(80);
                Intent intent  = new Intent(getActivity(), NgoAgentActivity.class);
                startActivity(intent);
            }
        });
        need_food = root.findViewById(R.id.need_food);
        need_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(20);
                Intent intent  = new Intent(getActivity(), NeedFoodActivity.class);
                startActivity(intent);
            }
        });

//        fab = root.findViewById(R.id.fab_robot);
//        fab.bringToFront();
//        need_food.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                vibe.vibrate(80);
//                Intent intent  = new Intent(getActivity(), ChatBot.class);
//                startActivity(intent);
//            }
//        });
        return root;
    }

}
