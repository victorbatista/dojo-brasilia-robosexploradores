package br.dojo.robosExploradores;

public class Explorador {
	private int linha;
	private int coluna;
	private String mapa1;
	private String mapa2;
	private char[][] arrayMapa1;
	private char[][] arrayMapa2;
	private int yRobo;
	private int yFinal;
	private int xRobo;
	private int xFinal;

	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public String getMapa1() {
		return mapa1;
	}
	public void setMapa1(String mapa1) {
		this.mapa1 = mapa1;
	}
	public String getMapa2() {
		return mapa2;
	}
	public void setMapa2(String mapa2) {
		this.mapa2 = mapa2;
	}

	public void definirMapas(String mapa1, String mapa2) {
		this.mapa1 = mapa1;
		this.mapa2 = mapa2;

		this.arrayMapa1 = stringToArray(mapa1);
		this.arrayMapa2 = stringToArray(mapa2);

	}
	/*
	public int subir(int x, int y){		
		return 2;
	}
	public int dir(int x, int y){		
		return 1;
	}*/
	
	public char[][] andar(char[][] mapa, int[] robo) {
		
		if (podeSubir(mapa, robo)){
			
			subir(mapa, robo);
			
		}else{
			if (podeAndarEsquerda(mapa, robo) ){
				
				andarEsquerda(mapa, robo);
			}else if (podeAndarDireita(mapa, robo)){
				
				andarDireita(mapa, robo);
			}
		}
		return mapa;
	}
	private void andarDireita(char[][] mapa, int[] robo) {
		mapa[robo[0]][robo[1]] = '*';
		mapa[robo[0]][robo[1]+1] = 'R';
	}
	private void andarEsquerda(char[][] mapa, int[] robo) {
		mapa[robo[0]][robo[1]] = '*';
		mapa[robo[0]][robo[1]-1] = 'R';
	}
	private void subir(char[][] mapa, int[] robo) {
		mapa[robo[0]][robo[1]] = '*';
		mapa[robo[0]-1][robo[1]] = 'R';
	}
	private boolean podeAndarDireita(char[][] mapa, int[] robo) {
		return (robo[1]+1) < mapa[0].length;
	}
	private boolean podeAndarEsquerda(char[][] mapa, int[] robo) {
		return (robo[1]-1) >= 0 && mapa[robo[0]][robo[1]-1] == '.';
	}
	private boolean podeSubir(char[][] mapa, int[] robo) {
		return (robo[0] - 1) >= 0 && mapa[robo[0]-1][robo[1]] == '.';
	}
	
	public int explorarMapas() {
		
		int distancia = calculaDistancia();
		
		if (mapa1.contains("#")){			
			if(distancia == 1){
				return 1;
			}
			
			int[] robo = new int[2];
			int qtdLinhas = arrayMapa1.length;			
			int qtdColunas = arrayMapa1[0].length;
			
			for(int y=0; y < qtdColunas; y++ ){
				for(int x=0; x < qtdLinhas; x++ ){
					if(arrayMapa1[x][y] == 'R'){
						robo[1] = y;
						robo[0] = x;
					}					
				}
			}
			
			arrayMapa1 = andar(arrayMapa1, robo);
			
			return explorarMapas()+1;
			
		}
		return distancia;
	}
	
	private int calculaDistancia() {
		localizarRoboEFinal();
		return Math.abs(yFinal - yRobo)+Math.abs(xFinal-xRobo);
	}
	private void localizarRoboEFinal() {
		int qtdLinhas = arrayMapa1.length;			
		int qtdColunas = arrayMapa1[0].length;
		
		for(int y=0; y < qtdColunas; y++ ){
			for(int x=0; x < qtdLinhas; x++ ){
				if(arrayMapa1[x][y] == 'R'){
					yRobo = y;
					xRobo = x;
				}
				if(arrayMapa1[x][y] == 'F'){
					yFinal = y;
					xFinal = x;
				}
			}
		}
	}

	public static char[][] stringToArray(String mapa) {
		mapa = mapa.replaceAll(" ", "");
		String[] linhas = mapa.split("\n");

		char [][] retorno = new char[linhas.length][linhas[0].length()];


		for (int x=0; x<linhas.length; x++){
			for (int y = 0; y < linhas[0].length(); y++){
				retorno[x][y] = linhas[x].charAt(y);
			}									
		} 
		return retorno;
	}
}








