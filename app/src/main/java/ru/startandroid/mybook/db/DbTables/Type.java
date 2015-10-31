package ru.startandroid.mybook.db.DbTables;

/**
 * Created by Алексей on 26.10.2015.
 */
public class Type {
    int _id_tp;
    String _name_tp;

    /*названия полей таблицы для Sqlite */
    public static final String TABLE_NAME = "type";
    public static final String ID = "id_tp";
    public static final String NAME = "name_tp";

    public static final String  CREATE_TABLE ="create table "
            + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + NAME + " text not null);";

    public Type(String _name_tp) {
        this._name_tp = _name_tp;
    }

    public Type() {
    }

    public Type(int _id_tp, String _name_tp) {

        this._id_tp = _id_tp;
        this._name_tp = _name_tp;
    }

    public void set_id_tp(int _id_tp) {
        this._id_tp = _id_tp;
    }

    public void set_name_tp(String _name_tp) {
        this._name_tp = _name_tp;
    }

    public int get_id_tp() {

        return _id_tp;
    }

    public String get_name_tp() {
        return _name_tp;
    }

}
