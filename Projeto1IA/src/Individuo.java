import java.util.Random;

public class Individuo {

    private String caminho = "";
    private int aptidao = 0;
    
    //gera um individuo com um caminho definido
    public Individuo(String caminho) {
        this.caminho = caminho;      
    }

    //gera um individuo com caminho aleatorio
    public Individuo() {
        Random r = new Random();
        
        for (int i = 0; i < Parametros.tamBitsCaminho; i++) {
        	caminho += r.nextInt(2);
        	if (i%2 != 0) {
        		caminho += ";";
        	}
        }       
    }

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public int getAptidao() {
		return aptidao;
	}

	public void setAptidao(int aptidao) {
		this.aptidao = aptidao;
	}
	
	public String toString() {
		return String.valueOf(aptidao) + "   |   " + caminho;
	}

}