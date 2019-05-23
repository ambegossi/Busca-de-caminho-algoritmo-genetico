import java.util.Random;

/**
*
* @author Anderson M. Begossi e Guilherme Boschetti 
*/

public class Principal {

	public static void main(String[] args) {

		// String caminhoIdeal = "00;00;00;10;10;00;00;10;00;11;11;11;00;10;10;10;10;10;00;00;10;10;10;01;10;00;";
		
		// Inicializa o mapa
		Posicao[][] mapa = new Posicao[Parametros.tamMapa][Parametros.tamMapa];
		initMapa(mapa);

		// Gera uma populacao inicial de individuos
		Individuo[] populacao = new Individuo[Parametros.tamPopulacao];
		for (int i = 0; i < Parametros.tamPopulacao; i++) {
			populacao[i] = new Individuo();
		}
		
		Individuo melhorIndividuo = new Individuo();

		int geracao = 1; //contador de geracoes
		int melhoraptidao=0; //melhor aptidao da geracao
		
		// laco de repeticao gerador de populacoes
		while (geracao <= Parametros.numMaxGeracoes && melhoraptidao!=87) {

			// laco de repeticao avaliador dos individuos
			for (int i = 0; i < Parametros.tamPopulacao; i++) {

				// posicao inicial no mapa
				int linha = 9;
				int coluna = 0;

				// String que recebe as direcoes (bits) do caminho do individuo gerado aleatoriamente
				String[] direcoes = populacao[i].getCaminho().split(";");

				// TESTE COM CAMINHO IDEAL
				/*
				if (i == 0) { 
					populacao[i].setCaminho(caminhoAlternativo); 
					direcoes =populacao[i].getCaminho().split(";");
				} */
				 
				// laco que percorre a string do caminho de bits (direcoes)
				String direant="22";
				for (int j = 0; j < direcoes.length; j++) {
					if (direcoes[j].equals(Parametros.NORTE)) {
						if(direant.equals(Parametros.SUL)) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoVisitado);
						}
						if (mapa[linha][coluna].isNorte()) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoParede);
						} else {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoNorte);
						}
						
						linha = linha - 1;
						direant=direcoes[j];
						
