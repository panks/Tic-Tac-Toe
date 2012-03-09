import java.util.Stack;


public class BoardData {
	int size;
	int sum;
	int[][] boardData;
	int aiR, aiC;
	
	public BoardData(int size){
		this.size=size;
		boardData=new int[size][size];
	}
	
	// update board date after every next move
	public void updateBoardData(int player, int row, int col){
		if(player==-1)
			boardData[row][col]=-1;
		else
			boardData[row][col]=1;
		
		printBoard(boardData);
	}
	
	
	// check if someone won
	public int checkStatus(int movesMade){
		// Row check		
		for(int i=0; i<size; i++){
			sum=0;
			for(int j=0; j<size; j++){
				sum+=boardData[i][j];
			}
			if((sum)==size||-sum==size){
				return 1;
			}
		}
		
		// col check
		for(int i=0; i<size; i++){
			sum=0;
			for(int j=0; j<size; j++){
				sum+=boardData[j][i];
			}
			if((sum)==size||-sum==size){
				return 1;
			}
		}
		
		// digonal check
		sum=0;
		for(int j=0; j<size; j++){
			sum+=boardData[j][j];
		}
		if((sum)==size||-sum==size){
			return 1;
		}
		
		sum=0;
		for(int j=0; j<size; j++){
			sum+=boardData[size-j-1][j];
		}
		if((sum)==size||-sum==size){
			return 1;
		}
		
		if(movesMade==size*size){
			return 0;
		}	

		return -1;
	}
	
	
	// Reset the entire board data
	public void reset(){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				boardData[i][j]=0;
			}
		}
	}
	
	// Print board (not used)
	public void printBoard(int [][] array){
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				System.out.print(array[i][j]+" ");
			}
			System.out.println("\n");
		}
	}
	
	
	// get the next computer move
	public void nextAIMove(int movesMade){
		Stack<Integer> row=new Stack<Integer>();
		Stack<Integer> col=new Stack<Integer>();
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				if(boardData[i][j]==0){
					row.push(i);
					col.push(j);
				}
			}
		}
		int randomNum = (int)(Math.random()*row.size());
		aiR=row.elementAt(randomNum);
		aiC=col.elementAt(randomNum);
	}

}
