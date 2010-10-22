package com.thoughtworks.dbaccess;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
//import android.widget.TextView;

public class Welcome extends Activity {   
	   private DBAccess dbaccess;
	   private Button insertButton;
	   private EditText newRow;
	   private ListView listView;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        this.insertButton = (Button)this.findViewById(R.id.insertButton);
	        this.newRow = (EditText)this.findViewById(R.id.newRow);
	        this.listView = (ListView)this.findViewById(R.id.listView);
	        listView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	              // When clicked, show a toast with the TextView text
	              Toast.makeText(getApplicationContext(), position+""/*((TextView) view).getText()*/,
	                  Toast.LENGTH_SHORT).show();
	            }
	          });
	        
	        this.insertButton.setOnClickListener(new OnClickListener() {
	          //@Override
	          public void onClick(View v) {
	            insertData();
	            updateDBView();
	          }
	        });
	        
	        this.dbaccess = new DBAccess(this);
	        
	        clearTable();
//	        insertData();  
	        updateDBView();
	    }
	    
	    private void updateDBView() {
	    	String[] from = {"id", "name"};
	    	int[] to = new int[] { R.id.id, R.id.name };
	    	
	        List<HashMap<String,String>> rowList = this.dbaccess.selectAll();
	        /*StringBuilder sb = new StringBuilder();
	        sb.append("Names in database:\n");
	        for (String name : names) {
	           sb.append(name + "\n");
	        }
	        */
	        //Log.d("EXAMPLE", "names size - " + names.size());
	        this.listView.setAdapter((ListAdapter) new SimpleAdapter(this, rowList, R.layout.list_item, from, to));
	        //this.output.setText(sb.toString());
	      	
	    }
	    
	    private void insertData() {
	    	if(!this.newRow.getText().toString().equals(""))
	    		this.dbaccess.insert(this.newRow.getText().toString());
//	    	this.dbaccess.insert("Porky Pig");
//	        this.dbaccess.insert("Foghorn Leghorn");
//	        this.dbaccess.insert("Yosemite Sam"); 
	    }
	    
	    private void clearTable() {
	    	this.dbaccess.deleteAll();
	    }
}