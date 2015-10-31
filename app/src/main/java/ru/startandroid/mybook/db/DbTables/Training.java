package ru.startandroid.mybook.db.DbTables;

/**
 * Created by Алексей on 24.10.2015.
 */
public class Training {
    int _id;
    String _name;
    String _main_ex_1;
    String _main_ex_2;
    String _tr_descr;

    /*названия полей таблицы для Sqlite */
    public static final String TABLE_NAME = "trainig";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String MAIN_EX_1 = "main_ex_1";
    public static final String MAIN_EX_2 = "main_ex_2";
    public static final String DESCRIP = "descr";


    public static final String  CREATE_TABLE ="create table "
            + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + NAME + " text not null, "
            + MAIN_EX_1 + " text not null, "
            + MAIN_EX_2 + " text not null, "
            + DESCRIP + " text not null);";

    public Training(){
    }


    public Training(int _id, String _name, String _main_ex_1, String _main_ex_2, String _tr_descr) {
        this._id = _id;
        this._name = _name;
        this._main_ex_1 = _main_ex_1;
        this._main_ex_2 = _main_ex_2;
        this._tr_descr = _tr_descr;
    }

    public Training(String _name, String _main_ex_1, String _main_ex_2, String _tr_descr) {
        this._name = _name;
        this._main_ex_1 = _main_ex_1;
        this._main_ex_2 = _main_ex_2;
        this._tr_descr = _tr_descr;
    }


    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_main_ex_1() {
        return _main_ex_1;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_main_ex_1(String _main_ex_1) {
        this._main_ex_1 = _main_ex_1;
    }

    public void set_main_ex_2(String _main_ex_2) {
        this._main_ex_2 = _main_ex_2;
    }

    public String get_main_ex_2() {

        return _main_ex_2;
    }

    public void set_tr_descr(String _tr_descr) {
        this._tr_descr = _tr_descr;
    }

    public String get_tr_descr() {
        return _tr_descr;
    }




}
