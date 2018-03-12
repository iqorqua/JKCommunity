package com.example.igorqua.jkcommunity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Hello extends Fragment {


    public Hello() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_hello, container, false);
        final View tip = result.findViewById(R.id.tips_layout);
        final View text = result.findViewById(R.id.textfragmethello);
        YoYo.with(Techniques.SlideInLeft)
                .duration(4000)
                .playOn(text);
        YoYo.with(Techniques.FadeIn)
                .duration(4700)
                .playOn(result.findViewById(R.id.imageView2));
        YoYo.with(Techniques.Pulse)
                .repeatMode(0)
                .duration(3000)
                .playOn(tip);
        // Inflate the layout for this fragment
        return result;
    }

    private void animateSampleOne(View toolbar, View activityMainMobileNumberLayout, View activityMainPinkFab, View activityMainheaderLayout) {

    }

}
