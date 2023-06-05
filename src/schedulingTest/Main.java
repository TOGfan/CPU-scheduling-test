
package schedulingTest;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static int[] findWaitingTime(int processes[][], int n) {
		int[] wt = new int[n];
		int time = 0;
		for (int i = 0; i < processes.length; i++) {
			if (processes[i][1] > time) {
				time = processes[i][1];
			}
			time += processes[i][2];
			wt[processes[i][0] - 1] = time - processes[i][1] - processes[i][2];

		}
		return wt;
	}

	public static float findavgTime(int wt[]) {
		int total_wt = 0;
		for (int i = 0; i < wt.length; i++) {
			total_wt = total_wt + wt[i];
		}
		return (float) total_wt / wt.length;
	}

	public static void printProcesses(int processes[][]) {
		for (int i = 0; i < processes.length; i++) {
			for (int j = 0; j < processes[i].length; j++) {
				System.out.print(processes[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void saveProcesses(int processes[][], PrintWriter writer) {
		for (int i = 0; i < processes.length; i++) {
			for (int j = 0; j < processes[i].length; j++) {
				writer.print(processes[i][j] + " ");
			}
			writer.println();
		}
	}

	public static int[][] generateRandomProcesses(int n) {
		Random random = new Random();
		int[][] processes = new int[n][3];
		for (int i = 0; i < n; i++) {
			processes[i][0] = i + 1;
			processes[i][1] = random.nextInt() % 51 + 50;
			processes[i][2] = random.nextInt() % 51 + 50;
		}
		return processes;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of processes:");
		int n = scanner.nextInt();
		System.out.println("Enter RR quantum:");
		int quantum = scanner.nextInt();
		scanner.close();
		int[][] processes = generateRandomProcesses(n);
		int[][] FCFSarr = FCFS.arrangeArrival(processes);
		int[][] SJFarr = SJF.arrangeArrival(processes);
		int[][] PreemptiveSJFarr = PreemptiveSJF.arrangeArrival(processes);
		int[][] RRarr = RR.arrangeArrival(processes, quantum);
		System.out.println("Processes (ID, arrival time, burst time):\n");
		printProcesses(processes);
		System.out.println("Processes aranged by FCFS:");
		printProcesses(FCFSarr);
		System.out.println("Processes aranged by SJF:");
		printProcesses(SJFarr);
		System.out.println("Processes aranged by PreemptiveSJF:");
		printProcesses(PreemptiveSJFarr);
		System.out.println("Processes aranged by RR:");
		printProcesses(RRarr);
		System.out.println("Average waiting time of FCFS: " + findavgTime(findWaitingTime(FCFSarr, processes.length)));
		System.out.println("Average waiting time of SJF: " + findavgTime(findWaitingTime(SJFarr, processes.length)));
		System.out.println("Average waiting time of PreemptiveSJF: "
				+ findavgTime(findWaitingTime(PreemptiveSJFarr, processes.length)));
		System.out.println("Average waiting time of RR: "
				+ findavgTime(findWaitingTime(RR.arrangeArrival(processes, quantum), processes.length)));
		try {
			PrintWriter results = new PrintWriter("Results.txt");
			results.println("Processes (ID, arrival time, burst time):\n");
			saveProcesses(processes, results);
			results.println("Processes aranged by FCFS:");
			saveProcesses(FCFS.arrangeArrival(processes), results);
			results.println("Processes aranged by SJF:");
			saveProcesses(SJF.arrangeArrival(processes), results);
			results.println("Processes aranged by PreemptiveSJF:");
			saveProcesses(PreemptiveSJF.arrangeArrival(processes), results);
			results.println("Processes aranged by RR:");
			saveProcesses(RR.arrangeArrival(processes, quantum), results);
			results.println("Average waiting time of FCFS: " + findavgTime(findWaitingTime(FCFSarr, processes.length)));
			results.println("Average waiting time of SJF: " + findavgTime(findWaitingTime(SJFarr, processes.length)));
			results.println("Average waiting time of PreemptiveSJF: " + findavgTime(findWaitingTime(PreemptiveSJFarr, processes.length)));
			results.println("Average waiting time of RR: " + findavgTime(findWaitingTime(RR.arrangeArrival(processes, quantum), processes.length)));
			results.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
