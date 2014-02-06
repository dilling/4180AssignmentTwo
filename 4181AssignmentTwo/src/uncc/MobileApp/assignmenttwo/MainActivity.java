package uncc.MobileApp.assignmenttwo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView pay, saved;
	double discount; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Define TextView and EditText Implementations in Interface
		final TextView tv1 = (TextView) findViewById(R.id.textView5);
		final EditText editText1 = (EditText) findViewById(R.id.editText1);
		
		// Add % Sign to End of SeekBar Value
		tv1.append("%");
		
		// Add Error Message for Unentered EditText Scenario
		editText1.setError("Enter List Price");
		
		// Define the Seekbar and its Properties
		SeekBar sb = (SeekBar) findViewById(R.id.seekBar1);
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
		
				// Update display of progress bar with appended % symbol
				tv1.setText(progress+"%");
			}
		});

		pay = (TextView) findViewById(R.id.textViewPay);
		saved = (TextView) findViewById(R.id.textViewSaved);
		
		((TextView) findViewById(R.id.editText1)).addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				pay.setText("");
				saved.setText("");				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}//end of onTextChanged
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}//end of onCreateOptionsMenu

}//end of main activity
