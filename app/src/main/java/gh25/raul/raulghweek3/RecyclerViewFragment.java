package gh25.raul.raulghweek3;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Raul on 13/05/2016.
 */
public class RecyclerViewFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Mascota> datasetMascotas;

    boolean dataBasedExistVariable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        // SharedPreference to determine if the data based have already been saved in the device.
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        // read shared preference
        boolean dataBasedExistVariable = sharedPref.getBoolean(getResources().getString(R.string.strDataBasedExistVariable), false);
        // create a new data base only if it wasnt already created. Else just load it.
        BaseDatos db = new BaseDatos(getActivity());
        if(!dataBasedExistVariable){
            Mascota.insertarMascotasDumis(db);
            // Save the shared preference to know that the data based was created
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean(getResources().getString(R.string.strDataBasedExistVariable), true);
            editor.commit();
        }
        // Get all the pets from the database
        datasetMascotas = db.obtenerTodasLasMascotas();


        // RecyclerView
        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView_List);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new RVAdapter(datasetMascotas, getActivity());
        mRecyclerView.setAdapter(mAdapter);


        return v;
    }

}
