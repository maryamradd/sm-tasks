package com.example.bluetoothlistener;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    final static int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    TextView noteTxt,devicesNum;
    Button BTbtn;
    ArrayList<String> devicesFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicesFound = new ArrayList<>();
        noteTxt = findViewById(R.id.noteTxt);
        devicesNum = findViewById(R.id.devicesNum);
        BTbtn = findViewById(R.id.BTbtn);
        noteTxt.setText("Click to start scanning");

        BTbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTxt.setText("Scanning ...");

        //get device bluetooth adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), " This device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
            finish();

        } else {

        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        //start discovery
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);
        bluetoothAdapter.startDiscovery();

            }
        }
        });

        }

    // to receive information about each device discovered

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

             if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                //bluetooth device found
                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                 devicesFound.add(device.getAddress());
                noteTxt.setText("Found \n\n\n\nDevices near you");
                devicesNum.setText(devicesFound.size());

            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregister the ACTION_FOUND receiver.
        unregisterReceiver(mReceiver);
    }



    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

        if (requestCode == REQUEST_ENABLE_BT && requestCode == RESULT_OK ){
            Toast.makeText(getApplicationContext(), "Bluetooth is on", Toast.LENGTH_SHORT).show();
        }
    }
}

