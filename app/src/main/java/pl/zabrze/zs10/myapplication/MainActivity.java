package pl.zabrze.zs10.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private GryKomputeroweDatabase gryKomputeroweDatabase;

    private EditText editTextNAzwa;
    private EditText editTextOpis;
    private EditText editTextRokWydania;
    private EditText editTextWiekGraczy;
    private Spinner spinnerKategoria;
    private Button buttonDodajDoBazy;
    private ListView listView;
    private ArrayAdapter<GraKomputerowa> arrayAdapter;
    private List<GraKomputerowa> gryKomputerowe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNAzwa = findViewById(R.id.editTextTextPersonName);
        editTextOpis = findViewById(R.id.editTextTextPersonName2);
        editTextRokWydania = findViewById(R.id.editTextNumber);
        editTextWiekGraczy = findViewById(R.id.editTextNumber2);
        spinnerKategoria = findViewById(R.id.spinner);
        buttonDodajDoBazy =findViewById(R.id.button);
        listView = findViewById(R.id.listViewgry);

        RoomDatabase.Callback mojCallback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        gryKomputeroweDatabase = Room.databaseBuilder(
                getApplicationContext(),
                GryKomputeroweDatabase.class, "gry_komputerowe_db")
                .addCallback(mojCallback)
                .allowMainThreadQueries()
                .build();

        //dodajGreDoBazy();
        buttonDodajDoBazy.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nazwa =editTextNAzwa.getText().toString();
                        String opis = editTextOpis.getText().toString();
                        int rok =Integer.valueOf(editTextRokWydania.getText().toString());
                        int wiek = Integer.valueOf(editTextWiekGraczy.getText().toString());
                        String kategoria = spinnerKategoria.getSelectedItem().toString();
                        GraKomputerowa graKomputerowa = new GraKomputerowa(nazwa,opis,rok,wiek,kategoria);
                        dodajGreDoBazy(graKomputerowa);
                    }
                }
        );
        odczytGryZBazy();

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        gryKomputeroweDatabase.zwrocDao().usunGreZBazy(gryKomputerowe.get(i));
                        gryKomputerowe.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    private void odczytGryZBazy(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                      gryKomputerowe = gryKomputeroweDatabase.zwrocDao().zwrocwszystkieGryZBazy();
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                      arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                                              android.R.layout.simple_list_item_1,
                                              gryKomputerowe
                                              );
                                      listView.setAdapter(arrayAdapter);

                                                                          }
                                }
                        );
                    }
                }
        );


    }



    private void dodajGreDoBazy(GraKomputerowa graKomputerowa){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        gryKomputeroweDatabase.zwrocDao().wstawDoBazy(graKomputerowa);
                       /* gryKomputeroweDatabase.zwrocDao().wstawDoBazy(new GraKomputerowa("LOL","kolorowa gra nie dla ludzi z epilepsją",
                                2008,40,"moba"));
                        gryKomputeroweDatabase.zwrocDao().wstawDoBazy(new GraKomputerowa("saper","dla cierpliwych",
                                1980,8,"logiczna"));
*/
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Się udało", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
                }
        );
    }

}
/*




    private void usunPracownika (Pracownik pracownik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBasePracownicy.getDaoPracownicy().usunPracownika(pracownik);
                        pracownicy.remove(pracownik);
                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter.notifyDataSetChanged();
                                    }
                                }
                        );
                    }
                }
        );
    }


    private void wypiszPracownikow(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //Todo: odczyt z bazy
                        pracownicy = dataBasePracownicy.getDaoPracownicy().wypiszWszystkichPracownikow();

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,pracownicy);
                                        listView.setAdapter(arrayAdapter);
                                    }
                                }
                        );
                    }
                }
        );
    }


    private void dodajDaneDoBazyWTle(Pracownik pracownik){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        dataBasePracownicy.getDaoPracownicy().dodajPracownika(pracownik);
                        //TODO: dodać nowego pracownika z formularza
                        handler.post(new Runnable() {
                       @Override
                       public void run() {
                           pracownicy.add(pracownik);
                           arrayAdapter.notifyDataSetChanged();
                           Toast.makeText(MainActivity.this, "dodano do bazy", Toast.LENGTH_SHORT).show();
                       }
                   });
                    }
                }
        );
    }

}
 */