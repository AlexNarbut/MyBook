package ru.startandroid.mybook.db.DbTables;

/**
 * Created by Алексей on 30.10.2015.
 */
public class TypeAndTrain {

    int _id_tp_tr;
    int _id_tp;
    int _id_tr;

    /*названия полей таблицы для Sqlite */
    public static final String TABLE_NAME = "type_and_train";
    public static final String ID = "id_tp_tr";
    public static final String ID_TP = "id_tp";
    public static final String ID_TR = "id_tr";

    public static final String  CREATE_TABLE ="create table "
            + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + ID_TP + " integer , "
            + ID_TR + " integer , "
            + " FOREIGN KEY( " + ID_TP + " ) REFERENCES "+ Type.TABLE_NAME+"( "+ Type.ID +" ) "
            + " FOREIGN KEY( " + ID_TR + " ) REFERENCES "+ Training.TABLE_NAME+"( "+ Training.ID +" ));";

    public TypeAndTrain() {
    }

    public TypeAndTrain(int _id_tp_tr, int _id_tp, int _id_tr) {
        this._id_tp_tr = _id_tp_tr;
        this._id_tp = _id_tp;
        this._id_tr = _id_tr;
    }

    public TypeAndTrain(int _id_tp, int _id_tr) {
        this._id_tp = _id_tp;
        this._id_tr = _id_tr;
    }

    public int get_id_tp_tr() {
        return _id_tp_tr;
    }

    public int get_id_tp() {
        return _id_tp;
    }

    public int get_id_tr() {
        return _id_tr;
    }

    public void set_id_tp_tr(int _id_tp_tr) {
        this._id_tp_tr = _id_tp_tr;
    }

    public void set_id_tp(int _id_tp) {
        this._id_tp = _id_tp;
    }

    public void set_id_tr(int _id_tr) {
        this._id_tr = _id_tr;
    }
}
