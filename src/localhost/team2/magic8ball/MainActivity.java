package localhost.team2.magic8ball;

import android.app.Activity;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener{

	private TextView tviewEightBallAns;
	private String[] answers;
	
	private SensorManager sensorManager;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // hide main activity title
		setContentView(R.layout.activity_main);

		answers = getResources().getStringArray(R.array.string_array_answers);
		tviewEightBallAns = (TextView) findViewById(R.id.tview_eight_ball_answer);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
		  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// TODO implement shake detection using sensor
	public void onShake() {
		// shake detected, so change text to answer
		tviewEightBallAns.setText(getAnswer());
	}

	public String getAnswer() {
		int answerIndex = (int)(Math.random() * answers.length);
		String answer = answers[answerIndex];
		return answer;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
		
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			double mAccelCurrent = Math.sqrt((double) (x*x + y*y + z*z));
            if (mAccelCurrent > 10) {
                 onShake();
            }
		}
	}
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
