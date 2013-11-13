import java.lang.Math;
public class test {
	public static void main(String[] args) {

		float f = 50.2f;
		f = aufrunden(f, 50);
		System.out.println(f);
	}
	/**erwartet float (die Zahl, die aufgerundet werden soll) und ein
	 * int (zu der aufgerundet werden soll, z.B. auf 50er, 100er, 1er, ..)
	 * return float (gerundeter Wert)*/
	public static float aufrunden(float zahl, int rundenZu){
		if(zahl%rundenZu != 0){
			zahl += rundenZu - zahl%rundenZu;
		}
		return zahl;
	}
}
