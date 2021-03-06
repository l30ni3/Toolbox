package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

public class ShowAllMachines extends AppCompatActivity {

    private ArrayList<ClassListMachines> itemArrayList;  //List items Array
    private MyAppAdapter myAppAdapter; //Array Adapter
    private ListView listView; // ListView
    private boolean success = false; // boolean


    private static final String DB_URL = "jdbc:postgresql://10.0.2.2:5432/Toolbox";
    private static final String USER = "postgres";
    private static final String PASS = "postgresql";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_machines);

        listView = (ListView) findViewById(R.id.listAllMachines); //ListView Declaration
        itemArrayList = new ArrayList<ClassListMachines>(); // Arraylist Initialization

        // Calling Async Task
        SyncData orderData = new SyncData();
        orderData.execute("");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
    }


    // Async Task has three overrided methods,
    private class SyncData extends AsyncTask<String, String, String> {
        String msg = "Internet/DB_Credentials/Windows_FireWall_TurnOn Error, See Android Monitor in the bottom For details!";
        ProgressDialog progress;

        @Override
        protected void onPreExecute() //Starts the progress dailog
        {
            progress = ProgressDialog.show(ShowAllMachines.this, "Synchronising",
                    "List Loading! Please Wait...", true);
        }

        @Override
        protected String doInBackground(String... strings)  // Connect to the database, write query and add items to array list
        {
            try {

                /* Register jdbc driver class. */
                Class.forName("org.postgresql.Driver");

                /* Get the Connection object. */
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

                if (conn == null) {
                    success = false;
                } else {
                    // Change below query according to your own database.
                    String query = "SELECT * FROM \"Inventar\".\"Maschinen\";";
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if (rs != null) // if resultset not null, I add items to itemArraylist using class created
                    {
                        while (rs.next()) {
                            try {
                                itemArrayList.add(new ClassListMachines(
                                        rs.getString("Maschinen_ID"),
                                        rs.getString("Name"),
                                        rs.getString("Lagerort"),
                                        rs.getBoolean("Nutzung")));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        //msg = "Found";
                        success = true;
                    } else {
                        msg = "No Data found!";
                        success = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                msg = writer.toString();
                success = false;
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) // disimissing progress dialoge, showing error and setting up my ListView
        {
            progress.dismiss();
            Toast.makeText(ShowAllMachines.this, msg + "", Toast.LENGTH_LONG).show();
            if (success == false) {
            } else {
                try {
                    myAppAdapter = new MyAppAdapter(itemArrayList, ShowAllMachines.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    listView.setAdapter(myAppAdapter);
                } catch (Exception ex) {

                }

            }
        }
    }

    public class MyAppAdapter extends BaseAdapter

    {
        //class ViewHolder, which holds the textViews
        public class ViewHolder {
            TextView textID;
            TextView textName;
            TextView textLoc;
            TextView textAvailability;
        }

        public List<ClassListMachines> parkingList;

        public Context context;
        ArrayList<ClassListMachines> arraylist;

        private MyAppAdapter(List<ClassListMachines> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
            arraylist = new ArrayList<ClassListMachines>();
            arraylist.addAll(parkingList);
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) // inflating the layout and initializing widgets
        {

            View rowView = convertView;
            ViewHolder viewHolder = null;
            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.list_machines, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textID = (TextView) rowView.findViewById(R.id.machineID);
                viewHolder.textName = (TextView) rowView.findViewById(R.id.machineName);
                viewHolder.textLoc = (TextView) rowView.findViewById(R.id.machineLoc);
                viewHolder.textAvailability = (TextView) rowView.findViewById(R.id.available);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // get the names from ClassListMachines and set Text to ViewHolder
            viewHolder.textID.setText(parkingList.get(position).getId() + "");
            viewHolder.textName.setText(parkingList.get(position).getName() + "");
            viewHolder.textLoc.setText(parkingList.get(position).getLoc() + "");
            if (parkingList.get(position).getAvailability() == true)
                viewHolder.textAvailability.setText("ja");
            else
                viewHolder.textAvailability.setText("nein");

            return rowView;
        }
    }
}
