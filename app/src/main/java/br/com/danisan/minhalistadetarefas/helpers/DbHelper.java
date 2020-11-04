package br.com.danisan.minhalistadetarefas.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String LISTA_TABELA = "tabela";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ LISTA_TABELA
                +" (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 " nome TEXT NOT NULL ); ";

        try{
            db.execSQL(sql);
            Log.i("Db","Tabela criada com sucesso");
        }catch (Exception e){
            Log.i("Db","Erro ao executar Db", e );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
