package pl.zabrze.zs10.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "gry_komputerowe")
public class GraKomputerowa {

    //Enum<String>kategorie=['bitewniak']
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_gry")
    private int id;
    private String nazwa;
    private String opis;
    @ColumnInfo(name = "rok_wydania")
    private int rokWydania;
    private int minimalnyWiek;
    private String kategoria;

    @Ignore
    public GraKomputerowa() {
    }

    public GraKomputerowa(String nazwa, String opis, int rokWydania, int minimalnyWiek, String kategoria) {
        id = 0;
        this.nazwa = nazwa;
        this.opis = opis;
        this.rokWydania = rokWydania;
        this.minimalnyWiek = minimalnyWiek;
        this.kategoria = kategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getRokWydania() {
        return rokWydania;
    }

    public void setRokWydania(int rokWydania) {
        this.rokWydania = rokWydania;
    }

    public int getMinimalnyWiek() {
        return minimalnyWiek;
    }

    public void setMinimalnyWiek(int minimalnyWiek) {
        this.minimalnyWiek = minimalnyWiek;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    @Override
    public String toString() {
        return       "nazwa:" + nazwa + ", opis:" + opis;
    }
}
