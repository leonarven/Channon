import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TimerTask;


public class Debug {

	private static HashMap<String, Long> timerStarts = new HashMap<String, Long>();
	private static HashMap<String, Long> timerEnds   = new HashMap<String, Long>();
	private static HashMap<String, Long> timers   = new HashMap<String, Long>();
	
	public static TimerTask debugMSGTimer = new TimerTask() {
		public void run() {
			Debug.println("TIMERS:");
			for(Entry<String, Long> entry : timers.entrySet())
				//if (entry.getValue()>=0) 
					Debug.println(entry.getKey()+" - "+entry.getValue()+"ms");
			Debug.println("COUNTS:");
				Debug.println("Asteroids - "+Channon.game.asteroids.size());
				Debug.println("Entities  - "+Channon.game.entities.size());
				Debug.println("Bullets   - "+BulletHandler.bullets.size());
				Debug.println("Stars     - "+Channon.game.field.stars.size());
		}
	};
	
	public static void start() {
		
	}
	
	public static void println(Object o) {
		if (Channon.DEBUG != true) return;
		System.out.println(o);
	}
	
	public static void initTimer(String id) {
		timerStarts.put(id, new Long(-1));
		timerEnds.put(id,   new Long(-1));
		timers.put(id,   new Long(-1));
	}
	public static void startTimer(String id) {
		timerStarts.put(id, new Long(System.currentTimeMillis()));
		timers.put(id,   new Long(-1));
	}
	public static void endTimer(String id) {
		timerEnds.put(id, new Long(System.currentTimeMillis()));
		timers.put(id,   timerEnds.get(id)-timerStarts.get(id));
	}
	public static void clearTimer(String id) {
		timerStarts.put(id, new Long(-1));
		timerEnds.put(id,   new Long(-1));
		timers.put(id,   new Long(-1));
	}
	public static long getTimer(String id) {
		return timers.get(id);
	}
	
	
}
