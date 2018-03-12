package com.example.igorqua.jkcommunity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;
import com.google.android.gms.maps.model.LatLng;


/**
 * A simple {@link Fragment} subclass.
 */
public class Place extends Fragment {

    private GyroscopeObserver gyroscopeObserver;

    public Place() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gyroscopeObserver = new GyroscopeObserver();
        // Set the maximum radian the device should rotate to show image's bounds.
        // It should be set between 0 and π/2.
        // The default value is π/9.
        gyroscopeObserver.setMaxRotateRadian(Math.PI/9);
        View result = inflater.inflate(R.layout.fragment_place, container, false);
        PanoramaImageView panoramaImageView = (PanoramaImageView) result.findViewById(R.id.panorama_image_view);
        panoramaImageView.setEnablePanoramaMode(true);
        panoramaImageView.setEnableScrollbar(true);
        panoramaImageView.setInvertScrollDirection(false);
        // Set GyroscopeObserver for PanoramaImageView.
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);
        gyroscopeObserver.register(result.getContext());
        return result;
    }


}
