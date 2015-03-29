package ro.pub.cs.systems.pdsd.practicaltest01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01SecondaryActivity extends Activity {

private ButtonClickListener2 buttonClickListener = new ButtonClickListener2();
	
	final private int[] ButtonIds = {
			R.id.ok, R.id.cancel
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_secondary);
		
		for(int i = 0; i < ButtonIds.length; i++)
		{
			Button b = (Button)findViewById(ButtonIds[i]);
			b.setOnClickListener(buttonClickListener);
		}
		
		EditText ed = (EditText)findViewById(R.id.total);
		/*EditText left = (EditText)findViewById(R.id.left_edit_text);
		EditText right = (EditText)findViewById(R.id.right_edit_text);
		int count = Integer.parseInt(left.getText().toString()) + Integer.parseInt(right.getText().toString());
		ed.setText(Integer.toString(count));*/
		Intent intent = getIntent();
	    if (intent != null) {
	      String numberOfClicks = intent.getStringExtra("number_of_clicks");
	      if (numberOfClicks != null) {
	        ed.setText(numberOfClicks);
	      }
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_secondary, menu);
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
	
	private class ButtonClickListener2 implements View.OnClickListener{
		
		
		@Override
		public void onClick(View v) {
			if(v instanceof Button)
			{
				if (v.getId() == R.id.ok){
					setResult(RESULT_OK, new Intent());
					finish();
				} else if(v.getId() == R.id.cancel)
				{
					setResult(RESULT_CANCELED, new Intent());
					finish();
				}
			}
		}
	}
}
