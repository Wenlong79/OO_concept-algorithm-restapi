package com.wl.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Times {
	private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public interface Task {
		void execute();
	}
	
	public static void test(String title, Task task) {
		if (task == null) return;
		System.out.println("-------------------------------------");
		title = (title == null) ? "" : ("【" + title + "】");
		System.out.println(title);
		System.out.println("START：" + fmt.format(new Date()));
		long begin = System.currentTimeMillis();
		task.execute();
		long end = System.currentTimeMillis();
		System.out.println("END：" + fmt.format(new Date()));
		double delta = (end - begin) / 1000.0;
		System.out.println("TIME CONSUMED：" + delta + " s");
		System.out.println("-------------------------------------");
	}
}
