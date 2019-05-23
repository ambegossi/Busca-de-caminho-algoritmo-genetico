
public class Parametros { //Classe de Parametros usados no algoritmo
	
	public static String NORTE = "00";
	public static String SUL = "01";
	public static String LESTE = "10";
	public static String OESTE = "11";
	
	public static int tamBitsCaminho = 52;
	public static int tamMapa = 10;
	public static int tamPopulacao = 8000; //8000
	public static int numMaxGeracoes = 500; //500
	
	public static double taxaDeCrossover = 0.9; // 90%
	public static double taxaDeMutacao = 0.9; // 90%
	public static double taxaDeElitismo = 0.5;// 50%
	public static int nTorneio = 2;//2
	
	public static int pontuacaoNorte = 1; //1
	public static int pontuacaoSul = 13; // 13
	public static int pontuacaoLeste = 3; //3
	public static int pontuacaoOeste = 9; // 9
	public static int pontuacaoParede = 20; // 20
	public static int pontuacaoForaMapa = 1000;
	public static int pontuacaoVisitado = 20;
}
