package br.com.objetiveti.pedidodevendas.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Models.Pedido_Num;
import br.com.objetiveti.pedidodevendas.R;

/**
 * Created by Arthur Bucar on 10/3/2017.
 */

public class Pedido_Num_Adapter extends RecyclerView.Adapter<Pedido_Num_Adapter.MyViewHolder> {
    private List<Pedido_Num> nuList;
    private LayoutInflater nuLayoutInflater;

    public Pedido_Num_Adapter(Context c, List<Pedido_Num> nu){
        nuList = nu;
        nuLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = nuLayoutInflater.inflate(R.layout.item_num_pedido,viewGroup, false);

        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.dataPed.setText(nuList.get(position).getDataPed());
        myViewHolder.numPed.setText(nuList.get(position).getNumPed());
        myViewHolder.numPrePed.setText(nuList.get(position).getNumPrePed());
        myViewHolder.numSub.setText(nuList.get(position).getNumSub());
    }

    @Override
    public int getItemCount() {
        return nuList.size();
    }

    public void onClick(View v){}

    public void addListItem(Pedido_Num o, int position){
        nuList.add(o);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dataPed;
        public TextView numPed;
        public TextView numPrePed;
        public TextView numSub;

        public MyViewHolder(final View itemView) {
            super(itemView);
                dataPed = (TextView) itemView.findViewById(R.id.dataPed);
                numPed = (TextView) itemView.findViewById(R.id.numPed);
                numPrePed = (TextView) itemView.findViewById(R.id.numPrePed);
                numSub = (TextView) itemView.findViewById(R.id.numSub);
        }
    }
}
