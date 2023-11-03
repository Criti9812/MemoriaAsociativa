package Memoria;

import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.Collections;


public class mem extends JFrame implements ActionListener{
  private JPanel panel, panel1, panel2, panel3, panel4, panelUsuario, panelMin, panelMax;
  private JButton b1, limpiar;
  private JLabel label1, label2, label3, label4;
  private Font fuente = new Font("Trebuchet MS", Font.BOLD, 24); 
  private int tam=20;
  private Celda[][] matriz1, matriz2, matriz3, matriz4, matrizU, matrizMax, matrizMin;
  private ArrayList<Integer> mat1 = new ArrayList<Integer>(tam);
  private ArrayList<Integer> mat2 = new ArrayList<Integer>(tam);
  private ArrayList<Integer> mat3 = new ArrayList<Integer>(tam);
  private ArrayList<Integer> mat4 = new ArrayList<Integer>(tam);
  private ArrayList<Integer> matUser = new ArrayList<Integer>(tam);
  private ArrayList<Integer> sMatriz1 = new ArrayList<Integer>();
  private ArrayList<Integer> sMatriz2 = new ArrayList<Integer>();
  private ArrayList<Integer> sMatriz3 = new ArrayList<Integer>();
  private ArrayList<Integer> sMatriz4 = new ArrayList<Integer>();
  private ArrayList<Integer> M = new ArrayList<Integer>();
  private ArrayList<Integer> W = new ArrayList<Integer>();
  private ArrayList<Integer> recuperacionM = new ArrayList<Integer>();
  private ArrayList<Integer> recuperacionW = new ArrayList<Integer>();
  private ArrayList<Integer> betaMax = new ArrayList<Integer>();
  private ArrayList<Integer> betaMaxima = new ArrayList<Integer>();
  private ArrayList<Integer> betaMin = new ArrayList<Integer>();
  private ArrayList<Integer> betaMinima = new ArrayList<Integer>();

  

  public void extra(){
    setSize(1200, 700);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(3);
    setResizable(false);
    setTitle("Memoria Asociativa");
    comp();
    setVisible(true);
    }

  public void comp(){
    panel();
    matrices();
    texto();
    boton();
  }

  public void panel(){
    panel=new JPanel();
    panel.setLayout(null);
    this.getContentPane().add(panel);
  }

  public void texto(){
    label1=new JLabel("-- MEMORIA ASOCIATIVA --");
    label1.setBounds(435,20,400,40);
    label1.setFont(fuente);
    panel.add(label1);
   
    label2=new JLabel("- NUEVO PATRON -");
    label2.setBounds(80,270,400,40);
    label2.setFont(fuente);
    panel.add(label2);  
    
    label3=new JLabel("- MAXIMOS -");
    label3.setBounds(410,270,400,40);
    label3.setFont(fuente);
    panel.add(label3); 
    
    label4=new JLabel("- MINIMOS -");
    label4.setBounds(700,270,400,40);
    label4.setFont(fuente);
    panel.add(label4);
  }

  public void boton(){
    b1=new JButton("Listo ");
    b1.setBounds(980,90,150,150);
    b1.setFont(fuente);
    b1.addActionListener(this);
    panel.add(b1);

    limpiar=new JButton("Limpiar");
    limpiar.setBounds(450,550,200,50);
    limpiar.setFont(fuente);
    limpiar.addActionListener(this);
    //panel.add(limpiar);
  }

