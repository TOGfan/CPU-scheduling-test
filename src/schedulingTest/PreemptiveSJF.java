package schedulingTest;

import java.util.ArrayList;

public class PreemptiveSJF {

	public static int[][] arrangeArrival(int[][] processes) {
		ArrayList<int[]> arranged = new ArrayList<int[]>();
		for (int i = 0; i < processes.length; i++) {
			arranged.add(processes[i].clone());
		}
		int time = 0;
		int minBurstTimeIndex;// index of the shortest process already in queue
		int minBurstTimeIndex2;// index of the shortest process arriving before minBurstTimeIndex
		int[] temp;
		boolean a = true;
		for (int i = 0; i < arranged.size(); i++) {
			minBurstTimeIndex = i;
			for (int j = i + 1; j < arranged.size(); j++) {
				if (arranged.get(minBurstTimeIndex)[1] > time) {
					if (arranged.get(j)[1] < arranged.get(minBurstTimeIndex)[1]) {
						minBurstTimeIndex = j;
						a = true;
					}
				} else {
					if (arranged.get(j)[2] < arranged.get(minBurstTimeIndex)[2] && arranged.get(j)[1] <= time) {
						minBurstTimeIndex = j;
						a = false;
					}
				}
			}
			minBurstTimeIndex2 = minBurstTimeIndex;
			for (int j = i + 1; j < arranged.size(); j++) {
				if (arranged.get(j)[1] > time) {
					if (a) {
						if (arranged.get(j)[1] < arranged.get(minBurstTimeIndex)[2]
								+ arranged.get(minBurstTimeIndex)[1]) {
							if (arranged.get(j)[1] + arranged.get(j)[2] < arranged.get(minBurstTimeIndex2)[2]
									+ arranged.get(minBurstTimeIndex2)[1]
									&& arranged.get(j)[1] + arranged.get(j)[2] < arranged.get(minBurstTimeIndex)[2]
											+ arranged.get(minBurstTimeIndex)[1]) {
								minBurstTimeIndex2 = j;
							}
						}
					} else {
						if (arranged.get(j)[1] < arranged.get(minBurstTimeIndex)[2] + time) {
							if (arranged.get(j)[1] + arranged.get(j)[2] < arranged.get(minBurstTimeIndex2)[2]
									+ arranged.get(minBurstTimeIndex2)[1]
									&& arranged.get(j)[1] + arranged.get(j)[2] < arranged.get(minBurstTimeIndex)[2]
											+ time) {
								minBurstTimeIndex2 = j;
							}
						}
					}
				}

			}
			if (minBurstTimeIndex != minBurstTimeIndex2) {

				temp = arranged.get(minBurstTimeIndex).clone();
				if (temp[1] > time) {
					time = temp[1];
				}
				temp[2] = arranged.get(minBurstTimeIndex2)[1] - time;
				time += temp[2];
				arranged.get(minBurstTimeIndex)[2] = arranged.get(minBurstTimeIndex)[2] - temp[2];
				arranged.add(i, temp.clone());
			} else {
				temp = arranged.get(minBurstTimeIndex).clone();
				if (temp[1] > time) {
					time = arranged.get(minBurstTimeIndex)[1] - time;
				}
				time += temp[2];
				arranged.remove(minBurstTimeIndex);
				arranged.add(i, temp.clone());
			}
		}
		int[][] arrangedArray = new int[arranged.size()][3];
		for (int i = 0; i < arranged.size(); i++) {
			arrangedArray[i] = arranged.get(i);
		}
		return arrangedArray;
	}
}
