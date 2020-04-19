import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Paint extends JFrame {

    //Painel onde tem o desenho (Visor ?)
    private DrawingPanel drawingPanel = new DrawingPanel();
    private JPanel painelFerramentas = new JPanel();
    private ArrayList<JRadioButton> buttons = new ArrayList<>();

    // Construtor do GUI

    /**
     *  Construtor do GUI
     *  e' utilizado o GridBagLayout para organizar todos os Panels com os RadioButtons e os Labels
     */
    public Paint(){
        super("Paint - ComputacaoGrafica - TP1");
        //Configurando o Layout Principal
        GridBagLayout gridLayout = new GridBagLayout();
        setLayout(gridLayout);
        painelFerramentas.setLayout(new GridBagLayout());

        //Constraints
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints cGroup = new GridBagConstraints();
        GridBagConstraints cItem = new GridBagConstraints();

        //Labels
        JLabel rLabel = new JLabel("Retas");
        JLabel pLabel = new JLabel("Poligonos");
        JLabel cLabel = new JLabel("Circunferencia");
        JLabel tLabel = new JLabel("Transformações");
        JLabel clipLabel = new JLabel("Recorte");
        JLabel fillLabel = new JLabel("Preenchimento");
        JLabel optionLabel = new JLabel("Opções");
        //Panels
        JPanel rPanel = new JPanel(new GridBagLayout());
        JPanel pPanel = new JPanel(new GridBagLayout());
        JPanel cPanel = new JPanel(new GridBagLayout());
        JPanel tPanel = new JPanel(new GridBagLayout());
        JPanel clipPanel = new JPanel(new GridBagLayout());
        JPanel fillPanel = new JPanel(new GridBagLayout());
        JPanel optionPanel = new JPanel(new GridBagLayout());
        JPanel aboutPanel = new JPanel(new GridBagLayout());

        //Buttons
        JRadioButton rDDA = new JRadioButton("DDA");
        JRadioButton rBresenham = new JRadioButton("Bresenham");
        JRadioButton pRetangulo = new JRadioButton("Retangulo");
        JRadioButton cBresenham = new JRadioButton("Bresenham(Circ.)");
        JRadioButton clipLiangBarsky = new JRadioButton("Liang-Barsky");
        JRadioButton clipCohenSutherLand = new JRadioButton("Cohen-Sutherland");
        JRadioButton rotacionar = new JRadioButton("Rotacionar");
        JRadioButton mover = new JRadioButton("Mover");
        JRadioButton redimencionar = new JRadioButton("Redimencionar");
        JRadioButton cisalhamento = new JRadioButton("Cisalhamento");
        JRadioButton reflexao = new JRadioButton("Reflexao");
        JRadioButton boundaryFill = new JRadioButton("Boundary-Fill");
        JRadioButton floodFill = new JRadioButton("Flood-Fill");
        JButton limparTela = new JButton("Limpar Desenhos");



        //Listeners
        rDDA.addActionListener(new ButtonListener());
        rBresenham.addActionListener(new ButtonListener());
        pRetangulo.addActionListener(new ButtonListener());
        cBresenham.addActionListener(new ButtonListener());
        mover.addActionListener(new ButtonListener());
        rotacionar.addActionListener(new ButtonListener());
        redimencionar.addActionListener(new ButtonListener());
        cisalhamento.addActionListener(new ButtonListener());
        reflexao.addActionListener(new ButtonListener());
        clipCohenSutherLand.addActionListener(new ButtonListener());
        clipLiangBarsky.addActionListener(new ButtonListener());
        boundaryFill.addActionListener(new ButtonListener());
        floodFill.addActionListener(new ButtonListener());
        limparTela.addActionListener(new ButtonListener());

        //Filling the button list
        buttons.add(rDDA);
        buttons.add(rBresenham);
        buttons.add(pRetangulo);
        buttons.add(cBresenham);
        buttons.add(mover);
        buttons.add(rotacionar);
        buttons.add(redimencionar);
        buttons.add(clipCohenSutherLand);
        buttons.add(clipLiangBarsky);
        buttons.add(cisalhamento);
        buttons.add(reflexao);
        buttons.add(boundaryFill);
        buttons.add(floodFill);


        // Configurando Painel de Ferramentas
        //Insets(top, left, bottom, right)
        // Panel de Retas
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        rPanel.add(rLabel, cItem);

        //Botao para Reta DDA
        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        rPanel.add(rDDA, cItem);

        //Botao para Reta Bresenham
        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        rPanel.add(rBresenham, cItem);

        cGroup.gridx = 0;
        cGroup.gridy = 0;
        cGroup.anchor = GridBagConstraints.NORTHWEST;
        cGroup.insets = new Insets(10,10,10,10);
        painelFerramentas.add(rPanel, cGroup);

        // Panel de Poligono
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        pPanel.add(pLabel, cItem);
        
        cItem.gridy++;
        cItem.insets = new Insets(0, 0 ,0, 0);
        pPanel.add(pRetangulo, cItem);
        
        cGroup.gridy++;
        painelFerramentas.add(pPanel, cGroup);

        // Panel de Circunferencia
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        cPanel.add(cLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0 ,0, 0);
        cPanel.add(cBresenham, cItem);

        cGroup.gridy++;
        painelFerramentas.add(cPanel, cGroup);

        //Adicionando Ferramentas de Transformacao
        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        tPanel.add(tLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(mover, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(rotacionar, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(redimencionar, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 5, 0);
        tPanel.add(reflexao, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0, 0, 0, 0);
        tPanel.add(cisalhamento, cItem);


        cGroup.gridy++;
        painelFerramentas.add(tPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        clipPanel.add(clipLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        clipPanel.add(clipCohenSutherLand, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        clipPanel.add(clipLiangBarsky, cItem);

        cGroup.gridy++;
        painelFerramentas.add(clipPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);
        fillPanel.add(fillLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,5,0);
        fillPanel.add(boundaryFill, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        fillPanel.add(floodFill, cItem);

        cGroup.gridy++;
        painelFerramentas.add(fillPanel, cGroup);

        cItem.gridx = 0;
        cItem.gridy = 0;
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.insets = new Insets(0,0,10,5);

        optionPanel.add(optionLabel, cItem);

        cItem.gridy++;
        cItem.insets = new Insets(0,0,0,0);
        optionPanel.add(limparTela, cItem);

        cGroup.gridy++;
        painelFerramentas.add(optionPanel, cGroup);

        cGroup.gridy++;
        painelFerramentas.add(aboutPanel, cGroup);

        painelFerramentas.validate();

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.VERTICAL;

        add(painelFerramentas, c);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;

        add(drawingPanel, c);

        //Definindo preferencias
        drawingPanel.setBackground(Color.white);
        drawingPanel.addMouseListener(drawingPanel);
        Dimension d = new Dimension(1024, 720);

        setPreferredSize(d);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /**
     * Classe responsavel pela parte desenhavel do codigo
     */
    private class DrawingPanel extends Panel implements MouseListener{
        // Constantes para testes durante a execucao do programa
        private static final int RETA = 0;
        private static final int CIRCUNFERENCIA = 1;
        private static final int RETANGULO = 2;
        private static final int MOVER = 1;
        private static final int ROTACIONAR = 2;
        private static final int REDIMENCIONAR = 3;
        private static final int RECORTECOHEN = 6;
        private static final int RECORTELIANG = 7;
        private static final int PREENCHEBOUNDARY = 8;
        private static final int PREENCHEFLOOD = 9;
        private boolean isDefaultAlgorithm = true;
        private Ponto pontoInicial = null;
        private Ponto pontoFinal = null;
        private Figura figura = null;
        //Qual operacao a ser realizada ao clicar (mover = 1, rotacionar = 2, redimencionar = 3)
        private int action = 0;
        //Todas as figuras inseridas na tela
        ArrayList<Figura> figuras = new ArrayList<>();
        //Qual figura deve ser realizado a operacao
        private int index;
        BufferedImage img = new BufferedImage(1600, 720, BufferedImage.TYPE_INT_RGB);
        Graphics2D teste = null;

        /**
         * Metodo Paint default da classe Panel desenha todas as figuras
         * utilizando por default o algoritmo de Bresenham
         * @param g - Graphics object
         */
        @Override
        public void paint(Graphics g) {
            if(!figuras.isEmpty()){
                g = (Graphics2D) g;
                teste = (Graphics2D) g;
                if(action == RECORTECOHEN || action == RECORTELIANG){

                    //Desenhando a janela de recorte
                    double dX = pontoFinal.x - pontoInicial.x;
                    double dY = pontoFinal.y - pontoInicial.y;

                    Reta top = new Reta();
                    Reta bottom = new Reta();
                    Reta left = new Reta();
                    Reta right = new Reta();

                    top.pontoInicial = new Ponto(pontoInicial.x, pontoInicial.y);
                    top.pontoFinal = new Ponto(pontoInicial.x + dX,pontoInicial.y);

                    bottom.pontoInicial = new Ponto(pontoFinal.x - dX, pontoFinal.y);
                    bottom.pontoFinal = new Ponto(pontoFinal.x, pontoFinal.y);

                    left.pontoInicial = new Ponto(pontoInicial.x, pontoInicial.y);
                    left.pontoFinal = new Ponto(bottom.pontoInicial.x, bottom.pontoInicial.y);

                    right.pontoInicial = new Ponto(top.pontoFinal.x, top.pontoFinal.y);
                    right.pontoFinal = new Ponto(pontoFinal.x, pontoFinal.y);
                    bottom.cor = Color.MAGENTA;
                    top.cor = Color.MAGENTA;
                    left.cor = Color.MAGENTA;
                    right.cor = Color.MAGENTA;

                    top.desenharFiguraBresenham(img);
                    bottom.desenharFiguraBresenham(img);
                    left.desenharFiguraBresenham(img);
                    right.desenharFiguraBresenham(img);

                    if(action == RECORTECOHEN){
                        for(Figura f : figuras){
                            ((Reta) f).cohenClip(img, pontoInicial, pontoFinal);
                        }
                        pontoInicial = pontoFinal = null;
                        this.action = 10; // Action invalida para nao fazer nada se clicar
                    }else{
                        for(Figura f : figuras){
                            ((Reta) f).liangClip(img, pontoInicial, pontoFinal);
                        }
                        pontoInicial = pontoFinal = null;
                        this.action = 10; // Action invalida para nao fazer nada se clicar
                    }
                }else if(action == PREENCHEBOUNDARY){
                    System.out.println(pontoInicial);
                    for(Figura f : figuras)
                        f.desenharFiguraBresenham(img);
                    g.drawImage(img,0 ,0,null);
                    this.boundaryFour(img, pontoInicial.x, pontoInicial.y, Color.BLACK.getRGB(), Color.BLACK.getRGB());
                    for(Figura f : figuras)
                        f.desenharFiguraBresenham(img);
                    pontoInicial = pontoFinal = null;
                }else if(action == PREENCHEFLOOD){
                    int rgb = img.getRGB((int)Math.round(pontoInicial.x), (int)Math.round(pontoInicial.y));
                    for(Figura f : figuras)
                        f.desenharFiguraBresenham(img);
                    g.drawImage(img,0 ,0,null);
                    this.floodFour(img, pontoInicial.x, pontoInicial.y, rgb , Color.BLACK.getRGB());
                    pontoInicial = pontoFinal = null;
                }else{
                    if(isDefaultAlgorithm){
                        for(Figura f : figuras)
                            f.desenharFiguraBresenham(img);
                    }else{
                        for(Figura f : figuras)
                            if(f.isCircunferencia)
                                f.desenharFiguraBresenham(img);
                            else if(f.isRetangulo)
                                f.desenharFiguraBresenham(img);
                            else
                                f.desenharFiguraDDA(img);
                    }
                }
                g.drawImage(img,0 ,0,null);
            }
        }

        /**
         * Metodo a ser chamado por classes superiores para inserir nova figura
         * @param key - RETA - 0, CIRCUNFERENCIA - 1, RETANGULO - 2
         * @param algorithm DDA - 0, BRESENHAM - 1
         */
        public void desenharFigura(int key, int algorithm){
                if(key == CIRCUNFERENCIA){
                    this.figura = new Circunferencia();
                    this.isDefaultAlgorithm = true;
                }else if(key == RETANGULO){
                    this.figura = new Retangulo();
                    this.isDefaultAlgorithm = true;
                }else if(algorithm == 0){
                    this.figura = new Reta();
                    this.isDefaultAlgorithm = false;
                }else if(algorithm == 1){
                    this.figura = new Reta();
                    this.isDefaultAlgorithm = true;
                }
            action = 0;
        }

        /**
         * Metodo utilizado para atualizar uma alteracao no paint,
         * ele primeiro seta todos os pixels como branco e depois chama o metodo repaint
         * sem isso estava ficando tudo preto ao tentar desenhar uma reta
         */
        private void updatePaint() {
            for(int i = 0; i < 1024; i++)
                for(int j = 0; j < 720;j++)
                    img.setRGB(i,j,Color.WHITE.getRGB());
            repaint();
        }

        /**
         * Painel de desenho usa os eventos de click para definir os pontos iniciais e finais
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if(action == 0){
                if(pontoInicial == null && pontoFinal == null){
                    pontoInicial = new Ponto(e.getX(), e.getY());
                }else if(pontoFinal == null){
                    pontoFinal = new Ponto(e.getX(), e.getY());
                    if(figura != null){
                        figura.pontoInicial = pontoInicial;
                        figura.pontoFinal = pontoFinal;
                    }
                    figuras.add(figura);
                    if(figura.isCircunferencia)
                        figura = new Circunferencia();
                    else if(figura.isRetangulo)
                        figura = new Retangulo();
                    else
                        figura = new Reta();
                        

                    pontoFinal = pontoInicial = null;
                    updatePaint();
                }
            }else if(action == MOVER){
                if(figuras.isEmpty()){
                    JOptionPane.showMessageDialog(null,"não há nenhuma figura!");
                }else if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo mover circunferencia!"); // hehe
                    else{
                        f.moverFigura(new Ponto(e.getX(), e.getY()));
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"posição invalida!");
                }
            }else if(action == RECORTECOHEN || action == RECORTELIANG){
                if(pontoInicial == null && pontoFinal == null){
                    pontoInicial = new Ponto(e.getX(), e.getY());
                }else if(pontoFinal == null) {
                    pontoFinal = new Ponto(e.getX(), e.getY());
                    updatePaint();
                }
            }else if(action == PREENCHEBOUNDARY || action == PREENCHEFLOOD){
                if(pontoInicial == null){
                    pontoInicial = new Ponto(e.getX(), e.getY());
                    updatePaint();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        public void rotacionarFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo rotacionar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite o grau de rotação: ", "0.0");
                        double grau = Double.parseDouble(input);
                        f.rotacionarFigura(grau);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        public void redimencionarFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo redimencionar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite a escala de redimencionamento de X: ", "0.0");
                        double escalaX = Double.parseDouble(input);
                        input = JOptionPane.showInputDialog("Digite a escala de redimencionamento de Y: ", "0.0");
                        double escalaY = Double.parseDouble(input);
                        f.mudarEscalaFigura(escalaX, escalaY);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        /**
         * Metodo semelhante ao updatePaint mas este e' chamado por fora, pelo botao de limpar figuras
         * ele e responsavel por esvaziar a lista de figuras
         */
        public void clear() {
            this.figuras.clear();
            updatePaint();
        }

        /**
         * Lidando com o botao de espelhamento
         */
        public void espelharFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo espelhar uma circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite qual eixo a ser espelhado: (0 - Eixo X, 1 - Eixo Y, 2 - Origem) ", "0");
                        int opcode = Integer.parseInt(input);
                        f.espelharFigura(opcode);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        /**
         * Lidando com o botao de cisalhamento
         */
        public void cisalharFigura() {
            if(!figuras.isEmpty()){
                if(index < figuras.size() && index >= 0){
                    Figura f = figuras.get(index);
                    if(f.isCircunferencia)
                        JOptionPane.showMessageDialog(null,"Yo no puedo aplicar uma pressao numa circunferencia!!");
                    else{
                        String input = JOptionPane.showInputDialog("Digite qual eixo a ser cisalhado: (0 - Eixo X, 1 - Eixo Y) ", "0");
                        int opcode = Integer.parseInt(input);
                        input = JOptionPane.showInputDialog("Digite um fator de cisalhamento a ser aplicado: ", "0.0");
                        double fator = Double.parseDouble(input);
                        f.shearFigura(fator, opcode);
                        updatePaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Posicion Invalida muchacho(a)");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Não há nenhuma figura!");
            }
        }

        /**
         * Implementacao recursiva do metodo de preenchimento boundary4
         * @param img BufferedImage que esta' sendo desenhado
         * @param x valor x do ponto
         * @param y valor y do ponto
         * @param borda Cor da borda
         * @param nova Cor nova a ser pintada
         */
        public void boundaryFour(BufferedImage img, double x, double y, int borda, int nova){
            int atual = img.getRGB((int)Math.round(x),(int)Math.round(y));
            if(atual != borda && atual != nova){
                img.setRGB((int)Math.round(x), (int)Math.round(y), nova);
                teste.drawImage(img,0,0,null);
                boundaryFour(img,x + 1, y, borda, nova);
                boundaryFour(img,x - 1, y, borda, nova);
                boundaryFour(img,x, y + 1, borda, nova);
                boundaryFour(img,x, y - 1, borda, nova);
            }
        }

        /**
         * Implementacao recursiva do metodo de preenchimento flood4
         * @param img BufferedImage que esta sendo desenhado
         * @param x valor x do ponto
         * @param y valor y do ponto
         * @param antiga cor antiga para ser preenchida (implementado onde e' clicado)
         * @param nova cor nova para ser preenchida
         */
        public void floodFour(BufferedImage img, double x, double y, int antiga, int nova){
            int atual = img.getRGB((int)Math.round(x),(int)Math.round(y));
            if(atual == antiga){
                img.setRGB((int)Math.round(x), (int)Math.round(y), Color.BLACK.getRGB());
                floodFour(img,x + 1, y, antiga, nova);
                floodFour(img,x - 1, y, antiga, nova);
                floodFour(img,x, y + 1, antiga, nova);
                floodFour(img,x, y - 1, antiga, nova);
            }
        }
    }


    /**
     * Classe para lidar com os clicks nos botoes do menu
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "DDA":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(0, 0);
                    break;
                case "Bresenham":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(0, 1);
                    break;
                case "Retangulo":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    //TODO: drawingPanel.desenharFigura(0, 1);
                break;
                case "Bresenham(Circ.)":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.desenharFigura(1, 1);
                    break;
                case "Mover":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        String input = JOptionPane.showInputDialog("Qual reta deve ser movida? (0,1,2..)","0");
                        drawingPanel.action = 1;
                        drawingPanel.index = Integer.parseInt(input);
                    break;
                case "Rotacionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        input = JOptionPane.showInputDialog("Qual reta deve ser redimencionada? (0,1,2..)","0");
                        drawingPanel.index = Integer.parseInt(input);
                        drawingPanel.rotacionarFigura();
                    break;
                case "Redimencionar":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        input = JOptionPane.showInputDialog("Qual reta deve ser redimencionada? (0,1,2..)","0");
                        drawingPanel.action = 3;
                        drawingPanel.index = Integer.parseInt(input);
                        drawingPanel.redimencionarFigura();
                    break;
                case "Reflexao":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    input = JOptionPane.showInputDialog("Qual reta deve ser espelhada? (0,1,2..)","0");
                    drawingPanel.action = 4;
                    drawingPanel.index = Integer.parseInt(input);
                    drawingPanel.espelharFigura();
                    break;
                case "Cisalhamento":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    input = JOptionPane.showInputDialog("Qual reta deve ser cisalhada? (0,1,2..)","0");
                    drawingPanel.action = 5;
                    drawingPanel.index = Integer.parseInt(input);
                    drawingPanel.cisalharFigura();
                    break;
                case "Cohen-Sutherland":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                        drawingPanel.action = 6;
                    break;
                case "Liang-Barsky":
                        limparSelecionados(buttons.indexOf(e.getSource()));
                    drawingPanel.action = 7;
                    break;
                case "Boundary-Fill":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    drawingPanel.action = 8;
                    break;
                case "Flood-Fill":
                    limparSelecionados(buttons.indexOf(e.getSource()));
                    drawingPanel.action = 9;
                    break;
                case "Limpar Desenhos":
                    drawingPanel.clear();
                    break;
            }
        }

        /**
         * metodo para remover selecao de outros radiobuttons
         * para que fique apenas um com bolinha
         * @param index
         */
        public void limparSelecionados(int index){
            for(int i = 0; i < buttons.size(); i++)
                if(i != index)
                    buttons.get(i).setSelected(false);
        }
    }
}
