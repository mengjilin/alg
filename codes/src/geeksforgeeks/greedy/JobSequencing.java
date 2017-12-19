package geeksforgeeks.greedy;

import java.util.*;
import alg.DisjointSet;

public class JobSequencing {

	public static void main(String[] args) {
		Job[] jobs = new Job[] {
			new Job("a",4,2),
			new Job("b",1,1),
			new Job("c",1,4),
			new Job("d",1,3),
		};
		String r = jobSequence(jobs);
		System.out.println("c,a".equals(r));
		
		jobs = new Job[] {
			new Job("a",2,100),
			new Job("b",1,19),
			new Job("c",2,27),
			new Job("d",1,25),
			new Job("e",3,13),
		};
		r = jobSequence(jobs);
		System.out.println("c,a,e".equals(r) || "a,c,e".equals(r));
	}
	
	static String jobSequence(Job[] jobs) {
		Arrays.parallelSort(jobs, (a, b) -> -Integer.compare(a.profit, b.profit));
		
		Job[] selectedJobs = new Job[jobs.length];
//		boolean[] alloted = new boolean[jobs.length];
//		for (int i = 0; i < jobs.length; i++) {
//			for (int j = Math.min(jobs.length, jobs[i].deadline) - 1; j >= 0; j--) {
//				if (!alloted[j]) {
//					selectedJobs[j] = jobs[i];
//					alloted[j] = true;
//					break;
//				}
//			}
//		}
		// use disjoint set
		int si = 0;
		int maxDeadline = jobs[0].deadline;
		for (Job job : jobs) if (maxDeadline < job.deadline) maxDeadline = job.deadline;
		DisjointSet ds = new DisjointSet(maxDeadline+1);
		for (Job job : jobs) {
			int avail = ds.find(job.deadline);
			if (avail > 0) {
				selectedJobs[si++] = job;
				ds.union(avail, ds.find(avail-1));
			}
		}
		Arrays.parallelSort(selectedJobs, 0, si, (a, b) -> Integer.compare(a.deadline, b.deadline));
		
		String ret = "";
		for (int i = 0; i < si; i++) {
			ret += "," + selectedJobs[i].id;
		}
		
		return ret.substring(1);
	}

	static class Job {
		public String id;
		public int deadline;
		public int profit;
		public Job() {}
		public Job(String id, int deadline, int profit) {
			this.id = id;
			this.deadline = deadline;
			this.profit = profit;
		}
	}
}
