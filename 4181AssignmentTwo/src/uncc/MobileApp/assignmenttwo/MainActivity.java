package uncc.MobileApp.assignmenttwo;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView pay, saved, seekbarText;
	SeekBar sb;
	EditText editTextPrice;
	double discount, price;
	RadioGroup radioGroup;
	final double[] discountLevel = { .10, .25, .50 };
	final String LOG_TAG = "demo";
	DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Define TextView and EditText Implementations in Interface
		seekbarText = (TextView) findViewById(R.id.textViewSeekbar);
		editTextPrice = (EditText) findViewById(R.id.editText1);
		sb = (SeekBar) findViewById(R.id.seekBar1);
		pay = (TextView) findViewById(R.id.textViewPay);
		saved = (TextView) findViewById(R.id.textViewSaved);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

		// Initializes price and discount, for if the initial button is changed
		setPrice(0.0);
		switch (radioGroup.getCheckedRadioButtonId()) {
		case R.id.radio0:
			setDiscount(discountLevel[0]);
			break;
		case R.id.radio1:
			setDiscount(discountLevel[1]);
			break;
		case R.id.radio2:
			setDiscount(discountLevel[2]);
			break;
		case R.id.radio3:
			setDiscount(sb.getProgress() / 100.00);
		}

		// Define the radio button group and its properties
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// checkedId is the RadioButton selected
				switch (checkedId) {
				case R.id.radio0:
					setDiscount(discountLevel[0]);
					break;
				case R.id.radio1:
					setDiscount(discountLevel[1]);
					break;
				case R.id.radio2:
					setDiscount(discountLevel[2]);
					break;
				case R.id.radio3:
					setDiscount(sb.getProgress() / 100.00);
					break;
				}
			}
		});

		// Define the seekbar and its Properties
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// Update display of progress bar with appended % symbol
				seekbarText.setText(progress + "%");

				// Updates the discount with the current seekbar progress if
				// custom is selected
				if (((RadioButton) findViewById(R.id.radio3)).isChecked())
					setDiscount(progress / 100.00);
			}

			// Implements required methods
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}
		});

		// Adds a listener for the text edit field
		editTextPrice.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
		
				
				// Sets the price from the value entered
				if (s.toString().equals(""))
					setPrice(0.0);
				else if ((s.toString().indexOf(".") >=0))
				{
					Toast.makeText(getApplicationContext(), "Caution: When entering change, please use no more than two decimal places.",
							   Toast.LENGTH_LONG).show();
				setPrice(Double.parseDouble(s.toString()));
				}
				else
					setPrice(Double.parseDouble(s.toString()));
			}

			// Implements required methods
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

		});

		// Setup Exit Button
		Button exitButton = (Button) findViewById(R.id.button1);
		exitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}// end of onCreateOptionsMenu

	public void setDiscount(double discount) {
		this.discount = discount;
		update();
	}

	public void setPrice(double price) {
		this.price = price;
		update();
	}

	private void update() {
		double discountPrice = (1 - discount) * price;
		
		//Use DecimalFormatter to Format Doubles Prior to Pushing to Display
		String creditsPayed = df.format(discountPrice);
		String creditsSaved = df.format(price-discountPrice);
		
		pay.setText(" $" + creditsPayed);
		saved.setText("$" + creditsSaved);
		
		//pay.setText(" $" + Double.toString(discountPrice));
		//saved.setText("$" + Double.toString(price - discountPrice));
	}

}// end of main activity