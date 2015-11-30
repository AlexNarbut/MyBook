package ru.startandroid.mybook.db;

import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import ru.startandroid.mybook.db.DbTables.DiaryItem;
import ru.startandroid.mybook.db.DbTables.Item;
import ru.startandroid.mybook.db.DbTables.Training;
import ru.startandroid.mybook.db.DbTables.Type;
import ru.startandroid.mybook.db.DbTables.TypeAndTrain;

/**
 * Created by Алексей on 22.10.2015.
 */
public interface IDatabaseHandler {

        public void createTables(SQLiteDatabase db);
        public void addTraining(Training training);
        public void addType(Type type);
        public void addTypeandTran(TypeAndTrain typeAndTrain);
        public void addDiary(DiaryItem diaryItem);

        public Training getTraining(int id);
        public Type getType(int id);
        public TypeAndTrain getTypeandTrain(int id);
        public DiaryItem getDiaryItem(int id);

        public List<Training> getAllTraining();
        public List<Type> getAllType();
        public List<TypeAndTrain> getAllTypeAndTrain();
        public List<DiaryItem> getAllDiary();

        public int getTrainingCount();
        public int getTypeCount();
        public int getTypeAndTrainCount();
        public int getDiaryCount();

        public int updateTraining(Training contact);
        public int updateType(Type type);
        public int updateTypeAndTrain(TypeAndTrain type);
        public int updateDiary(DiaryItem diaryItem);

        public void deleteTraining(Training contact);
        public void deleteTraining(Type type);
        public void deleteTypeAndTrain(TypeAndTrain type);
        public void deleteDiaryItem(DiaryItem item);

        public Item SearchClientTraining(String str,String ex);
        public void deleteAll();
        public List<DiaryItem> SearchDieryList(GregorianCalendar date);
}