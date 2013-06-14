package localhost.team2.magic8ball;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tviewEightBallAns;
	private String[] answers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // hide main activity title
		setContentView(R.layout.activity_main);
		
		Resources res = getResources();
		answers = res.getStringArray(R.array.string_array_answers);
		tviewEightBallAns = (TextView) findViewById(R.id.tview_eight_ball_answer);
		
		onShake();
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

}
