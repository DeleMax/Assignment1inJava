package monitorconcurrency;

public class MonitorExclusion extends Thread {

	public MonitorClass monitor = new MonitorClass(1);
	private int threadId;
	private boolean available = true;
	
	public MonitorExclusion(MonitorClass monitor) {
	   this.monitor = monitor;
	}

	public void setThreadId(int threadId) {
	    this.threadId = threadId;
	}
	
	private void busyCode() {
	    int sleepPeriod = ((int) Math.round(500 * Math.random()));
	    try {
	       sleep(sleepPeriod);
	    } catch (InterruptedException e) {
	    }
	}
	
	private void nonCriticalCode(){
	   busyCode();
	}

	private synchronized void criticalCode(){
	   available = false;
	   busyCode();
	   available = true;
	}

	public static void main(String[] args) {
	   final int numberOfProcesses = 5;

	   MonitorClass monitor = new MonitorClass(1);
	   MonitorExclusion p[] = new MonitorExclusion[numberOfProcesses];

	   for (int i = 0; i < numberOfProcesses; i++)
	   {
	     p[i] = new MonitorExclusion(monitor);
	     p[i].setThreadId(p[i].hashCode());
	     p[i].start();
	   }

	}
	
	public void run(){
		int randomNumber = monitor.randomNumber();
		if(randomNumber == 1){
		   for (int i = 0; i < 2; i++) {
			   if(available){
				   System.out.print("Thread "+this.getId()+ " is started writing the shared resource.\n");
				   criticalCode();
				   System.out.print("Thread "+this.getId()+ " is stopped writing the shared resource.\n");
			   }
		   }
		}else{
		   for (int i = 0; i < 2; i++) {
			   System.out.print("Thread "+this.getId()+ " is started reading the shared resource.\n");
			   nonCriticalCode();
		       System.out.print("Thread "+this.getId()+ " is stopped reading the shared resource.\n");
		   }
		}
	}
}
