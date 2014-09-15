
public class Debug {

	public static void println(Object o) {
		if (Channon.DEBUG != true) return;
		System.out.println(o);
	}
}
