import static java.nio.file.StandardWatchEventKinds.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.*;
import java.util.Calendar;

public class Watch {
	public static void main(String[] args) throws IOException {

		Path dir1 = Paths.get("C:\\Users\\kristian\\Desktop\\dir1");
		Path dir2 = Paths.get("C:\\Users\\kristian\\Desktop\\dir2");
		try {
			WatchService ws = FileSystems.getDefault().newWatchService();

			WatchEvent.Kind<?>[] events = {
					StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_DELETE,
					StandardWatchEventKinds.ENTRY_MODIFY };
			dir1.register(ws, events);
			dir2.register(ws, events);

			long time = Calendar.getInstance().getTimeInMillis();
			long time2 = Calendar.getInstance().getTimeInMillis();
			String currPath = null;
			while (true) {
				WatchKey key;

				try {
					key = ws.take();
				} catch (InterruptedException x) {
					return;
				}

				for (WatchEvent<?> event : key.pollEvents()) {

					WatchEvent.Kind kind = event.kind();
					if (kind == OVERFLOW) {
						continue;
					}

					if (kind == ENTRY_CREATE && Calendar.getInstance().getTimeInMillis() - time > 1000) {
						System.out.println("ENTRY_CREATE");
						System.out.println(event.context());
						currPath = key.watchable().toString();
						time = Calendar.getInstance().getTimeInMillis();
						
						if (currPath.contains(dir1.toString())
								&& Calendar.getInstance().getTimeInMillis()- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir1", "dir2");
							copyInfo(currPath + "\\" + event.context(), p
									+ "\\" + event.context());
							System.out.println("qqqqqqqqqqqqqqqqqqqqqq");
							time2 = Calendar.getInstance().getTimeInMillis();
							
						}
						if (currPath.contains(dir2.toString())
								&& Calendar.getInstance().getTimeInMillis()- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir2", "dir1");
							copyInfo(currPath + "\\" + event.context(), p
									+ "\\" + event.context());
							time2 = Calendar.getInstance().getTimeInMillis();
						}

					}
					if (kind == ENTRY_MODIFY
							&& Calendar.getInstance().getTimeInMillis() - time > 1000) {
						System.out.println("ENTRY_MODIFY");
						System.out.println(event.context());
						currPath = key.watchable().toString();
						time = Calendar.getInstance().getTimeInMillis();

					}
					if (kind == ENTRY_DELETE) {
						System.out.println("ENTRY_DELETE");
						System.out.println(event.context());
						currPath = key.watchable().toString();

					}

				}

				key.reset();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void copyInfo(String pFrom, String pTo) throws IOException {
		System.out.println(pFrom);
		System.out.println(pTo);
		File src = new File(pFrom);
		File target = new File(pTo);
		InputStream is = null;
		OutputStream os = null;
		try {
			System.out.println(src);
			System.out.println(target);
			is = new FileInputStream(src);
			os = new FileOutputStream(target);
			System.out.println("zzzzzzzzzzzzzzzzzzz2222222222222222222222");
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			System.out.println("zzzzzzzzzzzzzzzzzzz");
		} finally {
			is.close();
			os.close();
		}
		System.out.println("wwwwwwwwwwwwwwwwwwww");
	}
}