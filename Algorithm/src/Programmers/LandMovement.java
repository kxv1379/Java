package Programmers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * 
  * @FileName : LandMovement.java
  * @Project : Algorithm
  * @Date : 2020. 2. 10. 
  * @Author : Kim DongJin
  * @Comment :프로그래머스 지형이동 문제. 완료
 */ 

class Point {
	int x;
	int y;
	int diff;
	
	public Point(int x, int y, int diff) {
		this.x = x;
		this.y = y;
		this.diff = diff;
	}
	
	public void move(int x, int y,int[][] land, boolean[][] explored, Queue<Point> group,PriorityQueue<Point> priorityQueue, int height) {
		int gap = Math.abs(land[this.x][this.y]-land[x][y]);
		if(gap<=height) {
			diff = Math.min(gap, diff);
			group.offer(new Point(x, y, 0));
			explored[x][y] = true;
			return;
		}
		
		priorityQueue.offer(new Point(x, y, gap));
	}
	
	public void up(int[][] land, boolean[][] explored, Queue<Point> group,PriorityQueue<Point> priorityQueue, int height) {
		if(y-1<0 || explored[x][y-1])
			return;
		
		move(x, y-1,land, explored, group, priorityQueue, height);
	}
	
	public void down(int[][] land, boolean[][] explored, Queue<Point> group, PriorityQueue<Point> priorityQueue,int height) {
		if(y+1>=land.length || explored[x][y+1])
			return;
		
		move(x, y+1,land, explored, group, priorityQueue, height);
	}
	
	public void right(int[][] land, boolean[][] explored, Queue<Point> group,PriorityQueue<Point> priorityQueue, int height) {
		if(x+1>=land.length || explored[x+1][y])
			return;
		
		move(x+1, y,land, explored, group, priorityQueue, height);
	}
	
	public void left(int[][] land, boolean[][] explored, Queue<Point> group, PriorityQueue<Point> priorityQueue,int height) {
		if(x-1<0 || explored[x-1][y])
			return;
		
		move(x-1, y,land, explored, group, priorityQueue, height);
	}
	
}

public class LandMovement {
	public static int solution(int[][] land, int height) {
        int answer = 0;
        
        boolean[][] explored = new boolean[land.length][land.length];
        Queue<Point> group = new LinkedList<Point>();
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((o1, o2) -> o1.diff-o2.diff);
        
        group.offer(new Point(0, 0, 0));
        explored[0][0] = true;
        Point point = null;
        while(!group.isEmpty() || !priorityQueue.isEmpty()) {
        	
        	point = group.poll();
        	
        	point.up(land, explored, group, priorityQueue, height);
        	point.down(land, explored, group, priorityQueue, height);
        	point.left(land, explored, group, priorityQueue, height);
        	point.right(land, explored, group, priorityQueue, height);
        	
        	if(group.isEmpty()) {
        		while(!priorityQueue.isEmpty()) {
	        		point = priorityQueue.poll();
	        		if(!explored[point.x][point.y]) {
	        			answer+= point.diff;
	        			group.offer(point);
	        			explored[point.x][point.y]=true;
	        			break;
	        		}
        		}
        		
        	}
        }
        
        return answer;
    }
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}}, 3));
		System.out.println(solution(new int[][] {{10, 11, 10, 11}, {2, 21, 20, 10}, {1, 20, 21, 11}, {2, 1, 2, 1}}, 1));
	}
}
