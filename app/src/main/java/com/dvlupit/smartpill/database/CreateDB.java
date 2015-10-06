package com.dvlupit.smartpill.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by deck on 18/08/15.
 */
public class CreateDB extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "smartpillLocal.db";
    private static final int VERSAO = 1;

    private SQLDBCreations sqls;

    public CreateDB(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqls.createAI());
        db.execSQL(sqls.createAV());
        db.execSQL(sqls.createCAT());
        db.execSQL(sqls.createCON());
        db.execSQL(sqls.createMANU());
        db.execSQL(sqls.createREFER());
        db.execSQL(sqls.createMED());
        db.execSQL(sqls.createReminderSound());
        db.execSQL(sqls.createReminderSchedule());
        db.execSQL(sqls.createREM());
        db.execSQL(sqls.createUser());
        db.execSQL(sqls.createUserMedicine());
        db.execSQL(sqls.createRecipe());
        db.execSQL(sqls.createRecipeMedincine());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
