import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class juego extends JFrame implements Runnable, KeyListener {
public JLabel fondo, auto, gameover, autorojo, Score;
public int   x,y,x2,y2, yf, yt, con, activador,xl, sco;
public Thread hilo = new Thread(this);

    juego(){
        setBounds(0,0,615,635);
        setVisible(true);
        this.ventana();
        setLayout(null);
        setTitle("Prueba");
        addKeyListener(this);
        hilo.start();

    }

    private void ventana() {

        //Caracteristicas del contenedor ventanaa
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container ventanaa = getContentPane();
        ventanaa.setBounds(0,0,600,600);
        ventanaa.setLayout(null);

        //Jlabel de puntuación del juego
        Score = new JLabel("" + sco);
        Score.setBounds(20,20,50,50);
        Color blanco = new Color(255, 255, 255);
        Score.setForeground(blanco);
        ventanaa.add(Score);

        //Icono de game over
        Icon over = new ImageIcon(new ImageIcon("pantallaover2.PNG").getImage().getScaledInstance
                ( 400, 320, Image.SCALE_DEFAULT));
        //Jlabel para mostrar el icono de gameover
        gameover = new JLabel(over);
        gameover.setBounds(100,100,400,320);
        gameover.setVisible(false);
        ventanaa.add(gameover);

        //Instancia de icono de jugador
        Icon auto1 = new ImageIcon(new ImageIcon("auto.PNG").getImage().getScaledInstance
                ( 120, 120, Image.SCALE_DEFAULT));

        //instancia de jlabel del auto del jugador
        auto = new JLabel(auto1);
        auto.setBounds(x,y,100,50);
        auto.setVisible(true);
        ventanaa.add(auto);

        //Instancia del icono de auto enemigo
        Icon auto2 = new ImageIcon(new ImageIcon("autor.PNG").getImage().getScaledInstance
                ( 50, 100, Image.SCALE_DEFAULT));
        //Instancia enemigo
        autorojo = new JLabel(auto2);
        autorojo.setBounds(x2,y2,50,100);
        autorojo.setVisible(true);
        ventanaa.add(autorojo);

        //Instancia de icono fondo
        Icon fonfoe = new ImageIcon(new ImageIcon("fondo2.PNG").getImage().getScaledInstance
                ( 600, 1800, Image.SCALE_DEFAULT));

        //Instancia de Jlabel para mostrar el icono fonfoe
        fondo = new JLabel(fonfoe);
        fondo.setVisible(true);
        ventanaa.add(fondo);


        //metodo para repintar en pantalla
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        if(e.getKeyCode() == KeyEvent.VK_UP) {

                y = y -40;
            }

            if(e.getKeyCode() == KeyEvent.VK_LEFT) {

                x = x -40;
            }

            if(e.getKeyCode() == KeyEvent.VK_DOWN) {

                y = y +40;
            }

            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

                x = x +30;
        }

        if(e.getKeyCode() == KeyEvent.VK_R) {
            gameover.setVisible(false);
            activador =1;
        }

        auto.setBounds(x,y,50,100);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
       int pri = 1;

        while(true) {


            /*hace  que se se sumen los puntos del jugador solo si el juego ha iniciado
            Se detendra si el jugador pierde o gana*/
            if (activador == 2){
                 sco = sco + 1;
            }

           /*Cambia el color de fuente del Jlabel score cuando sco llega a 500 (que es la variable
            que guarda la puntuación)*/
            if (sco > 500){
                Color amarillo = new Color(250, 241, 86);
                Score .setForeground(amarillo);
            }

             /*Cambia el color de fuente del Jlabel score cuando sco llega a 700 (que es la variable
            que guarda la puntuación)*/
            if (sco > 700){
                Color amarillo = new Color(255, 187, 46);
                Score.setForeground(amarillo);
            }

             /*Cambia el color de fuente del Jlabel score cuando sco llega a 1200 (que es la variable
            que guarda la puntuación)*/
            if (sco > 1200){
                Color amarillo = new Color(255, 41, 50);
                Score.setForeground(amarillo);
            }

            //cuando el juego se inicia se le asignan valores a las variables
            if(pri == 1 || activador == 1) {
                //se restablecen las variables para funcionar
                y = 240;          //variable de posición y del jugador
                x = 360;          //variable de posición x del jugador
                yt = 20;          //variable que hace que el auto se deslize para abajo
                yf = -1200;       // altura y del fondo
                x2 = 300;         //variable de posición x del enemigo
                y2 = 100;         //variable de posición y del enemigo
                pri = 2;          //variable para que se detenga este if
                con = 1;          //variable que define la animación de perdedor
                activador =2;     //variable que da inicio al juego
                xl = 10;          //variable de deslizamiento para la derecha
                sco = 1400;        //variable de score que se reinicia
                //se instancia un icono con la imagen del jugador
                Icon auto1 = new ImageIcon(new ImageIcon("auto.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                //se asigna el icono auto1 a Jlabel auto
                auto.setIcon(auto1);

                //se instancia un icono con la imagen del enemigo
                Icon auto2 = new ImageIcon(new ImageIcon("autor.PNG").getImage().getScaledInstance
                        ( 50, 100, Image.SCALE_DEFAULT));
                //se asigna el icono auto2 a Jlabel autorojo
                autorojo.setIcon(auto2);
            }

            //Se hace el movimiento hacia izquierda o derecha
            x2 = x2 - xl;

            //mueve el auto rojo hacia la izquierda
            if(x2 < 121){
                xl = -20;
            }
            //mueve el auto rojo hacia la derecha
            if(x2 >  420){
                xl =  +20;
            }

            //movimiento hacia abajo de auto
            if(sco < 999){
            y = y  + yt - 18;
              }
            if(sco > 999){
                y = y + yt - 29;
            }

            //movimento de auto rojo hacia abajo
            y2 = y2 + yt;
            if (sco > 1000 && activador == 2){
                yt = 30;
            }
            //movimiento de fondo
            yf = yf +yt;
            //reinicio de movimiento de fondo
            if(yf > 1  ) {
                yf = -1200;
            }
            //Cuando el auto enemigo desaparece en pantalla se reinicia el icono y la posición
            if(y2 > 500  ) {
                y2 = -40;
                Icon auto1 = new ImageIcon(new ImageIcon("autor.PNG").getImage().getScaledInstance
                        ( 50, 100, Image.SCALE_DEFAULT));
                autorojo.setIcon(auto1);
            }
            //movimiento hacia la izquierda si estamos en ultima casilla
            if( y > 430 ){
                x = x + 5;
            }


            //pone las posiciones de los Jlabel
            Score.setBounds(20,20,100,50);
            Score.setText("SCORE:" + sco);
            auto.setBounds(x,y,50,100);
            autorojo.setBounds(x2,y2,50,100);
            fondo.setBounds(0,yf,600,1800);

            //game over cuando se tocan los bordes de la pantalla
            if( y > 600 || x > -1 && x < 70 ||x > 450 || y < -120){
                yt = 0;
                xl = 0;
                activador = 3;
            }

            //hace que cuando el auto choque con el auto rojo pierdas
            if(x > x2 -1 && x < x2 + 50 && y > y2-1 && y < y2 +100 || x +50 > x2 -1 && x + 50 < x2 + 50 && y + 100 > y2-1 && y + 100< y2 +100){
                Icon auto1 = new ImageIcon(new ImageIcon("exp4.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                autorojo.setIcon(auto1);

                //cuando sco es mayor a 1199 la puntuación subira 100 mas
                if (sco > 1199){
                    sco = sco + 100  ;
                }

                //si la puntuación es menor a 1199 y tocas al enemigo perderas
               if (sco < 1199){
                yt = 0;
                xl = 0;
                activador = 3;
               }
            }

            //cuando activador sea 3 se activara la animación de perdida
            if (activador == 3){
                con = con + 1;
            }

            //se inicia la anmiación de choque cuando el jugador pierde
            if(con ==1){
                Icon auto1 = new ImageIcon(new ImageIcon("auto2.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                auto.setIcon(auto1);
            }

            //se cambia el icono para la animacíón cuando se pierde
            if(con == 4){
                Icon auto1 = new ImageIcon(new ImageIcon("exp1.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                auto.setIcon(auto1);
            }
            //se cambia el icono del auto para la animacíón cuando se pierde
            if(con == 9){
                Icon auto1 = new ImageIcon(new ImageIcon("exp2.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                auto.setIcon(auto1);
            }

            //parte final de la animación  cuando se pierde
            if(con == 12){
                Icon auto1 = new ImageIcon(new ImageIcon("exp4.PNG").getImage().getScaledInstance
                        ( 120, 120, Image.SCALE_DEFAULT));
                auto.setIcon(auto1);
                Icon gam = new ImageIcon(new ImageIcon("pantallaover2.PNG").getImage().getScaledInstance
                        ( 400, 320, Image.SCALE_DEFAULT));
                gameover.setBounds(100,100,400,320);
                gameover.setIcon(gam);
                gameover.setVisible(true);

            }

            //Condicional que determina cuando ganamos
            if(sco > 1500) {
                Icon gam = new ImageIcon(new ImageIcon("pantallawin.PNG").getImage().getScaledInstance
                        ( 200, 200, Image.SCALE_DEFAULT));
                gameover.setIcon(gam);
                gameover.setBounds(200,10,200,100);
                gameover.setVisible(true);
                xl = 0;
                yt = 0;
                        }
            try {
                //tiempo de respuesta
                Thread.sleep(50);

            } catch (InterruptedException ignored) {
            }
        }

    }
}
