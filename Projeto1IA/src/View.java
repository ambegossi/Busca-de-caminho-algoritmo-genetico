import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class View extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int [][]mapa;  //matriz que vai ser desenhada 
	
	public void setMapa(int[][]mapa) { //construtor com matriz do mapa 
		this.mapa=mapa;
	}
    
    public View() {   //cria a janela 
        setTitle("Empilhadeira Autoguiada");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) { //pinta o mapa 
        super.paint(g);
        
        g.translate(50, 50);
        
        // desenha o mapa
        for (int row = 0; row < Parametros.tamMapa; row++) {
            for (int col = 0; col < Parametros.tamMapa; col++) {
                Color color;
                switch (mapa[row][col]) {
                    case 1 : color = Color.RED; break;  // pinta o caminho de vermelho
                    case 0 : color = Color.BLACK; break; // pinta quadrados do mapa
                    default : color = Color.BLUE;
                }
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.drawRect(30 * col, 30 * row, 30, 30);  // quadrados com tamanho 30
            }
        }
        
        // desenha paredes
        g.setColor(Color.BLUE);        
        g.drawLine(30, 300, 30, 240);
        g.drawLine(60, 270, 120, 270);
        g.drawLine(60, 270, 60, 240);
        g.drawLine(120, 270, 120, 240);
        g.drawLine(60, 240, 120, 240);
        g.drawLine(150, 300, 150, 240);
        g.drawLine(180, 270, 180, 210);
        g.drawLine(180, 270, 270, 270);
        g.drawLine(210, 240, 270, 240);
        g.drawLine(210, 240, 210, 180);
        g.drawLine(300, 210, 240, 210);
        g.drawLine(150, 210, 120, 210);
        g.drawLine(90, 210, 30, 210);
        g.drawLine(90, 210, 90, 150);
        g.drawLine(120, 210, 120, 180);
        g.drawLine(120, 180, 270, 180);
        g.drawLine(0, 180, 60, 180);
        g.drawLine(30, 150, 60, 150);
        g.drawLine(60, 150, 60, 120);
        g.drawLine(120, 150, 120, 90);
        g.drawLine(150, 150, 300, 150);
        g.drawLine(0, 120, 90, 120);
        g.drawLine(120, 120, 240, 120);
        g.drawLine(240, 120, 240, 90);
        g.drawLine(240, 90, 270, 90);
        g.drawLine(270, 90, 270, 150);
        g.drawLine(30, 90, 150, 90);
        g.drawLine(180, 90, 210, 90);
        g.drawLine(210, 90, 210, 60);
        g.drawLine(180, 90, 180, 30);
        g.drawLine(240, 60, 300, 60);
        g.drawLine(30, 60, 150, 60);
        g.drawLine(30, 30, 30, 60);
        g.drawLine(30, 30, 120, 30);
        g.drawLine(150, 60, 150, 0);
        g.drawLine(180, 30, 240, 30);
        g.drawLine(240, 30, 240, 60);
        g.drawLine(240, 60, 300, 60);
        g.drawLine(270, 0, 270, 30);
        g.drawLine(0, 0, 0, 300);
        g.drawLine(0, 0, 300, 0);
        g.drawLine(300, 0, 300, 300);
        g.drawLine(0, 300, 300, 300);

    }
   
    public void geraMapa() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                view.setMapa(mapa);
                view.setVisible(true);
            }
        });
    }
    
}