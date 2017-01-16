import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Riley Hanson
 * CMSC 412: Social Networks
 * Assignment 1 - Problem 5
 * Undirected Weighted Graph
 * 
 * Calculates and outputs the unnormalized weight of nodes
 *
 */
public class A2 {

	private int m;
	private int n;
	private boolean[][] graph;
	private File f;

	public A2() {
		f = new File("closeness.txt");
	}

	public void setN(int x) {
		this.n = x;
		graph = new boolean[n][n];
		
		for(int i = 0; i < this.n; i++) {
			for(int j = 0; j < this.n; j++) {
				graph[i][j] = false;
				graph[j][i] = false;
			}
		}
	}

	public void setM(int y) {
		this.m = y;
	}

	public void setUV(int u, int v) {
		graph[u-1][v-1] = true;
		graph[v-1][u-1] = true;
	}
	
	public void setK() {
		try {
			PrintWriter pw = new PrintWriter(f);
			double k = 0.0;
			
			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					if(graph[i][j] != false) {
						k ++;					
					} 
				}
				k = compute(k);
				pw.println(k);
				k = 0;
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int getN() {
		return this.n;
	}

	public int getM() {
		return this.m;
	}
	
	public File getK() {
		return this.f;
	}

	public double compute(double sum) {
		if (sum > 1) {
			return sum / (this.n - 1);
		} else {
			return Math.abs(sum / this.n -1);
		}
		
	}
	
//	public String toString() {
//		int x;
//		String s = "";
//		String str = "";
//		for(int i = 0; i < this.n; i++) {
//			for(int j = 0; j < this.n; j++) {
//				x = graph[i][j];
//				s = Integer.toString(x);
//				str += s + " ";
//			} str = "";
//		}
//		return str;
//	}
	
	/**
	 * @param args
	 * run program with sample graph.txt file as args
	 */
	public static void main(String[] args) {
		String f = null;
		String line = null;
		String regex = " ";
		int u = 0;
		int v = 0;
		File output;

		if(args[0] != null) {
			f = args[0];
			BufferedReader br;

			try {
				br = new BufferedReader( new FileReader (f));
				A2 g = new A2();

				line = br.readLine();
				String str[] = line.split(regex);
				g.setN(Integer.parseInt(str[0]));
				g.setM(Integer.parseInt(str[1]));
				
				for(int i = 0; i < g.getM(); i++) {
					line = br.readLine();
					str = line.split(regex);
					u = Integer.parseInt(str[0]);
					v = Integer.parseInt(str[1]);
					g.setUV(u,v);
				}
				
				g.setK();
				output = g.getK();
				br.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

