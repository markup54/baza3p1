package pl.zabrze.zs10.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GryKomputeroweDao {

    @Insert
    public void wstawDoBazy(GraKomputerowa gra);

    @Delete
    public void usunGreZBazy(GraKomputerowa gra);

    @Update
    public void aktualizujGre(GraKomputerowa gra);

    @Query("Select * from gry_komputerowe")
    public List<GraKomputerowa> zwrocwszystkieGryZBazy();

    @Query("Select nazwa from gry_komputerowe where rok_wydania> :rokWydaniaParametr")
    public List<String> zwrocNazwyGierZRoku(int rokWydaniaParametr);




}
