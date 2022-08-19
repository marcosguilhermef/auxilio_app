package com.origin.auxilio_emergencial.ui.consultar_auxilio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.telephony.BarringInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.origin.auxilio_emergencial.R;
import com.origin.auxilio_emergencial.databinding.FragmentConsultarAuxilioBinding;
import com.origin.auxilio_emergencial.models.Cpf;
import com.origin.auxilio_emergencial.models.Parcela;
import com.origin.auxilio_emergencial.service.CodeError;
import com.origin.auxilio_emergencial.service.Service;
import com.origin.auxilio_emergencial.utils.Analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarAuxilio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarAuxilio extends Fragment {


    private FragmentConsultarAuxilioBinding bidingAuxilio;
    private TextInputEditText cpfInput;
    private ConsultarAuxilioViewModel viewmodel;

    public ConsultarAuxilio() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
    }


    public static ConsultarAuxilio newInstance(String param1, String param2) {
        ConsultarAuxilio fragment = new ConsultarAuxilio();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //Analytics.ScreenNameSend(getActivity().getTitle().toString(), getClass().getName());
        viewmodel = new ViewModelProvider(this).get(ConsultarAuxilioViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bidingAuxilio = FragmentConsultarAuxilioBinding.inflate( inflater, container, false );

        bidingAuxilio.buscar.setOnClickListener( prepareInputs() );


        viewmodel.getParcelas().observe( getViewLifecycleOwner(), new Observer<List<Parcela>>() {

            @Override
            public void onChanged(List<Parcela> parcelas) {
                if(parcelas != null){
                    sentToInforPage(prepareBundleInfo(parcelas));
                    showButton();
                }
            }

        } );

        viewmodel.getErro().observe( getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                showButton();
                showError(integer);
            }
        } );


        return bidingAuxilio.getRoot();
    }

    private View.OnClickListener prepareInputs(){
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cpf CPF = new Cpf();
                hidenButton();
                if(bidingAuxilio.TextInputCpf.getText().length() != 0){
                    CPF.setCpf( bidingAuxilio.TextInputCpf.getText().toString() );
                    makeRequest(CPF);
                }else{
                    showError(0);
                }
            }
        };
        return onClickListener;
    }

    private void makeRequest(Cpf CPF){
        Service service = new Service( viewmodel.getSucesso() ,viewmodel.getErro() , viewmodel.getParcelas() );
        service.makeCall(CPF);
    }

    private void showError(Integer integer){
        new MaterialAlertDialogBuilder(getContext(),R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setMessage( CodeError.getMenssage( integer ) )
                .setPositiveButton("Fechar", (vr,i) -> {
                    vr.cancel();
                })
                .show();
        showButton();
    }



    private Bundle prepareBundleInfo(List<Parcela> parcela){
        Bundle bundle = new Bundle();
        ArrayList<Parcela> arr = new ArrayList<>();

        arr.addAll( parcela );

        bundle.putParcelableArrayList( "parcelas", arr);

        return bundle;
    }

    private void sentToInforPage(Bundle bundle){
        Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate( R.id.navigationToAuxilio, bundle);
    }

    private void maskImput(){
        EditText editText = bidingAuxilio.TextInputCpf;
        SimpleMaskFormatter mask;
        if(editText.getText().length() > 10){
            mask = new SimpleMaskFormatter("NNNNNNNNNN");
        }else{
            mask = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        }
        MaskTextWatcher mtw = new MaskTextWatcher(editText, mask);
        editText.addTextChangedListener(mtw);
    }

    private void hidenButton(){
        bidingAuxilio.buscar.setVisibility( View.GONE );
        bidingAuxilio.progressBarAccept.setVisibility( View.VISIBLE );
    }

    private void showButton(){
        bidingAuxilio.buscar.setVisibility( View.VISIBLE );
        bidingAuxilio.progressBarAccept.setVisibility( View.GONE );
    }

    public void onStop() {
        super.onStop();
        viewmodel.renew();
    }
}