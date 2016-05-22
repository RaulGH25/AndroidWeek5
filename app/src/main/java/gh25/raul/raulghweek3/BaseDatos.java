package gh25.raul.raulghweek3;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Raul on 21/05/2016.
 */
public final class BaseDatos extends SQLiteOpenHelper {

    private Context context;

    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryCrearTablaMascotas = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS
                                        + "("
                                        + ConstantesBaseDatos.TABLE_MASCOTAS_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                        + ConstantesBaseDatos.TABLE_MASCOTAS_NAME   + " TEXT, "
                                        + ConstantesBaseDatos.TABLE_MASCOTAS_PHOTO  + " INTEGER"
                                        + ")";


        String queryCrearTablaLikes = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES
                + "("
                + ConstantesBaseDatos.TABLE_LIKES_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ConstantesBaseDatos.TABLE_LIKES_NUMBER    + " INTEGER, "
                + ConstantesBaseDatos.TABLE_LIKES_FK        + " INTEGER, "
                + "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_FK + ") "
                + "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")"
                + ")";

        db.execSQL(queryCrearTablaMascotas);

        db.execSQL(queryCrearTablaLikes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST " + ConstantesBaseDatos.TABLE_LIKES);
        onCreate(db);
    }


    // My methods

    public ArrayList<Mascota> obtenerTodasLasMascotas(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setName(registros.getString(1));
            mascotaActual.setPhotoID(registros.getInt(2));

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }



    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }


    public void insertarLike(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES, null, contentValues);
        db.close();
    }


    public int sumarLikesMascota(Mascota mascota){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_NUMBER+")"
                + " FROM "  + ConstantesBaseDatos.TABLE_LIKES
                + " WHERE "  + ConstantesBaseDatos.TABLE_LIKES_FK
                + " = "       + mascota.getId();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query, null);

        if(registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }



    public ArrayList<Mascota> obtenerFavoritos(){
        ArrayList<Mascota> mascotas = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();

        // First get the ID of the pets iwtht the 5 last likes pressed (not repeated pets)

        String query1 = "SELECT DISTINCT " + ConstantesBaseDatos.TABLE_LIKES_FK
                + " FROM " + ConstantesBaseDatos.TABLE_LIKES
                + " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_ID
                + " DESC LIMIT 5";

        Cursor registros1 = db.rawQuery(query1, null);

        // Get the information of each of the pets extracted

        while(registros1.moveToNext()){

            int mascotaID = registros1.getInt(0);

            String query2 = "SELECT * FROM "+ ConstantesBaseDatos.TABLE_MASCOTAS
                    + " WHERE "  + ConstantesBaseDatos.TABLE_MASCOTAS_ID
                    + " = "  + mascotaID;

            Cursor registros2 = db.rawQuery(query2, null);

            if(registros2.moveToNext()){
                Mascota mascotaActual = new Mascota();
                mascotaActual.setId(registros2.getInt(0));
                mascotaActual.setName(registros2.getString(1));
                mascotaActual.setPhotoID(registros2.getInt(2));
                mascotas.add(mascotaActual);
            }


        }


        /*String query = "SELECT * FROM "+ConstantesBaseDatos.TABLE_MASCOTAS
                + " WHERE "  + ConstantesBaseDatos.TABLE_MASCOTAS_ID
                + " = "  + "( SELECT " + ConstantesBaseDatos.TABLE_LIKES_FK
                + " FROM " + ConstantesBaseDatos.TABLE_LIKES
                + " ORDER BY " + ConstantesBaseDatos.TABLE_LIKES_ID
                + " DESC LIMIT 5 )";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while(registros.moveToNext()){

            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setName(registros.getString(1));
            mascotaActual.setPhotoID(registros.getInt(2));

            mascotas.add(mascotaActual);
        }*/

        db.close();

       return mascotas;
    }

}
