package com.origin.auxilio_emergencial.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.origin.auxilio_emergencial.databinding.FragmentHomeBinding;
import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.utils.Analytics;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentHomeBinding homeBinding;

    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        args.putString( ARG_PARAM2, param2 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i( "getTitle: ", getActivity().getTitle().toString() );
        Log.i( "getName: ", getClass().getName() );
        Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
        if (getArguments() != null) {
            mParam1 = getArguments().getString( ARG_PARAM1 );
            mParam2 = getArguments().getString( ARG_PARAM2 );
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeBinding = FragmentHomeBinding.inflate( inflater,container, false );

        homeBinding.consultarAuxilio.card.setOnClickListener(toNavitate());
        homeBinding.avaliarApp.cardAvaliarApp.setOnClickListener(toNavitate());
        homeBinding.compartilharApp.cardCompartilhar.setOnClickListener(toNavitate());
        homeBinding.calendario.cardCalendario.setOnClickListener( toNavitate() );



        return homeBinding.getRoot();
    }

    private View.OnClickListener toNavitate(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("snash:  ",getResources().getResourceName(view.getId()));
                switch (getResources().getResourceName(view.getId())){
                    case "com.origin.auxilio_emergencial:id/card": Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_home_to_consultar_cpf);
                        break;
                    case "com.origin.auxilio_emergencial:id/card_avaliar_app": openAvaliation();
                        break;
                    case "com.origin.auxilio_emergencial:id/cardCompartilhar": compartilhar();
                        break;
                    case "com.origin.auxilio_emergencial:id/cardCalendario": Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_home_to_navigationToCalendario);
                        break;
                }
            }
        };
    }

    private void openAvaliation(){
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.origin.auxilio_emergencial"));
        startActivity(viewIntent);
        //Analytics.avaliar("Avaliar");
    }

    private void compartilhar(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.origin.auxilio_emergencial");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        //Analytics.share("Compartilhar");

    }
}