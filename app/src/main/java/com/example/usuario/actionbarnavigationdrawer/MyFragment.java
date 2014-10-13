package com.example.usuario.actionbarnavigationdrawer;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MyFragment extends Fragment  {

    public static String ARGS_COLOR = "ARGS_COLOR";
    public static String ARGS_SUBTITLE = "ARGS_SUBTITLE";
    private OnFragmentInteractionListener mListener;

    public static MyFragment getInstance(int color,String subtitle){
        MyFragment fragment = new MyFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_COLOR, color);
        bundle.putString(ARGS_SUBTITLE, subtitle);

        fragment.setArguments(bundle);

        return fragment;
    }

    // Creando una vista en base al fragment layout.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout,container, false);

        int color = getArguments().getInt(ARGS_COLOR);
        String subtitle=getArguments().getString(ARGS_SUBTITLE);
        mListener.onFragmentInteraction("Nel Puto lo modifique desde el fragment");
        RelativeLayout layout = (RelativeLayout) rootView.findViewById(R.id.layout);
        layout.setBackgroundColor(getResources().getColor(color));

        return rootView;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String subtitle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}