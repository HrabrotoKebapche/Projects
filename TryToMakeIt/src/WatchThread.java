import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Calendar;

public class WatchThread extends Thread {
	public void run() {
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
			File src = null;
			File target = null;
			while (true) {
				WatchKey key;
				currPath = null;
				src = null;
				target = null;

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

					if (kind == ENTRY_CREATE
							&& Calendar.getInstance().getTimeInMillis() - time > 1000) {
						System.out.println("ENTRY_CREATE");
						System.out.println(event.context());
						currPath = key.watchable().toString();
						time = Calendar.getInstance().getTimeInMillis();

						if (currPath.contains(dir1.toString())
								&& Calendar.getInstance().getTimeInMillis()
										- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir1", "dir2");
							src = new File(currPath + "\\" + event.context());
							target = new File(p + "\\" + event.context());
							copyInfo(src, target);
							// copyInfo(currPath + "\\" + event.context(), p +
							// "\\" + event.context());
							time2 = Calendar.getInstance().getTimeInMillis();
							src = null;
							target = null;
							p = null;
							currPath = null;
						} else if (currPath.contains(dir2.toString())
								&& Calendar.getInstance().getTimeInMillis()
										- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir2", "dir1");
							src = new File(currPath + "\\" + event.context());
							target = new File(p + "\\" + event.context());
							copyInfo(src, target);
							// copyInfo(currPath + "\\" + event.context(), p +
							// "\\" + event.context());
							time2 = Calendar.getInstance().getTimeInMillis();
							src = null;
							target = null;
							p = null;
							currPath = null;
							
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
						if (currPath.contains(dir1.toString())
								&& Calendar.getInstance().getTimeInMillis()
										- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir1", "dir2");
							try {
								Files.delete(Paths.get(p + "\\" + event.context()));
							} catch (IOException x) {
								System.err.println(x);
							}
							time2 = Calendar.getInstance().getTimeInMillis();

						} else if (currPath.contains(dir2.toString())
								&& Calendar.getInstance().getTimeInMillis()
										- time2 > 1000) {
							String p = currPath;
							p = p.replaceAll("dir2", "dir1");
							try {
								Files.delete(Paths.get(p + "\\" + event.context()));
							} catch (IOException x) {
								System.err.println(x);
							}
							time2 = Calendar.getInstance().getTimeInMillis();
						}

					}

				}

				key.reset();

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void copyInfo(File src, File target) throws IOException {
		if (src.isDirectory()) {
			if (!target.exists()) {
				target.mkdirs();
			}

			String files[] = src.list();

			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(target, file);

				copyInfo(srcFile, destFile);
			}
		}
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(src);
			os = new FileOutputStream(target);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException fnf) {
			System.out.println("Specified file not found :" + fnf);
		} catch (NullPointerException fnf) {
			System.out.println("Null pointer at :" + fnf);
		} finally {
			is.close();
			os.close();
		}
	}
}
