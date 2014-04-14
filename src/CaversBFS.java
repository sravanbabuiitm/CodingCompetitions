import java.io.BufferedReader;
import java.io.FileReader;

// PRUNING IS PERFORMED
public class CaversBFS {
	public static void main(String args[]) throws Exception{
		char color[][] = getAdjacenyMatrix("/Users/sravanb/Documents/workspace/CodingCompetitions/src/input2.txt");
		int noChambers = color.length-1;
		
		for(int current=2;current<=noChambers;current++){
			// We Explore each of the path that start from 1st chamber for ONLY 1 solution.
			if(color[1][current]=='G')
			getMaxCavers_BFS(noChambers,color,current);
		}
		
		System.out.println(maxCavers);
	}
	static int maxCavers = 0;
	/* We can adapt the feature of Graph Pruning in this case
	 since we can get atmost only 1 caver from every path that starts from root node. */
	private static boolean getMaxCavers_BFS(int noChambers, char[][] color,int current) {
	
		for(int i=current+1;i<=noChambers;i++){
			if(color[current][i]=='G'){
				if(getMaxCavers_BFS(noChambers, color, i))
					return true;
				/* If a solution is found in any of the nodes we return without further processing that route since thats the maximum that can be found in that route. */
			}
			else if(color[current][i]=='B'){
				maxCavers = maxCavers + 1;
				color[current][i]='R';
				return true;
			}
		}
		return false;
	}

	private static char[][] getAdjacenyMatrix(String fileName) throws Exception {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		int noChambers = Integer.parseInt(br.readLine());
		char color[][]=new char[noChambers+1][noChambers+1];
		
		for(int i=1;i<noChambers;i++){
			for(int j=1;i<noChambers;i++){
				color[i][j]='R';
			}
		}

		for(int i=1;i<noChambers;i++){
			String tokens[] = br.readLine().split(" ");
			int connections = Integer.parseInt(tokens[0]);
			for(int j=1;j<=connections;j++){
				int toChamber = Integer.parseInt(tokens[j]);
				if(toChamber==noChambers)
					color[i][toChamber] = 'B';
				else
					color[i][toChamber] =  'G';
			}
		}
		return color;
	}
}