  public void matrices(){
    panel1=new JPanel();
    panel1.setPreferredSize(new Dimension(120, 90));
    panel1.setLayout(new GridLayout(5,4));
    panel1.setBounds(50, 90, 200, 150);
    panel.add(panel1);

    panel2=new JPanel();
    panel2.setPreferredSize(new Dimension(120, 90));
    panel2.setLayout(new GridLayout(5,4));
    panel2.setBounds(280, 90, 200, 150);
    panel.add(panel2);

    panel3=new JPanel();
    panel3.setPreferredSize(new Dimension(120, 90));
    panel3.setLayout(new GridLayout(5,4));
    panel3.setBounds(510, 90, 200, 150);
    panel.add(panel3);

    panel4=new JPanel();
    panel4.setPreferredSize(new Dimension(120, 90));
    panel4.setLayout(new GridLayout(5,4));
    panel4.setBounds(740, 90, 200, 150);
    panel.add(panel4);

    panelUsuario=new JPanel();
    panelUsuario.setPreferredSize(new Dimension(120, 90));
    panelUsuario.setLayout(new GridLayout(5,4));
    panelUsuario.setBounds(80, 330, 200, 150);
    panel.add(panelUsuario);

    panelMax=new JPanel();
    panelMax.setPreferredSize(new Dimension(120, 90));
    panelMax.setLayout(new GridLayout(5,4));
    panelMax.setBounds(380, 330, 200, 150);
    panel.add(panelMax);

    panelMin=new JPanel();
    panelMin.setPreferredSize(new Dimension(120, 90));
    panelMin.setLayout(new GridLayout(5,4));
    panelMin.setBounds(670, 330, 200, 150);
    panel.add(panelMin);

    llenarMatrices();
  }

