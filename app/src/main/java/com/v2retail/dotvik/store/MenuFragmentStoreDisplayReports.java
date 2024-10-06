package com.v2retail.dotvik.store;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.v2retail.dotvik.R;
import com.v2retail.util.AlertBox;

/**
 * @author Narayanan
 * @version 11.82
 * {@code Author: Narayanan, Revision: 1, Created: 19th Sep 2024, Modified: 19th Sep 2024}
 */
public class MenuFragmentStoreDisplayReports extends Fragment implements View.OnClickListener {

    Context con;
    FragmentManager fm;
    AlertBox box;
    private OnFragmentInteractionListener mListener;
    Button stock_0001;

    public MenuFragmentStoreDisplayReports() {
        // Required empty public constructor
    }

    public static MenuFragmentStoreDisplayReports newInstance() {
        MenuFragmentStoreDisplayReports fragment = new MenuFragmentStoreDisplayReports();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.menu_fragment_store_display_reports, container, false);
        con=getContext();
        box=new AlertBox(con);
        fm=getActivity().getSupportFragmentManager();

        stock_0001 = view.findViewById(R.id.store_display_reprots_stock_for_0001);

        stock_0001.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View view) {
        setFragment(view.getId());
    }

    public void setFragment(int fragmentID) {
        Fragment fragment = null;
        switch (fragmentID) {

            case R.id.store_display_reprots_stock_for_0001:
                fragment = FragmentDisplayReportStock0001.newInstance("Display > Reports");
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft =fm.beginTransaction();
            ft.replace(R.id.home, fragment, "StoreDisplayReports");
            ft.addToBackStack("StoreDisplayReports");
            ft.commit();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
        if (fm.getBackStackEntryCount() == 1){

            box.getDialogBox(getActivity());

        }
        else {
            fm.popBackStack();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((Home_Activity) getActivity())
                .setActionBarTitle("Display > Reports");
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}