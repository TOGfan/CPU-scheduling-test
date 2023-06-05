package schedulingTest;
public class SJF {

	  public static int[][]arrangeArrival(int[][] processes){
	 		int[][] arranged=processes.clone();
	 		int time=0;
	 		int minBurstTimeIndex;
	 		int[] temp;	
	 		for(int i=0;i<arranged.length;i++) {
	 			minBurstTimeIndex=i;
	 			for(int j=i+1;j<arranged.length;j++) {
	 				if(arranged[minBurstTimeIndex][1]>time) {
	 					if(arranged[j][1]<arranged[minBurstTimeIndex][1]) {
	 						minBurstTimeIndex=j;
	 					}
	 				}else {
			 			if(arranged[j][2]<arranged[minBurstTimeIndex][2]&&arranged[j][1]<=time) {
			 				minBurstTimeIndex=j;
			 			}
	 				}
	 			}
 				temp=arranged[minBurstTimeIndex].clone();
	 			if(temp[1]>time) {
	 				time=temp[1];
	 			}
	 			time=time+temp[2];
	 			for(int j=minBurstTimeIndex-1;j>=i;j--) {
	 				arranged[j+1]=arranged[j];
	 			}
	 			arranged[i]=temp.clone();
	 		}
	 		return arranged;
	  }
	}
	  
