package kim.taeng.domain.future;

import lombok.Data;

@Data
public class ResultModel {

	private int id;

	private long delay;

	private String message;

	public String toString() {
		return "This Result Model From : " + id + " , " + id + " Sleeped " + delay + " ms and has Message : " + message;
	}
}
