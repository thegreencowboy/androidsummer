package localhost.team2.magic8ball;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	// To-do: move answers to file for reading
	private TextView tviewEightBallAns;

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
		tviewEightBallAns.setText(createFileAnswers());
	}

	private String createFileAnswers() {
		File fileAnswers = new File(this.getFilesDir(), "FileAnswers");

		try {
			if (!fileAnswers.exists()){//Only write the file the first time
				writeFile(fileAnswers);
			}
			return readFile(fileAnswers);

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;
	}

	private static String readFile(File fileAns) throws IOException {
		BufferedReader bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileAns)));
		int answer = (int) (Math.random() * 20);
		String line = null;
		for (int i = 0; i <= answer; i++) {
			line = bfReader.readLine();
		}
		bfReader.close();
		return line;
		
	}

	private static void writeFile(File fileAns) throws IOException {
		PrintWriter writer = new PrintWriter(fileAns);
		String[] answers = { "It is certain", "It is decidely so",
				"Without a doubt", "Yes definitely", "You may rely on it",
				"As I see it yes", "Most likely", "Outlook good", "Yes",
				"Signs point to yes", "Reply hazy try again", "Ask again later",
				"Better not tell you now", "Concentrate and ask again",
				"Don\'t count on it", "My reply is no", "My sources say no",
				"Outlook not so good", "Very doubtful", "No" };

		for (String oneAns : answers) {//for each string in the array I create a string oneAns
			writer.write(oneAns+"\n");
		}
		writer.flush();
		writer.close();
	}
}