						if (linha < 0) { // Se saiu fora do mapa
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoForaMapa);
							break; // para a verificacao do caminho
						}

					} else if (direcoes[j].equals(Parametros.SUL)) {
						if(direant.equals(Parametros.NORTE)) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoVisitado);
						}
						if (mapa[linha][coluna].isSul()) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoParede);
						} else {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoSul);
						}

						linha = linha + 1;
						direant=direcoes[j];

						if (linha > Parametros.tamMapa - 1) { // Se saiu fora do mapa
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoForaMapa);
							break; // para a verificacao do caminho
						}

					} else if (direcoes[j].equals(Parametros.LESTE)) {
						if(direant.equals(Parametros.OESTE)) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoVisitado);
						}
						if (mapa[linha][coluna].isLeste()) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoParede);
						} else {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoLeste);
						}

						coluna = coluna + 1;
						direant=direcoes[j];

						if (coluna > Parametros.tamMapa - 1) { // Se saiu fora do mapa
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoForaMapa);
							break; // para a verificacao do caminho
						}

					} else if (direcoes[j].equals(Parametros.OESTE)) {
						if(direant.equals(Parametros.LESTE)) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoVisitado);
						}
						if (mapa[linha][coluna].isOeste()) {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoParede);
						} else {
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoOeste);
						}

						coluna = coluna - 1;
						direant=direcoes[j];

						if (coluna < 0) { // Se saiu fora do mapa
							populacao[i].setAptidao(populacao[i].getAptidao() + Parametros.pontuacaoForaMapa);
							break; // para a verificacao do caminho
						}
					}

				} // for j
				
				// verifica a ultima posicao 
				if (linha >= 0 && linha <= 9 && coluna >= 0 && coluna <= 9) {
					// pune se nao chegou na saida, com pontuacao diferente conforme quadrante do
					// mapa
					if (linha == 0 && coluna == 9) {
						populacao[i].setAptidao(populacao[i].getAptidao() + 1);
					} else if (linha <= 4 && coluna <= 4) {
						populacao[i].setAptidao(populacao[i].getAptidao() + 15);
					} else if (linha >= 5 && coluna <= 4) {
						populacao[i].setAptidao(populacao[i].getAptidao() + 30);
					} else if (linha >= 5 && coluna >= 5) {
						populacao[i].setAptidao(populacao[i].getAptidao() + 20);
					} else if (linha <= 2 && coluna >= 7) {
						populacao[i].setAptidao(populacao[i].getAptidao() + 2);
					} else {
						populacao[i].setAptidao(populacao[i].getAptidao() + 9);
					}
				}

			} // for i

			// Ordenacao do vetor de populacao 
			Individuo aux = new Individuo();
			for (int i = 0; i < populacao.length; i++) {
				for (int j = 0; j < populacao.length - 1; j++) {
					if (populacao[j].getAptidao() > populacao[j + 1].getAptidao()) {
						aux = populacao[j];
						populacao[j] = populacao[j + 1];
						populacao[j + 1] = aux;
					}
				}
			}

			//print do melhor individuo de cada geracao
			System.out.printf("Geracao: %d | Melhor: %s\n", geracao, populacao[0].toString()); 
			melhorIndividuo.setCaminho(populacao[0].getCaminho());
			melhoraptidao=populacao[0].getAptidao();
			
			/*
			 * print de todos individuos por geracao 
			 * for (int i = 0; i < Parametros.tamPopulacao; i++) {
			 * System.out.printf("Geracao: %d  %s\n",geracao,populacao[i].toString()); 
			 * }
			 */

			// cria randomico
			Random random = new Random();

			Individuo[] descendentes = new Individuo[Parametros.tamPopulacao]; // Declaracao do vetor de
																				// descendentes
			int contind = 0; //contador de individuos da geracao de descendentes
			
			// insere novos individuos na nova geracao, ate atingir o tamanho maximo
			while (contind <= Parametros.tamPopulacao - 1) {

				// selecao de 2 pais por torneio
				Individuo[] populacaoTorneio = new Individuo[Parametros.nTorneio]; // declara vetor para fazer torneio

				// seleciona n individuos aleatoriamente na populacao
				for (int t = 0; t < Parametros.nTorneio; t++) {
					Random randomtorneio = new Random();
					populacaoTorneio[t] = populacao[randomtorneio.nextInt(Parametros.tamPopulacao - 1)];

				}

				// ordena o vetor da populacao do torneio
				Individuo aux2 = new Individuo();
				for (int i = 0; i < populacaoTorneio.length; i++) {
					for (int j = 0; j < populacaoTorneio.length - 1; j++) {
						if (populacaoTorneio[j].getAptidao() > populacaoTorneio[j + 1].getAptidao()) {
							aux2 = populacaoTorneio[j];
							populacaoTorneio[j] = populacaoTorneio[j + 1];
							populacaoTorneio[j + 1] = aux2;
						}
					}
				}
				
				// melhores individuos da populacao de torneio
				String pai1 = populacaoTorneio[0].getCaminho();
				String pai2 = populacaoTorneio[1].getCaminho();

				Random random2 = new Random();
				// verifica taxa de crossover
				if (random.nextDouble() <= Parametros.taxaDeCrossover) {
					int pcorte = 1 + random2.nextInt(77); // 52 bits + 25 ponto e virgula
					String parte1filho1 = pai1.substring(0, pcorte);
					String parte2filho1 = pai2.substring(pcorte, pai2.length());
					String parte1filho2 = pai2.substring(0, pcorte);
					String parte2filho2 = pai1.substring(pcorte, pai1.length());

					String descendente1 = parte1filho1 + parte2filho1;
					String descendente2 = parte1filho2 + parte2filho2;

					descendentes[contind] = new Individuo(descendente1);
					descendentes[contind + 1] = new Individuo(descendente2);

				} else {
					descendentes[contind] = new Individuo(pai1);
					descendentes[contind + 1] = new Individuo(pai2);
				}

				for (int m = 0; m < 2; m++) {
					
					Random random3 = new Random();
					// verifica taxa para fazer a mutacao
					if (random.nextDouble() <= Parametros.taxaDeMutacao) {
						int bitMutacao = random3.nextInt(77); // 52 bits + 25 ponto e virgula // bit que vao sofrer
																// mutacao. OBS: atencao ao ';'
						//individuo que vai sofrer a mutacao
						int individuoMutacao;
						if (m == 0) {
							individuoMutacao = contind; 
						} else {
							individuoMutacao = contind + 1;
						}

						// verificacao do bit sorteado e inversao do mesmo
						if (descendentes[individuoMutacao].getCaminho().charAt(bitMutacao) == '0') {
							char chars[] = descendentes[individuoMutacao].getCaminho().toCharArray();
							chars[bitMutacao] = '1';
							descendentes[individuoMutacao].setCaminho(new String(chars));
						} else if (descendentes[individuoMutacao].getCaminho().charAt(bitMutacao) == '1') {
							char chars[] = descendentes[individuoMutacao].getCaminho().toCharArray();
							chars[bitMutacao] = '0';
							descendentes[individuoMutacao].setCaminho(new String(chars));
						} else {
							if (descendentes[individuoMutacao].getCaminho().charAt(bitMutacao - 1) == '0') {
								char chars[] = descendentes[individuoMutacao].getCaminho().toCharArray();
								chars[bitMutacao - 1] = '1';
								descendentes[individuoMutacao].setCaminho(new String(chars));
							} else {
								char chars[] = descendentes[individuoMutacao].getCaminho().toCharArray();
								chars[bitMutacao - 1] = '0';
								descendentes[individuoMutacao].setCaminho(new String(chars));
							}
						}

					}
				}
				
				//incrementa a contagem de individuos da nova populacao em +2
				contind = contind + 2;
			}
			
			// aplicacao do elitismo
			for (int i = 0; i < Parametros.tamPopulacao * Parametros.taxaDeElitismo; i++) {
				populacao[i].setAptidao(0);
				descendentes[Parametros.tamPopulacao - (i + 1)] = populacao[i];
			}
			
			// nova populacao formada pelos descendentes e incremento da geracao 
			populacao = descendentes;
			geracao++;

		}
		
		printMapa(melhorIndividuo); //chama o metodo pintMapa mandando o melhor individuo
		
	} // main

	private static void printMapa(Individuo melhorIndividuo) {
		int [][]mapa = new int[10][10];  
	    
		String[] direcoes = melhorIndividuo.getCaminho().split(";");
		
		// posicao inicial no mapa
		int linha = 9;
		int coluna = 0;
		
		for (int j = 0; j < direcoes.length; j++) {
			if (direcoes[j].equals(Parametros.NORTE)) {
				mapa[linha][coluna]=1;
				linha = linha - 1;

			} else if (direcoes[j].equals(Parametros.SUL)) {
				mapa[linha][coluna]=1;
				linha = linha + 1;

			} else if (direcoes[j].equals(Parametros.LESTE)) {
				mapa[linha][coluna]=1;
				coluna = coluna + 1;


			} else if (direcoes[j].equals(Parametros.OESTE)) {
				mapa[linha][coluna]=1;
				coluna = coluna - 1;
			}
		} //for j
		
		mapa[linha][coluna]=1;
		
		View printamapa = new View(); //cria objeto printamapa
		printamapa.setMapa(mapa); //seta o mapa do objeto
		printamapa.geraMapa(); //chama o metodo que ira gerar a janela do mapa
		
	}
	
	private static void initMapa(Posicao[][] mapa) { //metodo de inicializacao do mapa com declaracao de paredes
                                                      //em cada direcao
		
		// Para cada posicao do mapa, atribui-se um objeto da classe Posicao
		mapa[0][0] = new Posicao(true, false, false, true);
		mapa[0][1] = new Posicao(true, true, false, false);
		mapa[0][2] = new Posicao(true, true, false, false);
		mapa[0][3] = new Posicao(true, true, false, false);
		mapa[0][4] = new Posicao(true, false, true, false);
		mapa[0][5] = new Posicao(true, false, false, true);
		mapa[0][6] = new Posicao(true, true, false, false);
		mapa[0][7] = new Posicao(true, true, false, false);
		mapa[0][8] = new Posicao(true, false, true, false);
		mapa[0][9] = new Posicao(true, false, false, true);
		mapa[1][0] = new Posicao(false, false, true, true);
		mapa[1][1] = new Posicao(true, true, false, true);
		mapa[1][2] = new Posicao(true, true, false, false);
		mapa[1][3] = new Posicao(true, true, false, false);
		mapa[1][4] = new Posicao(false, true, true, false);
		mapa[1][5] = new Posicao(false, false, true, true);
		mapa[1][6] = new Posicao(true, false, false, true);
		mapa[1][7] = new Posicao(true, false, true, false);
		mapa[1][8] = new Posicao(false, true, false, true);
		mapa[1][9] = new Posicao(false, true, true, false);
		mapa[2][0] = new Posicao(false, false, false, true);
		mapa[2][1] = new Posicao(true, true, false, false);
		mapa[2][2] = new Posicao(true, true, false, false);
		mapa[2][3] = new Posicao(true, true, false, false);
		mapa[2][4] = new Posicao(true, true, false, false);
		mapa[2][5] = new Posicao(false, false, true, false);
		mapa[2][6] = new Posicao(false, true, true, true);
		mapa[2][7] = new Posicao(false, false, false, true);
		mapa[2][8] = new Posicao(true, true, false, false);
		mapa[2][9] = new Posicao(true, false, true, false);
		mapa[3][0] = new Posicao(false, true, false, true);
		mapa[3][1] = new Posicao(true, true, false, false);
		mapa[3][2] = new Posicao(true, true, false, false);
		mapa[3][3] = new Posicao(true, false, true, false);
		mapa[3][4] = new Posicao(true, true, false, true);
		mapa[3][5] = new Posicao(false, true, false, false);
		mapa[3][6] = new Posicao(true, true, false, false);
		mapa[3][7] = new Posicao(false, true, true, false);
		mapa[3][8] = new Posicao(true, false, true, true);
		mapa[3][9] = new Posicao(false, false, true, true);
		mapa[4][0] = new Posicao(true, false, false, true);
		mapa[4][1] = new Posicao(true, true, true, false);
		mapa[4][2] = new Posicao(true, false, false, true);
		mapa[4][3] = new Posicao(false, false, true, false);
		mapa[4][4] = new Posicao(true, false, false, true);
		mapa[4][5] = new Posicao(true, true, false, false);
		mapa[4][6] = new Posicao(true, true, false, false);
		mapa[4][7] = new Posicao(true, true, false, false);
		mapa[4][8] = new Posicao(false, true, true, false);
		mapa[4][9] = new Posicao(false, true, false, true);
		mapa[5][0] = new Posicao(false, true, false, true);
		mapa[5][1] = new Posicao(true, true, false, false);
		mapa[5][2] = new Posicao(false, false, true, false);
		mapa[5][3] = new Posicao(false, false, false, true);
		mapa[5][4] = new Posicao(false, true, false, false);
		mapa[5][5] = new Posicao(true, true, false, false);
		mapa[5][6] = new Posicao(true, true, false, false);
		mapa[5][7] = new Posicao(true, true, false, false);
		mapa[5][8] = new Posicao(true, true, false, false);
		mapa[5][9] = new Posicao(true, false, true, false);
		mapa[6][0] = new Posicao(true, false, false, true);
		mapa[6][1] = new Posicao(true, true, false, false);
		mapa[6][2] = new Posicao(false, true, true, false);
		mapa[6][3] = new Posicao(false, false, true, true);
		mapa[6][4] = new Posicao(true, true, false, true);
		mapa[6][5] = new Posicao(true, false, false, false);
		mapa[6][6] = new Posicao(true, false, true, false);
		mapa[6][7] = new Posicao(true, false, false, true);
		mapa[6][8] = new Posicao(true, true, false, false);
		mapa[6][9] = new Posicao(false, true, true, false);
		mapa[7][0] = new Posicao(false, false, false, true);
		mapa[7][1] = new Posicao(true, false, false, false);
		mapa[7][2] = new Posicao(true, true, false, false);
		mapa[7][3] = new Posicao(false, true, false, false);
		mapa[7][4] = new Posicao(true, false, false, false);
		mapa[7][5] = new Posicao(false, false, true, false);
		mapa[7][6] = new Posicao(false, false, true, true);
		mapa[7][7] = new Posicao(false, true, false, true);
		mapa[7][8] = new Posicao(true, true, false, false);
		mapa[7][9] = new Posicao(true, false, true, false);
		mapa[8][0] = new Posicao(false, false, true, true);
		mapa[8][1] = new Posicao(false, false, true, true);
		mapa[8][2] = new Posicao(true, true, false, true);
		mapa[8][3] = new Posicao(true, true, true, false);
		mapa[8][4] = new Posicao(false, false, true, true);
		mapa[8][5] = new Posicao(false, false, true, true);
		mapa[8][6] = new Posicao(false, true, false, true);
		mapa[8][7] = new Posicao(true, true, false, false);
		mapa[8][8] = new Posicao(true, true, false, false);
		mapa[8][9] = new Posicao(false, false, true, false);
		mapa[9][0] = new Posicao(false, true, true, false);
		mapa[9][1] = new Posicao(false, true, false, true);
		mapa[9][2] = new Posicao(true, true, false, false);
		mapa[9][3] = new Posicao(true, true, false, false);
		mapa[9][4] = new Posicao(false, true, true, false);
		mapa[9][5] = new Posicao(false, true, false, true);
		mapa[9][6] = new Posicao(true, true, false, false);
		mapa[9][7] = new Posicao(true, true, false, false);
		mapa[9][8] = new Posicao(true, true, false, false);
		mapa[9][9] = new Posicao(false, true, true, false);
	}
}
