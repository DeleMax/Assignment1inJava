package monitorconcurrency;
import java.util.Random;


public class MonitorClass {

	private int counter;
	
	public MonitorClass(int counter) {
		if (counter <= 0) {
			throw new IllegalArgumentException("counter should be equal to one or above");
		}
		this.counter = counter;
	}
	
	public int randomNumber(){
		Random rand = new Random();
		return rand.nextInt(2); // This would generate randomly 0 or 1
	}
}
