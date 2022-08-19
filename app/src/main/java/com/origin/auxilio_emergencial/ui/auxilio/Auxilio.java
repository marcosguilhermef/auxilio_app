package com.origin.auxilio_emergencial.ui.auxilio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.databinding.FragmentAuxilioBinding;
import com.origin.auxilio_emergencial.models.Parcela;
import com.origin.auxilio_emergencial.utils.Analytics;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Auxilio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Auxilio extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "parcelas";

    // TODO: Rename and change types of parameters
    private ArrayList<Parcela> mParam1;

    private FragmentAuxilioBinding databiding;

    public Auxilio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Auxilio.
     */
    // TODO: Rename and change types and number of parameters
    public static Auxilio newInstance(String param1, String param2) {
        Auxilio fragment = new Auxilio();
        Bundle args = new Bundle();
        args.putString( ARG_PARAM1, param1 );
        fragment.setArguments( args );
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        //Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelableArrayList( ARG_PARAM1 );
        }
    }

    private void choiceView() throws IndexOutOfBoundsException{
        if(mParam1 == null){
            return;
        }

        if(mParam1.isEmpty()){
            databiding.naoHaDados.getRoot().setVisibility( View.VISIBLE );
            return;
        }

        databiding.infomacoesAuxilio.getRoot().setVisibility( View.VISIBLE );


        databiding.infomacoesAuxilio.beneficiario.nomeBeneficiario.setText( mParam1.get( 0 ).getBeneficiario().getNome() );
        databiding.infomacoesAuxilio.beneficiario.cpfBeneficiario.setText( mParam1.get( 0 ).getBeneficiario().getCpfFormatado() );
        databiding.infomacoesAuxilio.beneficiario.nisBeneficiario.setText( mParam1.get( 0 ).getBeneficiario().getNis() );

        databiding.infomacoesAuxilio.responsavel.nomeResponsavel.setText( mParam1.get( 0 ).getResponsavelAuxilioEmergencial().getNome() );
        databiding.infomacoesAuxilio.responsavel.cpfResponsavel.setText( mParam1.get( 0 ).getResponsavelAuxilioEmergencial().getCpfFormatado() );
        databiding.infomacoesAuxilio.responsavel.nisResponsavel.setText( mParam1.get( 0 ).getResponsavelAuxilioEmergencial().getNis() );

        databiding.infomacoesAuxilio.status.enquadramento.setText( mParam1.get( 0 ).getEnquadramentoAuxilioEmergencial() );

        databiding.infomacoesAuxilio.verParcelas.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList( "parcelas", mParam1 );
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate( R.id.navigationToParcelas, bundle);
            }
        } );
        //databiding.infomacoesAuxilio.parcelas.setAdapter( new AuxilioAdapter( getActivity(), getContext(), mParam1 ) );


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        databiding = FragmentAuxilioBinding.inflate( inflater, container, false );
        choiceView();
        return databiding.getRoot();
    }
}