  public void llenarMatrices(){
    matriz1 = new Celda[5][4];
    matriz2 = new Celda[5][4];
    matriz3 = new Celda[5][4];
    matriz4 = new Celda[5][4];
    matrizU = new Celda[5][4];
    matrizMax = new Celda[5][4];
    matrizMin = new Celda[5][4];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        matriz1[i][j] = new Celda(0);
        panel1.add(matriz1[i][j]);

        matriz2[i][j] = new Celda(0);
        panel2.add(matriz2[i][j]);

        matriz3[i][j] = new Celda(0);
        panel3.add(matriz3[i][j]);

        matriz4[i][j] = new Celda(0);
        panel4.add(matriz4[i][j]);

        matrizU[i][j] = new Celda(0);
        panelUsuario.add(matrizU[i][j]);      

        matrizMax[i][j] = new Celda(0);
        panelMax.add(matrizMax[i][j]); 
        
        matrizMin[i][j] = new Celda(0);
        panelMin.add(matrizMin[i][j]);         
      }
    }  
  }

  public void actionPerformed(ActionEvent ev){
    if(ev.getSource()==b1){
      limpia();
      procedimiento();
    }
    else{
      limpia();
    }
  }

  public void procedimiento(){
    calcularArrays();
    sMatriz1=calcularNuevaMatriz(mat1);
    sMatriz2=calcularNuevaMatriz(mat2);
    sMatriz3=calcularNuevaMatriz(mat3);
    sMatriz4=calcularNuevaMatriz(mat4);
    M=obtenerMaximo(sMatriz1, sMatriz2, sMatriz3, sMatriz4);
    W=obtenerMinimo(sMatriz1, sMatriz2, sMatriz3, sMatriz4);
    recuperacion();
    mostrarMatriz();
  }

  public void calcularArrays(){
    for(int fila=0; fila<5; fila++){
      for(int columna=0; columna<4; columna++){
        int val1 = matriz1[fila][columna].getValor();
        int val2 = matriz2[fila][columna].getValor();
        int val3 = matriz3[fila][columna].getValor();
        int val4 = matriz4[fila][columna].getValor();
        int valU = matrizU[fila][columna].getValor();
        mat1.add(val1);
        mat2.add(val2);
        mat3.add(val3);
        mat4.add(val4);
        matUser.add(valU);
      }
    }

  }

  public ArrayList<Integer> calcularNuevaMatriz(ArrayList<Integer> matriz){
    ArrayList<Integer> matrizN = new ArrayList<Integer>();
    for(int i=0; i<tam; i++){
      int x=matriz.get(i);
      for(int j=0; j<tam; j++){
        int y=matriz.get(j);
        int resultado=alpha(x, y);
        matrizN.add(resultado);
      }
    }
    return matrizN;
  }

  public ArrayList<Integer> obtenerMaximo(ArrayList<Integer> matriz1, ArrayList<Integer> matriz2, ArrayList<Integer> matriz3, ArrayList<Integer> matriz4){
    ArrayList<Integer> matrizMax = new ArrayList<Integer>();
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    int mayor;
    for(int i=0; i<tam*tam; i++){
      numeros.add(matriz1.get(i));
      numeros.add(matriz2.get(i));
      numeros.add(matriz3.get(i));
      numeros.add(matriz4.get(i));
      mayor=Collections.max(numeros);
      matrizMax.add(mayor);
      numeros.clear();
    }
    return matrizMax;
  }

  public ArrayList<Integer> obtenerMinimo(ArrayList<Integer> matriz1, ArrayList<Integer> matriz2, ArrayList<Integer> matriz3, ArrayList<Integer> matriz4){
    ArrayList<Integer> matrizMin = new ArrayList<Integer>();
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    int menor;
    for(int i=0; i<tam*tam; i++){
      numeros.add(matriz1.get(i));
      numeros.add(matriz2.get(i));
      numeros.add(matriz3.get(i));
      numeros.add(matriz4.get(i));
      menor=Collections.min(numeros);
      matrizMin.add(menor);
      numeros.clear();
    }
    return matrizMin;
  }


  public int alpha(int x, int y){
    int val=0;
    if(x==0){
      if(y==0){
        val=1; //0,0 -> 1
      }
      else{
        val=0; //0,1 -> 0
      }
    }
    else{
      if(y==0){
        val=2; //1,0 -> 2
      }
      else{
        val=1; //1,1 -> 1
      }
    }
    return val;
  }

  public int beta(int x, int y){
    int val=0;
    if(x==0){
      if(y==0){
        val=0; //0,0 -> 0
      }
      else if(y==1){
        val=0; //0,1 -> 0
      }
    }
    else if(x==1){
      if(y==0){
        val=0; //1,0 -> 0
      }
      else{
        val=1; //1,1 -> 1
      }
    }
    else{
      if(y==0){
        val=1; //2,0 -> 1
      }
      else if(y==1){
        val=1; //2,1 -> 1
      }
    }
    return val;
  }

  public void recuperacion(){
    for(int o=0; o<M.size(); o++){
       recuperacionM.add(o);
    }

     for(int o=0; o<W.size(); o++){
       recuperacionW.add(o);
    }

    int contadM;
    for(int i= 0; i < matUser.size(); i++){
      contadM=i;
      for(int j = i; j < M.size(); j+= 20){
        recuperacionM.set(contadM, beta(M.get(j), matUser.get(i)));
        contadM+=20;
      }
    }

    int contadW;
    for(int i= 0; i < matUser.size(); i++){
      contadW=i;
      for(int j = i; j < W.size(); j+= 20){
        recuperacionW.set(contadW, beta(W.get(j), matUser.get(i)));
        contadW+=20;
      }
    }

    int menor;
    for(int i=0; i<recuperacionM.size(); i+=20){
      for(int j=0; j<20; j++){
        betaMax.add(recuperacionM.get(j+i));
      }
        menor=Collections.min(betaMax);
        betaMaxima.add(menor);
        betaMax.clear();
    }

    int mayor;
    for(int i=0; i<recuperacionM.size(); i+=20){
      for(int j=0; j<20; j++){
        betaMin.add(recuperacionW.get(j+i));
      }
        mayor=Collections.max(betaMin);
        betaMinima.add(mayor);
        betaMin.clear();
    }

  }

  public void mostrarMatriz(){
    int ind=0;
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 4; columna++) {
        int cond=betaMaxima.get(ind);
        if(cond==1){
          matrizMax[fila][columna].setBackground(Color.BLACK);
        }
        ind++;
      }
    }
    ind=0;
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 4; columna++) {
        int cond=betaMinima.get(ind);
        if(cond==1){
          matrizMin[fila][columna].setBackground(Color.BLACK);
        }
        ind++;
      }
    }
  }
  
  public void limpia(){
    mat1.clear(); mat2.clear(); mat3.clear(); mat4.clear(); matUser.clear();
    sMatriz1.clear(); sMatriz2.clear(); sMatriz3.clear(); sMatriz4.clear();
    M.clear(); W.clear(); recuperacionM.clear(); recuperacionW.clear();
    betaMax.clear(); betaMaxima.clear(); betaMin.clear(); betaMinima.clear();
    for (int fila = 0; fila < 5; fila++) {
      for (int columna = 0; columna < 4; columna++) {
          matrizMax[fila][columna].setBackground(Color.WHITE);
          matrizMin[fila][columna].setBackground(Color.WHITE);
      }
    }
  }



  public static void main (String [] args){
    mem n=new mem();
    n.extra();
  }
}