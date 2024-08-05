public class Fila {
    int[] array;
    int primeiro, ultimo;

    Fila(){
        this(5);
    }

    Fila(int tamanho){
        array= new int[tamanho+1];
        primeiro = ultimo = 0;
    }

    void inserir(int x) throws Exception{
        if(((ultimo + 1) % array.length) == primeiro)
            throw new Exception("Erro!");

        array[ultimo] = x;
        ultimo = (ultimo +1) % array.length;
    }

    int remover() throws Exception{
        if(primeiro == ultimo)
            throw new Exception("Erro!");
        
        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length;
        return resp;
    }

    void mostrar(){
        int i = primeiro;
        System.out.print("[");

        while(i != ultimo){
            System.out.print(array[i] + "");
            i = (i + 1) % array.length;
        }

        System.out.println("]");
    }

}
