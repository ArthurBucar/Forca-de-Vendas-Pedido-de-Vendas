package br.com.objetiveti.pedidodevendas.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.List;

import br.com.objetiveti.pedidodevendas.Adapters.ClienteAdapterc;
import br.com.objetiveti.pedidodevendas.Dao.DaoClientesc;
import br.com.objetiveti.pedidodevendas.Models.Clientec;
import br.com.objetiveti.pedidodevendas.R;

public class ClientesFragment extends Fragment implements View.OnClickListener, ViewTreeObserver.OnGlobalLayoutListener{

    private RecyclerView cRecyclerView;
    private List<Clientec> cList;
    private View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener cListener;
    private View view;

    public ClientesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClientesFragment newInstance(String param1, String param2) {
        ClientesFragment fragment = new ClientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle("Clientes");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clientes, null);

        cRecyclerView = (RecyclerView)view.findViewById(R.id.rvListCliente);

        cRecyclerView.setHasFixedSize(true);

        DaoClientesc fonteClientes = new DaoClientesc();
        cList = fonteClientes.getSetCliente_Listac(getContext(),0);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        cRecyclerView.setLayoutManager(llm);

        ClienteAdapterc clienteAdapter = new ClienteAdapterc(getActivity(), cList);
        cRecyclerView.setAdapter(clienteAdapter);

//        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), NovoClienteActivity.class);
//                startActivity(intent);
//            }
//        });
        setHasOptionsMenu(true);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (cListener != null) {
            cListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_search, menu);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            cListener = (OnFragmentInteractionListener) context;
        } else {
          //  Toast.makeText(context, "Fragment Clientes", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        cListener = null;
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onGlobalLayout() {

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
        void onFragmentInteraction(Uri uri);
    }
}
