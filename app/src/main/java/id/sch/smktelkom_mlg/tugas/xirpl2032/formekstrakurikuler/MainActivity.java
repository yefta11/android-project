package id.sch.smktelkom_mlg.tugas.xirpl2032.formekstrakurikuler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    EditText etNama;
    Button bDaftar;
    TextView tvHasil, tvHasil2, tvEkstra, tvHasil3, tvHasil4;
    RadioButton rbLK, rbPR;
    CheckBox cbBasket, cbVoli, cbFutsal, cbPaduanSuara;
    int nEkstra;
    Spinner spKelas, spJurusan;
    String[][] arJurusan = {{"RPL 1", "RPL 2", "RPL 3", "TKJ 1", "TKJ 2", "TKJ 3"},
            {"RPL 1", "RPL 2", "RPL 3", "TKJ 1", "TKJ 2"},
            {"RPL 1", "RPL 2", "TKJ 1", "TKJ 2"}};
    ArrayList<String> listJurusan = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        bDaftar = (Button) findViewById(R.id.buttonDaftar);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        tvHasil2 = (TextView) findViewById(R.id.textViewHasil2);
        tvHasil3 = (TextView) findViewById(R.id.textViewHasil3);
        tvHasil4 = (TextView) findViewById(R.id.textViewHasil4);
        rbLK = (RadioButton) findViewById(R.id.radioButtonLk);
        rbPR = (RadioButton) findViewById(R.id.radioButtonPr);
        cbBasket = (CheckBox) findViewById(R.id.checkBoxBasket);
        cbVoli = (CheckBox) findViewById(R.id.checkBoxVoli);
        cbFutsal = (CheckBox) findViewById(R.id.checkBoxFutsal);
        cbPaduanSuara = (CheckBox) findViewById(R.id.checkBoxPaduanSuara);
        tvEkstra = (TextView) findViewById(R.id.textViewEkstra);
        cbBasket.setOnCheckedChangeListener(this);
        cbVoli.setOnCheckedChangeListener(this);
        cbFutsal.setOnCheckedChangeListener(this);
        cbPaduanSuara.setOnCheckedChangeListener(this);
        spKelas = (Spinner) findViewById(R.id.spinnerKelas);
        spJurusan = (Spinner) findViewById(R.id.spinnerJurusan);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listJurusan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJurusan.setAdapter(adapter);

        spKelas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listJurusan.clear();
                listJurusan.addAll(Arrays.asList(arJurusan[pos]));
                adapter.notifyDataSetChanged();
                spJurusan.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        bDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProcess();
            }
        });
    }

    private void doProcess() {

        if (isValid()) {
            String nama = etNama.getText().toString();
            tvHasil.setText("Atas nama " + nama);
        }

        String gender = null;

        if (rbLK.isChecked()) {
            gender = rbLK.getText().toString();
        } else if (rbPR.isChecked()) {
            gender = rbPR.getText().toString();
        }

        if (gender == null) {
            tvHasil2.setText("Anda belum memilih Gender");
        } else {
            tvHasil2.setText("Gender Anda ; " + gender);
        }

        String ekstra = "Ekstrakurikuler yang Anda ambil adalah ;\n";
        int startlen = ekstra.length();
        if (cbBasket.isChecked()) ekstra += cbBasket.getText() + " \n";
        if (cbVoli.isChecked()) ekstra += cbVoli.getText() + " \n";
        if (cbFutsal.isChecked()) ekstra += cbFutsal.getText() + " \n";
        if (cbPaduanSuara.isChecked()) ekstra += cbPaduanSuara.getText() + " \n";

        if (ekstra.length() == startlen) ekstra += "Anda Belum memilih Ekstrakurikuler!";

        tvHasil3.setText(ekstra);

        tvHasil4.setText("Kelas " + spKelas.getSelectedItem().toString()
                + "  " + spJurusan.getSelectedItem().toString());
    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        return valid;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundbutton, boolean isChecked) {
        if (isChecked) nEkstra += 1;
        else nEkstra -= 1;

        tvEkstra.setText("Ekstrakurikuler (" + nEkstra + "terpilih)");
    }
}