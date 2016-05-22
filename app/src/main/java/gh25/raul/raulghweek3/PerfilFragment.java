package gh25.raul.raulghweek3;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.v7.widget.GridLayoutManager;

import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by Raul on 13/05/2016.
 */
public class PerfilFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private CircularImageView circularImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        // Circular Image Configuration
        circularImageView = (CircularImageView) v.findViewById(R.id.circularImageView);
        circularImageView.setBorderWidth(getResources().getDimension(R.dimen.perfilCircleImageBorderSize) * (int) getResources().getDisplayMetrics().density);
        circularImageView.setShadowRadius(getResources().getDimension(R.dimen.perfilCircleImageShadowSize));
        circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        circularImageView.setShadowColor(getResources().getColor(R.color.colorPrimaryLight));

        ArrayList<Mascota> datasetNuestraMascota = new ArrayList<>();
        int numberOfUploadedPicks = (int) Math.ceil(Math.random() * 10 + 4);
        for(int i=0; i<numberOfUploadedPicks; i++){
            datasetNuestraMascota.add(new Mascota("Nuestra Mascota", R.drawable.mascota1, 1, (int) Math.ceil(Math.random() * 100)));
        }

        mRecyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView_ListOurPet);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new RVAdapterPerfilMascota(datasetNuestraMascota, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
}
