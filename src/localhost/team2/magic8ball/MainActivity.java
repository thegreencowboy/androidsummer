package localhost.team2.magic8ball;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	// To-do: move answers to file for reading
	public String[] answers = { "It is certain", "It is decidely so",
			"Without a doubt", "Yes definitely", "You may rely on it",
			"As I see it yes", "Most likely", "Outlook good", "Yes",
			"Signs point to yes", "Reply hazy try again", "Ask again later",
			"Better not tell you now", "Concentrate and ask again",
			"Don\'t count on it", "My reply is no", "My sources say no",
			"Outlook not so good", "Very doubtful" };

	TextView tviewEightBallAns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // hide main activity title
		setContentView(R.layout.activity_main);
		tviewEightBallAns = (TextView) findViewById(R.id.tview_eight_ball_answer);
		onShake();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// To-do: implement shake using sensor
	public void onShake() {
		int answer = (int) (Math.random() * answers.length); // generate random number/index for eight ball answer
		tviewEightBallAns.setText(answers[answer]);
	}

}
