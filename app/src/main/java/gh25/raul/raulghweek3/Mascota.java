package gh25.raul.raulghweek3;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Raul on 09/05/2016.
 */
public class Mascota {

    private static final int LIKE = 1;

    private String name;
    private int likes = 0;
    private int photoID;
    private int id;


    public Mascota() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mascota(String name, int photoID, int id) {
        this.photoID = photoID;
        this.name = name;
        this.id = id;
    }

    public Mascota(String name, int photoID, int id, int likes) {
        this.photoID = photoID;
        this.name = name;
        this.id = id;
        this.likes = likes;
    }


    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhotoID() {
        return photoID;
    }

    public String getName() {
        return name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }





    static void insertarMascotasDumis(BaseDatos bd){

        ContentValues contentValues;

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 1");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota1);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 2");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota2);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 3");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota3);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 4");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota4);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 5");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota5);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 6");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota6);
        bd.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NAME, "Mascota 7");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO, R.drawable.mascota7);
        bd.insertarMascota(contentValues);

    }


    public void darLikeMascota(Mascota mascota, Context context){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_FK, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_NUMBER, LIKE);
        db.insertarLike(contentValues);
    }

    public int contarLikesMascota(Mascota mascota, Context context){
        BaseDatos db = new BaseDatos(context);
        return db.sumarLikesMascota(mascota);
    }

    

}
