class P1feno{

    public static void main(String[] args) {
        int M, N, Soma = 0;

        M = MyIO.readInt();
        N = MyIO.readInt();

        String[] hp = new String[M];
        Integer[] value = new Integer[M];
        
        for(int i = 0; i < M; i++){
            hp[i] = MyIO.readString();
            value[i] = MyIO.readInt();
        }

        String tempString = "";
        for(int count = 0; count < N; count++){
            do {
                tempString = MyIO.readString();
                if (!tempString.equals(".")) {
                    for (int i = 0; i < M; i++) {
                        if (tempString.equals(hp[i])) {
                            Soma += value[i];
                        }
                    }
                }
            } while (!tempString.equals("."));            
            MyIO.println(Soma);
            Soma = 0;
        }
    }
}