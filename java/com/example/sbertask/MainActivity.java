package com.example.sbertask;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.sbertask.adapters.ValuteAdapter;
import com.example.sbertask.data.SberTaskAppDatabase;
import com.example.sbertask.model.Valute;
import com.example.sbertask.parser.UserXmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.http.*;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    ArrayList<Valute> valuteArrayList = new ArrayList<>();
    private ValuteAdapter valuteAdapter;
    private RecyclerView recyclerView;
    //    private DatabaseHandler dbHandler;
    private SberTaskAppDatabase sberTaskAppDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserXmlParser parser = new UserXmlParser();

        System.out.println("Hallaw");

        Log.d("Connection", "hi");

        recyclerView = findViewById(R.id.recyclerView);
        sberTaskAppDatabase = Room.databaseBuilder(this, SberTaskAppDatabase.class, "CarsDB")
                .fallbackToDestructiveMigration()
                .build();

      if(isOnline(this)){
          new Thread(new Runnable() {
              public void run() {
                  try{

                      clearAll();
                      String content = download("https://www.cbr.ru/scripts/XML_daily.asp");
                      Log.v("Checking mass","-----------------------------");
                      parser.parse(content);
                      valuteArrayList = parser.getUsers();
                      for (int i = 0; i < valuteArrayList.size(); i++) {
                          System.out.println(valuteArrayList.get(i));
                          createValute(valuteArrayList.get(i).getId(),valuteArrayList.get(i).getName(),valuteArrayList.get(i).getCharCode(),
                                  valuteArrayList.get(i).getNominal(),valuteArrayList.get(i).getRate());
                      }

                      loadCompleted();
                      Log.v("Checking mass","-----------------------------");
                  }
                  catch (IOException ex){

                  }
              }
          }).start();
      } else {
          loadCompleted();
      }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        valuteAdapter = new ValuteAdapter(this, valuteArrayList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(valuteAdapter);

        boolean kol = isOnline(this);
        Log.v("Connection",String.valueOf(kol));



    }

    public void addAndEditCars(final Valute valute, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.layout_convert, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView newCarTitle = view.findViewById(R.id.newCarTitle);
        final EditText quantityEditText = view.findViewById(R.id.nameEditText);
        Button convertButton = view.findViewById(R.id.convert);
        final TextView priceTextView = view.findViewById(R.id.priceTextView);

        newCarTitle.setText("Конвертер");

        if (valute != null) {
            quantityEditText.setText(valute.getNominal());
        }

        convertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double rate = Double.parseDouble(valute.getRate().replaceAll(",","."));
                double quantity = Double.parseDouble(quantityEditText.getText().toString().replaceAll(",","."));
                int nominal = Integer.parseInt(valute.getNominal());

                double perCoin  = rate/nominal;
                double result = quantity*perCoin;
                priceTextView.setText(String.valueOf(result));
            }
        });

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                });


        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }

    public void loadCompleted(){
        new GetAllValutesAsyncTask().execute();


    }

    private String download(String urlPath) throws IOException {
        StringBuilder xmlResult = new StringBuilder();
       // BufferedReader reader = null;
        BufferedReader br = null;
        InputStream stream = null;
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(urlPath);
            connection = (HttpsURLConnection) url.openConnection();
            stream = connection.getInputStream();
            //reader = new BufferedReader(new InputStreamReader(stream));
            InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "windows-1251");
            br = new BufferedReader(isr);
            String line;
            while ((line=br.readLine()) != null) {
                xmlResult.append(line);
            }
            Log.v("Symbs",xmlResult.toString());
            return xmlResult.toString();
        } finally {
            if (br != null) {
                br.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void deleteValute(Valute valute, int position) {

        valuteArrayList.remove(position);

        new DeleteValuteAsyncTask().execute(valute);
    }

    public void clearAll(){
        sberTaskAppDatabase.clearAllTables();
    }

    private void updateValute(String name, String charCode, String nominal, String rate, int position) {

        Valute valute = valuteArrayList.get(position);

        valute.setName(name);
        valute.setCharCode(charCode);
        valute.setNominal(nominal);
        valute.setRate(rate);

        new UpdateValuteAsyncTask().execute(valute);

        valuteArrayList.set(position, valute);

    }

    private void createValute(String id, String name, String charCode, String nominal, String rate) {
        Valute valute = new Valute();
        valute.setName(name);
        valute.setId(id);
        valute.setCharCode(charCode);
        valute.setNominal(nominal);
        valute.setRate(rate);
        new CreateValuteAsyncTask().execute(valute);
    }

    private class CreateValuteAsyncTask extends AsyncTask<Valute, Void, Void>{

        @Override
        protected Void doInBackground(Valute... valutes) {

            long id = sberTaskAppDatabase.getValuteDAO().addValute(valutes[0]);


            Valute valute = sberTaskAppDatabase.getValuteDAO().getValute(id);

            if (valute != null) {

                valuteArrayList.add(0, valute);

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            valuteAdapter.notifyDataSetChanged();
        }
    }

    private class UpdateValuteAsyncTask extends AsyncTask<Valute, Void, Void>{
        @Override
        protected Void doInBackground(Valute... valutes) {

            sberTaskAppDatabase.getValuteDAO().updateValute(valutes[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            valuteAdapter.notifyDataSetChanged();
        }
    }

    private class DeleteValuteAsyncTask extends AsyncTask<Valute, Void, Void>{

        @Override
        protected Void doInBackground(Valute... valutes) {
            sberTaskAppDatabase.getValuteDAO().deleteValute(valutes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            valuteAdapter.notifyDataSetChanged();
        }
    }


    private class GetAllValutesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            valuteAdapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            valuteArrayList.addAll(sberTaskAppDatabase.getValuteDAO().getAllValutes());
            return null;
        }
    }

}