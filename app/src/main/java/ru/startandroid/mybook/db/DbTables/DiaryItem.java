package ru.startandroid.mybook.db.DbTables;

import java.util.Date;

/**
 * Created by Алексей on 30.10.2015.
 */
public class DiaryItem {
    int _id_dr;
    int _id_tp_tr;
    Date day;
    byte state;
    /*названия полей таблицы для Sqlite */
    public static final String TABLE_NAME = "diary";
    public static final String ID = "_id_dr";
    public static final String ID_TP_TR = "_id_tp_tr";
    public static final String DAY = "day_tr";
    public static final String STATE = "state";

    public DiaryItem() {
    }

    public static final String  CREATE_DIARY_TABLE ="create table "
            + TABLE_NAME + " ("
            + ID + " integer primary key autoincrement, "
            + ID_TP_TR + " integer ,  "
            + DAY + " text not null , "
            + STATE + " text not null, "
             + " FOREIGN KEY( " + ID_TP_TR + " ) REFERENCES "+
            TypeAndTrain.TABLE_NAME+" ( "+ TypeAndTrain.ID +" ));";

    public DiaryItem(int _id_tp_tr, byte state) {
        this._id_tp_tr = _id_tp_tr;
        this.day = new Date();
        this.state = state;
    }

    public DiaryItem(int _id_tp_tr, Date day, byte state) {
        this._id_tp_tr = _id_tp_tr;
        this.day = day;
        this.state = state;
    }

    public DiaryItem(int _id_dr, int _id_tp_tr, Date day, byte state) {

        this._id_dr = _id_dr;
        this._id_tp_tr = _id_tp_tr;
        this.day = day;
        this.state = state;
    }

    public void set_id_dr(int _id_dr) {
        this._id_dr = _id_dr;
    }

    public void set_id_tp_tr(int _id_tp_tr) {
        this._id_tp_tr = _id_tp_tr;
    }

    public void setDay() {

        this.day = new Date();
    }

    public void setDay(Date date) {
        this.day = date;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public int get_id_dr() {

        return _id_dr;
    }

    public int get_id_tp_tr() {
        return _id_tp_tr;
    }

    public Date getDay() {
        return day;
    }

    public byte getState() {
        return state;
    }

}
