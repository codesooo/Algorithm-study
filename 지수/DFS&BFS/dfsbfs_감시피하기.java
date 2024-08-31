import java.util.*;
import java.io.*;

public class dfsbfs_감시피하기 {
    
    static int N;
    static char map[][];
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, 1, -1};
    static ArrayList<Node> listT = new ArrayList<>(); 
    static ArrayList<Node> listS = new ArrayList<>(); 
    
    public static class Node{
        int x;
        int y;
        
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());        
        map = new char[N][N];
        
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j] == 'S'){
                	listS.add(new Node(i, j));
                } else if(map[i][j] == 'T') {
                    listT.add(new Node(i, j));
                }
            }
        }
        
        dfs(0, 0, 0);
        System.out.println("NO");
    }
    
    public static void dfs(int x, int y, int count){
        if(count == 3){
            bfs();
            return;
        }
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 'X'){
                    map[i][j] = 'O';
                    dfs(i, j, count + 1);
                    map[i][j] = 'X';
                }
            }
        }
    }
    
    public static void bfs(){
        boolean check[][] = new boolean[N][N]; 
        char copyMap[][] = new char[N][N];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copyMap[i][j] = map[i][j];
            }
        }
        
        Queue<Node> q = new LinkedList<>();
        for(int i = 0; i < listT.size(); i++){
            q.add(listT.get(i));
        }
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int x = node.x;
            int y = node.y;
            
            check[x][y] = true;
            
            for(int mx = 0; mx < 4; mx++){
                int goX = x + moveX[mx];
                int goY = y + moveY[mx];
                
                while(goX >= 0 && goY >= 0 && goX < N && goY < N){
                    if(copyMap[goX][goY] != 'O'){
                        check[goX][goY] = true; 
                        goX += moveX[mx];
                        goY += moveY[mx];
                    } else{
                        break;
                    }
                }
            }
        }
        
        if(checkStudent(check)) {
        	System.out.println("YES");
        	System.exit(0);
        }
    }
    
    public static boolean checkStudent(boolean visit[][]) {
    	
    	for(Node node : listS) {
    		if(visit[node.x][node.y]) {
    			return false;
    		}
    	}
    	return true;
    }
}
