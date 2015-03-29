package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SimulareActivity extends Activity {
	static int pressMe = 1;
	static int pressMeToo = 1;
	final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

	private ButtonClickListener buttonClickListener = new ButtonClickListener();
	
	final private int[] ButtonIds = {
			R.id.button1, R.id.button2, R.id.navigate_to_secondary_activity_button
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simulare);
		for(int i = 0; i < ButtonIds.length; i++)
		{
			Button b = (Button)findViewById(ButtonIds[i]);
			b.setOnClickListener(buttonClickListener);
			
		}
		EditText ed = (EditText)findViewById(R.id.left_edit_text);
		ed.setText(Integer.toString(0));
		
		EditText ed1 = (EditText)findViewById(R.id.right_edit_text);
		ed1.setText(Integer.toString(0));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simulare, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		EditText ed = (EditText)findViewById(R.id.left_edit_text);
		savedInstanceState.putString("left_edit_text", ed.getText().toString());
		EditText rg = (EditText)findViewById(R.id.right_edit_text);
		savedInstanceState.putString("right_edit_text", rg.getText().toString());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		EditText ed = (EditText)findViewById(R.id.left_edit_text);
		EditText rg = (EditText)findViewById(R.id.right_edit_text);
		
		if(savedInstanceState != null)
		{
			if(savedInstanceState.getString("right_edit_text") != null)
			{
				rg.setText(savedInstanceState.getString("right_edit_text"));
			} 
			if(savedInstanceState.getString("left_edit_text") != null)
			{
				ed.setText(savedInstanceState.getString("left_edit_text"));	
			} 
		} else
		{
			rg.setText(Integer.toString(0));
			ed.setText(Integer.toString(0));	
		}
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
	  }
	
	private class ButtonClickListener implements View.OnClickListener{
		
		
		@Override
		public void onClick(View v) {
			EditText ed = (EditText)findViewById(R.id.left_edit_text);
			EditText rd = (EditText)findViewById(R.id.right_edit_text);
			if(v instanceof Button)
			{
				if(((Button)v).getId() == R.id.button1)
				{
					ed.setText(Integer.toString(pressMe++));
				}
				if(((Button)v).getId() == R.id.button2)
				{
					rd.setText(Integer.toString(pressMeToo++));
				}
				
				if(((Button)v).getId() == R.id.navigate_to_secondary_activity_button)
				{
					Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01SecondaryActivity");
					intent.putExtra("number_of_clicks",
				            String.valueOf(
				              Integer.parseInt(ed.getText().toString())
				              + Integer.parseInt(rd.getText().toString())
				            ));
					startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
				}
			}
		}
	}
}
