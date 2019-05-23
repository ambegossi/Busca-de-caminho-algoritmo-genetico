
public class Posicao {  //Classe Posicao, contendo as direcoes e paredes

    private boolean norte;
    private boolean sul;
    private boolean leste;
    private boolean oeste;
    private int visitado;
    
    //gera uma posicao
    public Posicao() {
        
    }
    
    //gera uma posicao com definicao de paredes
    public Posicao(boolean norte, boolean sul, boolean leste, boolean oeste) {  
        this.norte = norte;
        this.sul = sul;
        this.leste = leste;
        this.oeste = oeste;
        this.visitado=0;
    }
    
	public int getVisitado() {
		return visitado;
	}
	public void setVisitado(int visitado) {
		this.visitado = visitado;
	}
	public boolean isNorte() {
		return norte;
	}
	public void setNorte(boolean norte) {
		this.norte = norte;
	}
	public boolean isSul() {
		return sul;
	}
	public void setSul(boolean sul) {
		this.sul = sul;
	}
	public boolean isLeste() {
		return leste;
	}
	public void setLeste(boolean leste) {
		this.leste = leste;
	}
	public boolean isOeste() {
		return oeste;
	}
	public void setOeste(boolean oeste) {
		this.oeste = oeste;
	}

}