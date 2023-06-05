package schedulingTest;

public class FCFS {
	public static int[][] arrangeArrival(int[][] processes) { // return sorted by arrival time
		int[][] arranged = processes.clone();
		int[] temp = new int[3];
		for (int i = 0; i < arranged.length - 1; i++) {
			for (int j = 0; j < arranged.length - i - 1; j++) {
				if (arranged[j][1] > arranged[j + 1][1]) {
					temp = arranged[j];
					arranged[j] = arranged[j + 1];
					arranged[j + 1] = temp;
				}
			}
		}
		return arranged;
	}
}
