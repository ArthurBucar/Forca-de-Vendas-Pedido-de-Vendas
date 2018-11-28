package br.com.objetiveti.pedidodevendas.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Activities.AcNovoPedido;
import br.com.objetiveti.pedidodevendas.Adapters.PedidoAdapter;
import br.com.objetiveti.pedidodevendas.Dao.DaoPedido;
import br.com.objetiveti.pedidodevendas.Models.Pedido;
import br.com.objetiveti.pedidodevendas.Models.Pedido_Num;
import br.com.objetiveti.pedidodevendas.R;

public class PedidosFragment extends Fragment implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener, SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener {

    private RecyclerView oRecyclerView;
    private RecyclerView ooRecyclerView;
    private List<Pedido> pList;
    private List<Pedido_Num> nuList;
    private View v;

    private OnFragmentInteractionListener pListener;
    private View view;
    public PedidosFragment() {
        // Required empty public constructor
    }

    public static PedidosFragment newInstance(String param1, String param2) {
        PedidosFragment fragment = new PedidosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Pedidos");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pedidos, null);

        oRecyclerView = (RecyclerView)view.findViewById(R.id.rvListOrder);
        //ooRecyclerView = (RecyclerView)view.findViewById(R.id.rvListOrder);

        oRecyclerView.setHasFixedSize(true);
       // ooRecyclerView.setHasFixedSize(true);

        DaoPedido daoPedido = new DaoPedido();
        pList = daoPedido.getSetPedidoLista(getContext(), 0);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        oRecyclerView.setLayoutManager(llm);

        PedidoAdapter pedidoAdapter = new PedidoAdapter(getActivity(), pList);
        oRecyclerView.setAdapter(pedidoAdapter);

//        Pedido_Num_Adapter pedido_num_adapter = new Pedido_Num_Adapter(getActivity(), nuList);
//        ooRecyclerView.setAdapter(pedido_num_adapter);

        //View v = inflater.inflate(R.layout.fragment_pedidos, container, false);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), AcNovoPedido.class);
                startActivity(in);
            }
        });
        setHasOptionsMenu(true);
        return view;
                //inflater.inflate(R.layout.fragment_pedidos, container, false);
    }
//
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (pListener != null) {
            pListener.onFragmentInteraction(uri);
        }
    }

    public interface ItemLongClickListener {

        void onItemLongClick(View v,int pos);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            pListener = (OnFragmentInteractionListener) context;
        } else {
            //Toast.makeText(context, "Fragment Pedidos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        pListener = null;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onGlobalLayout() {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return false;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
