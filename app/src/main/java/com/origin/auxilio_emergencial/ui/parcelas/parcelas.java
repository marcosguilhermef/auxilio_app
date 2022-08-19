package com.origin.auxilio_emergencial.ui.parcelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.databinding.FragmentParcelasBinding;
import com.origin.auxilio_emergencial.models.Parcela;
import com.origin.auxilio_emergencial.ui.adsFragment;
import com.origin.auxilio_emergencial.utils.Analytics;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link parcelas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class parcelas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "parcelas";

    // TODO: Rename and change types of parameters
    private ArrayList<Parcela> mParam1;

    private FragmentParcelasBinding databiding;

    public parcelas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment parcelas.
     */
    // TODO: Rename and change types and number of parameters
    public static parcelas newInstance(String param1, String param2) {
        parcelas fragment = new parcelas();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
        adsFragment.destroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList( ARG_PARAM1 );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databiding = FragmentParcelasBinding.inflate( inflater, container, false );
        databiding.parcelas.setAdapter( new AuxilioAdapter( getActivity(), getContext(), mParam1 ) );
        return databiding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adsFragment.call();
    }
}