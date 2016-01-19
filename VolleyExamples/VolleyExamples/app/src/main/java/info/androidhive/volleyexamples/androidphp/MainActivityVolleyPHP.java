package info.androidhive.volleyexamples.androidphp;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import info.androidhive.volleyexamples.R;
import info.androidhive.volleyexamples.app.AppController;

public class MainActivityVolleyPHP extends Activity {

	String url = "http://myandroidng.com/Apartment/volleyphp/take_order.php";
	String URLPHP = "http://myandroidng.com/Apartment/WS/ws_crud_maintainance_1.php";

	String item_name;
	String item_mth;

	EditText item_et;
	EditText item_month_et;

	ProgressDialog PD;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_android_php);

		PD = new ProgressDialog(this);
		PD.setMessage("Loading.....");
		PD.setCancelable(false);

		item_et = (EditText) findViewById(R.id.item_et_id);
		item_month_et = (EditText) findViewById(R.id.item_et_mthid);

	}

	public void insert(View v) {
		PD.show();
		item_name = item_et.getText().toString();

		StringRequest postRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						PD.dismiss();
						item_et.setText("");
						Toast.makeText(getApplicationContext(),
								"Data Inserted Successfully",
								Toast.LENGTH_SHORT).show();

					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						PD.dismiss();
						Toast.makeText(getApplicationContext(),
								"failed to insert", Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();
				params.put("item_name", item_name);

				return params;
			}
		};

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(postRequest);
	}

	public void insertPHP(View v) {
		PD.show();
		item_name = item_et.getText().toString();
		item_mth = item_month_et.getText().toString();

//      Post params to be sent to the server
		HashMap<String, String> params = new HashMap<String, String>();

		StringRequest postRequest = new StringRequest(Request.Method.POST, URLPHP,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						PD.dismiss();
						item_et.setText("");
						item_month_et.setText("");
						//item_et.setText("");
						Toast.makeText(getApplicationContext(),
								"Response Object " + response
								,Toast.LENGTH_SHORT).show();
//
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				PD.dismiss();
				Toast.makeText(getApplicationContext(),
						"failed to update...", Toast.LENGTH_SHORT).show();
			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<String, String>();

				params.put("mth",item_mth);
				params.put("yr","2016");
				params.put("O_id","4");
				params.put("f_id", "1");
				params.put("amt", "1001");
				params.put("pdby",item_name);

				return params;
			}
		};

		// add the request object to the queue to be executed
		AppController.getInstance().addToRequestQueue(postRequest);
	}

	public void read(View v) {
		try
		{
			Intent read_intent = new Intent(MainActivityVolleyPHP.this, ReadData.class);
			startActivity(read_intent);
		}
		catch(Exception x)
		{
			Toast.makeText(getApplicationContext(),x.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}