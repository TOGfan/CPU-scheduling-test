package schedulingTest;

import java.util.ArrayList;

public class RR {
	public static int[][] arrangeArrival(int[][] processes, int quantum) {
		int[][] copy = FCFS.arrangeArrival(processes).clone();
		for (int i = 0; i < copy.length; i++) {
			copy[i] = copy[i].clone();
		}
		ArrayList<int[]> arranged = new ArrayList<int[]>();
		int[] temp = new int[3];
		int time = 0;
		int nextProcessBegin = copy[0][1];
		boolean processesLeft = true;
		while (processesLeft) {
			processesLeft = false;
			for (int i = 0; i < copy.length; i++) {
				if (copy[i][2] != 0) {
					processesLeft = true;
					if (copy[i][1] <= time) {
						if (copy[i][2] > quantum) {
							if (arranged.size() > 0 && arranged.get(arranged.size() - 1)[0] == copy[i][0]) {
								// if no other processes in queue finish this one
								time += quantum;
								arranged.get(arranged.size() - 1)[2] += quantum;
								copy[i][2] -= quantum;
							} else {
								temp = copy[i].clone();
								temp[2] = quantum;
								copy[i][2] -= quantum;
								arranged.add(temp.clone());
								time += quantum;
							}
						} else {
							if (arranged.size() > 0 && arranged.get(arranged.size() - 1)[0] == copy[i][0]) {
								// if no other processes in queue finish this one
								time += copy[i][2];
								arranged.get(arranged.size() - 1)[2] += copy[i][2];
								copy[i][2] = 0;
							} else {
								arranged.add(copy[i].clone());
								time += copy[i][2];
								copy[i][2] = 0;
								nextProcessBegin = Integer.MAX_VALUE;
							}
						}
					} else {
						if (copy[i][1] < nextProcessBegin) { // find the time next process begins in case queue is
																// empty
							nextProcessBegin = copy[i][1];
						}
					}
				}
			}
			if (nextProcessBegin > time) { // if the queue is empty wait
				time = nextProcessBegin;
			}
		}
		int[][] arrangedArray = new int[arranged.size()][3];
		for (int i = 0; i < arranged.size(); i++) {
			arrangedArray[i] = arranged.get(i);
		}
		return arrangedArray;
	}

}