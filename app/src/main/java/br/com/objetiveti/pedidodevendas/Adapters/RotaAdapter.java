package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;

import br.com.objetiveti.pedidodevendas.Activities.AcMaps;
import br.com.objetiveti.pedidodevendas.Models.Rota;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Objetive TI on 25/09/2017.
 */

public class RotaAdapter extends RecyclerView.Adapter<RotaAdapter.MyViewHolder> {
    private List<Rota> mList;
    private LayoutInflater mLayoutInflater;
    private String dv;
    private String fq;
    private String sm;
    private int diaAtualNumero = 0;
    String currentDateTimeString;
    String weekDayu = "";
    public RotaAdapter(Context c, List<Rota> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public String weekDay(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_rota, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(view);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {

        myViewHolder.nomeCliente.setText(mList.get(position).getNomeCliente());
        myViewHolder.enderecoCliente.setText(mList.get(position).getEnderecoCliente());
        myViewHolder.telefoneCliente.setText(mList.get(position).getTelefoneCliente());

//        Calendar c = Calendar.getInstance();
//        int ano = c.get(Calendar.YEAR);
//        int mes = c.get(Calendar.MONTH)+1;
//        int dia = c.get(Calendar.DAY_OF_MONTH);
//        int hora = c.get(Calendar.HOUR_OF_DAY);
//        int minuto = c.get(Calendar.MINUTE);
//        Log.i("diaa", String.valueOf(dia));
//
//        if (dia >= 1 && dia <= 7){
//            diaAtualNumero = 1;
//        } else if (dia >= 8 && dia <= 15){
//            diaAtualNumero = 2 ;
//        } else if (dia >= 16 && dia <= 23){
//            diaAtualNumero = 3 ;
//        } else if (dia >= 24 && dia <= 31){
//            diaAtualNumero = 4;
//        }
//        Log.e("aaaaa", String.valueOf(diaAtualNumero));
//
//
//        if (weekDay(Calendar.getInstance()).equals("Monday")){
//            weekDayu = "SEG";
//        } else if (weekDay(Calendar.getInstance()).equals("Tuesday")) {
//            weekDayu = "TER";
//        } else if (weekDay(Calendar.getInstance()).equals("Wednesday")) {
//            weekDayu = "QUA";
//        } else if (weekDay(Calendar.getInstance()).equals("Thursday")) {
//            weekDayu = "QUI";
//        } else if (weekDay(Calendar.getInstance()).equals("Friday")) {
//            weekDayu = "SEX";
//        } else if (weekDay(Calendar.getInstance()).equals("Saturday")) {
//            weekDayu = "SAB";
//        } else if (weekDay(Calendar.getInstance()).equals("Sunday")) {
//            weekDayu = "DOM";
//        }
//
//
//        Log.i("Data", weekDayu);
//
//
//        dv = mList.get(position).getDias();
//        fq = mList.get(position).getFrequencia();
//        sm = mList.get(position).getSemana();
//
//        Log.i("tagFQ", fq);
//        Log.i("tagFQ", dv);
//        Log.i("tagFQ", sm);
//        if (fq.equals("S")){
//            if (weekDayu.equals(dv)){
//                myViewHolder.nomeCliente.setText(mList.get(position).getNomeCliente());
//                myViewHolder.enderecoCliente.setText(mList.get(position).getEnderecoCliente());
//                myViewHolder.telefoneCliente.setText(mList.get(position).getTelefoneCliente());
//            }
//        }
//        if (fq.equals("Q")){
//            if (sm.equals(1)){
//                if (diaAtualNumero % 2 == 1){
//                    if(weekDayu.equals(dv)){
//                        myViewHolder.nomeCliente.setText(mList.get(position).getNomeCliente());
//                        myViewHolder.enderecoCliente.setText(mList.get(position).getEnderecoCliente());
//                        myViewHolder.telefoneCliente.setText(mList.get(position).getTelefoneCliente());
//                    }
//                }
//            }
//        }
//        if (fq.equals("M")){
//            myViewHolder.nomeCliente.setText(mList.get(position).getNomeCliente());
//            myViewHolder.enderecoCliente.setText(mList.get(position).getEnderecoCliente());
//            myViewHolder.telefoneCliente.setText(mList.get(position).getTelefoneCliente());
//        }
    }

    @Override
    public int getItemCount() {
        //return Math.min(mList.size(), 3);
        return mList.size();
    }

    public void onClick(View v){}

    public void addListItem(Rota c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nomeCliente;
        public TextView enderecoCliente;
        public TextView telefoneCliente;
        public TextView cidade;

        public TextView enderecoMaps;

        public MyViewHolder(final View itemView) {
            super(itemView);

            enderecoCliente = (TextView) itemView.findViewById(R.id.tv_brands);
            nomeCliente = (TextView) itemView.findViewById(R.id.tv_models);
            telefoneCliente = (TextView) itemView.findViewById(R.id.tv_asaps);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, AcMaps.class);
                    intent.putExtra("endereco", enderecoCliente.getText());

                    context.startActivity(intent);
                }
            });
        }
    }
}
