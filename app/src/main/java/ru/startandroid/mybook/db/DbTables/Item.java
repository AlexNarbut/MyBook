package ru.startandroid.mybook.db.DbTables;

/**
 * Created by Алексей on 01.11.2015.
 */
public class Item {
    Integer id_tp_tr;
    Integer id_tr;
    String descr;
    String trainName;

    public Item(Integer id_tp_tr, Integer id_tr,String trainName, String descr) {
        this.id_tp_tr = id_tp_tr;
        this.id_tr = id_tr;
        this.trainName = trainName;
        this.descr = descr;
    }

    public String getTrainName() {
        return trainName;
    }
    public Integer getId_tp_tr() {
        return id_tp_tr;
    }

    public Integer getId_tr() {
        return id_tr;
    }

    public String getDescr() {
        return descr;
    }

    public void setId_tp_tr(Integer id_tp_tr) {
        this.id_tp_tr = id_tp_tr;
    }

    public void setId_tr(Integer id_tr) {
        this.id_tr = id_tr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
