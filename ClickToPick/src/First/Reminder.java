package First;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

class Reminder {

    Timer timer;

    public Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds * 1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            status("Failed",3);
            return;
        }
    }
    public static void status(String stat,long time) {
		try(FileWriter fw = new FileWriter("C:\\Users\\kristian\\Desktop\\myfile.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.print(Instant.now() + "|");
			    out.print(stat + "|");
			    out.print("time:"+time + "|");
			    out.print("files:" + 1 +"|");
			    out.print("\n");
			} catch (IOException e) {

			}
	}
}