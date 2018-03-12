package kim.taeng.utils;

import java.util.Random;

public class Commons {
	public long getRandomTime() {
		return new Random(System.currentTimeMillis()).nextInt(5000) + 1000;
	}
}
