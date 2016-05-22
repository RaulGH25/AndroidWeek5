package gh25.raul.raulghweek3;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Raul on 15/05/2016.
 */
public class RVAdapterPerfilMascota extends RecyclerView.Adapter<RVAdapterPerfilMascota.PetnViewHolder>{

    private Activity activity;
    private ArrayList<Mascota> datasetMascotas;

    public static class PetnViewHolder extends RecyclerView.ViewHolder {
        CardView cardview;
        TextView petLikes;
        ImageView yellowBone;
        ImageView petPhoto;

        PetnViewHolder(View itemView) {
            super(itemView);
            cardview = (CardView)itemView.findViewById(R.id.cardViewOurPet);
            petPhoto = (ImageView)itemView.findViewById(R.id.imageView_PhotoOurPet);
            petLikes = (TextView)itemView.findViewById(R.id.TextView_LikesNumnerOurPet);
            yellowBone = (ImageView)itemView.findViewById(R.id.imageView_YellowBoneOurPet);
        }
    }

    RVAdapterPerfilMascota(ArrayList<Mascota> datasetMascotas, Activity activity){
        this.datasetMascotas = datasetMascotas;
        this.activity = activity;
    }

    @Override
    public int getItemCount() {
        return datasetMascotas.size();
    }

    @Override
    public PetnViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_perfil, viewGroup, false);
        PetnViewHolder pvh = new PetnViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PetnViewHolder petnViewHolder, final int i) {
        final Mascota pet = datasetMascotas.get(i);
        petnViewHolder.petLikes.setText(String.valueOf(pet.getLikes()));
        petnViewHolder.yellowBone.setImageResource(R.drawable.huesodorado);
        petnViewHolder.petPhoto.setImageResource(R.drawable.mascota1);

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
