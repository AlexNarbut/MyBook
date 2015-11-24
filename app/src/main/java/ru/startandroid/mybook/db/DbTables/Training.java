package ru.startandroid.mybook.db.DbTables;

/**
 * Created by Алексей on 24.10.2015.
 */
public class Training {
    int _id;
    String _name;
    String _rnd;
    String _tr_descr;

    /*названия полей таблицы для Sqlite */
    public static final String TABLE_NAME = "trainig";
    public static final String ID = "id";
    public static final String ROUNDS = "rnd";
    public static final String NAME = "namew";
    public static final String DESCRIP = "descr";


    public static final String  CREATE_TABLE ="create table "
            + TABLE_NAME + " ("
            + ID + " integer primary key , "
            + ROUNDS + " text not null, "
            + NAME + " text unique not null, "
            + DESCRIP + " text not null);";

    public Training(){
    }


    public Training(int _id, String _name,String _rnd, String _tr_descr) {
        this._id = _id;
        this._name = _name;
        this._rnd = _rnd;
        this._tr_descr = _tr_descr;
    }

    public Training(String _name,String _rnd, String _tr_descr) {
        this._name = _name;
        this._rnd = _rnd;
        this._tr_descr = _tr_descr;
    }


    public String get_rnd() {
        return _rnd;
    }

    public void set_rnd(String _rnd) {
        this._rnd = _rnd;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }


    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }


    public void set_tr_descr(String _tr_descr) {
        this._tr_descr = _tr_descr;
    }

    public String get_tr_descr() {
        return _tr_descr;
    }




}
