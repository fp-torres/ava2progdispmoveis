package com.example.organizadordeestudos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EstudosDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ESTUDOS = "estudos";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DISCIPLINA = "disciplina";
    private static final String COLUMN_HORARIO = "horario";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_ESTUDOS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DISCIPLINA + " TEXT, " +
                COLUMN_HORARIO + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDOS);
        onCreate(db);
    }

    // CREATE
    public void adicionarEstudo(String disciplina, String horario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DISCIPLINA, disciplina);
        values.put(COLUMN_HORARIO, horario);
        db.insert(TABLE_ESTUDOS, null, values);
        db.close();
    }

    // READ
    public List<Estudo> listarEstudos() {
        List<Estudo> lista = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ESTUDOS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(new Estudo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    // UPDATE
    public void atualizarEstudo(int id, String disciplina, String horario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DISCIPLINA, disciplina);
        values.put(COLUMN_HORARIO, horario);
        db.update(TABLE_ESTUDOS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // DELETE
    public void deletarEstudo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ESTUDOS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}