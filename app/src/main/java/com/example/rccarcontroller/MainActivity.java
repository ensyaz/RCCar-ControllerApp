package com.example.rccarcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter myBluetooth;  //Bizim Bluetoothumuz
    private Set<BluetoothDevice> pairedDevices;  //Etraftaki cihazların verileri
    Button toggle_button,pair_button;
    ListView pairedlist;
    public static String EXTRA_ADRESS = "device_address";
    ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBluetooth=BluetoothAdapter.getDefaultAdapter();  //Bluetooth ozelligi olup olmadıgını gosterecek
        toggle_button=(Button) findViewById(R.id.button_toggle); //Button baglandı
        pair_button=(Button) findViewById(R.id.button_pair);  //İkinci Button Tanıtıldı
        pairedlist=(ListView) findViewById(R.id.device_list); //ListView Tanıtıldı



        toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBluetooth();
                

            }


        });

        pair_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listdevice();
            }
        });


    }

    private void toggleBluetooth() {  //Bluetooth'u aktiflestirme veya pasiflestirme
        if(myBluetooth==null)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth Cihazı Yok",Toast.LENGTH_SHORT).show(); //Bluetooth'un açık olmadığı durumu gösteriyor.
        }

        if(!myBluetooth.isEnabled())
        {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

        }
        if(myBluetooth.isEnabled())
        {
            myBluetooth.disable();
        }

    }

    private void listdevice() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice bt: pairedDevices)
            {
                list.add(bt.getName() + "\n"+bt.getAddress()); //Listeye bulduğu cihazların adlarını ve adreslerini ekler.

            }
        }

        else
        {
            Toast.makeText(getApplicationContext(), "Eşleşmiş Cihaz Yok",Toast.LENGTH_SHORT).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        pairedlist.setAdapter(adapter);
        pairedlist.setOnItemClickListener(selectDevice); // Seçilen Cihazı Bağlamak için select device'a geçilir.





    }

    public AdapterView.OnItemClickListener selectDevice = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length()-17); //Adresi Tanımlanmış Oldu

            Intent comintent = new Intent (MainActivity.this, Comunication.class);
            comintent.putExtra(EXTRA_ADRESS,address ); //Herhangi bir layouttan diğer layout'a aktarır.
            startActivity(comintent);
        }
    };
}
