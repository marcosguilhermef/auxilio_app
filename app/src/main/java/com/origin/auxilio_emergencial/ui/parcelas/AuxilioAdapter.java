package com.origin.auxilio_emergencial.ui.parcelas;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.Navigation;
//import com.google.firebase.analytics.FirebaseAnalytics;
import com.origin.auxilio_emergencial.models.Parcela;
import com.origin.auxilio_emergencial.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AuxilioAdapter extends BaseAdapter {

    private List<Parcela> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private Holder holder;
    //private FirebaseAnalytics mFirebaseAnalytics;
    private Activity activity;


    public AuxilioAdapter(Activity activity, Context aContext, List<Parcela> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Parcela getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId().longValue();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup   ) {
        view = layoutInflater.inflate(R.layout.parcela_layout, null);
        holder = new Holder();



        SimpleDateFormat format = new SimpleDateFormat("MM/yyyy");

        TextView parcela = (TextView) view.findViewById(R.id.parcela);
        TextView valor = (TextView) view.findViewById(R.id.valor);
        TextView mes = (TextView) view.findViewById(R.id.mes);
        TextView enquadramento = (TextView) view.findViewById( R.id.enquadramento_parcela );
        TextView situacao = (TextView) view.findViewById( R.id.situacao );
        parcela.setText(getItem(i).getNumeroParcela());
        valor.setText(String.valueOf(getItem(i).getValor()));
        mes.setText(format.format( getItem(i).getMesDisponibilizacao() ));
        enquadramento.setText(getItem(i).getEnquadramentoAuxilioEmergencial());
        situacao.setText( getItem(i).getSituacaoAuxilioEmergencial() );
        return view;
    }


    class Holder {
        TextView cpf;
        TextView data;
        long id;
    }



}
