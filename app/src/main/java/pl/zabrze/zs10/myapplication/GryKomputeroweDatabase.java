package pl.zabrze.zs10.myapplication;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GraKomputerowa.class },version = 1,exportSchema = false)
public abstract class GryKomputeroweDatabase extends RoomDatabase {

    public abstract GryKomputeroweDao zwrocDao();
}
