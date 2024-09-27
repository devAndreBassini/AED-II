public class Comparacoes {

	public static void printArray(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
	}

	public static void main(String[] args) {
		// int[] array = {30, 37, 63, 101, 115, 20};
		int[] array = {67, 37, 30, 20, 115, 101};
		int n = array.length;

		printArray(array);
		System.out.println();
		for(int i = 1; i < n; i++){
			int tmp = array[i];
			int j = i -1;

			while( (j >= 0) && (array[j] > tmp) ){
				printArray(array);
				System.out.println(" --- " + array[j] + " > " + tmp + " ?");	
				array[j + 1] = array[j];
				j--;
				
			}
			array[j + 1] = tmp;
			
		}
		printArray(array);
		System.out.println("");
	}
	
}