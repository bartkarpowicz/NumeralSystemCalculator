import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class KalkulatorFrame extends JFrame implements Runnable
{
    private JMenuBar menuGlowne;
    private JMenu menuPlik, menuObliczenia;
    private JMenuItem miZamknij, miOblicz, miWyczysc, miDodaj, miOdejmij, miPodziel, miPomnoz;
    private JRadioButton bin, dec, oct, hex;
    private ButtonGroup bg;
    private JTextField tfValue = new JTextField(4);
    private JTextField tfValue2 = new JTextField(4);
    private JButton bOblicz, bDodaj, bOdejmij, bPodziel, bPomnoz;
    private JLabel liczba1, liczba2, dzialanie, lWynik, binWynik, decWynik, octWynik, hexWynik;


    public static void main(String[] args){
        EventQueue.invokeLater(new KalkulatorFrame("Kalkulator"));
    }

    public KalkulatorFrame(String title)
    { super(title);
        Toolkit tk= Toolkit.getDefaultToolkit();
        Dimension dim=tk.getScreenSize();
        setSize(new Dimension(dim.width/2,dim.height/2));
        setLocationByPlatform(true);
        JPanel p = new JPanel();
        //p.setLayout(new GridLayout(5, 4, 3, 3));
        CloseAction ca = new CloseAction();
        ClearAction cl = new ClearAction();
        ComputeAction co = new ComputeAction();
        Dodawanie dod = new Dodawanie();
        Odejmowanie ode = new Odejmowanie();
        Dzielenie dzi = new Dzielenie();
        Mnozenie mno = new Mnozenie();
        WindowClosingListener wcl = new WindowClosingListener();
        addWindowListener(wcl);
        menuGlowne = new JMenuBar();
        menuPlik = new JMenu("Plik");
        menuObliczenia = new JMenu("Obliczenia");
        miZamknij = new JMenuItem(ca);
        miOblicz = new JMenuItem(co);
        miWyczysc = new JMenuItem(cl);
        miDodaj = new JMenuItem(dod);
        miOdejmij = new JMenuItem(ode);
        miPodziel = new JMenuItem(dzi);
        miPomnoz = new JMenuItem(mno);
        setJMenuBar(menuGlowne);
        menuGlowne.add(menuPlik);
        menuGlowne.add(menuObliczenia);
        menuPlik.add(miZamknij);
        menuPlik.add(miWyczysc);
        menuObliczenia.add(miOblicz);
        menuObliczenia.add(miDodaj);
        menuObliczenia.add(miOdejmij);
        menuObliczenia.add(miPodziel);
        menuObliczenia.add(miPomnoz);
        ButtonGroup bg = new ButtonGroup();
        bin = new JRadioButton("Binary");
        dec = new JRadioButton("Decimal");
        oct = new JRadioButton("Octal");
        hex = new JRadioButton("Hexagon");
        bg = new ButtonGroup();
        bg.add(bin);
        bg.add(dec);
        bg.add(oct);
        bg.add(hex);
        bin.setSelected(true);
        bin.setBounds(320, 20, 70, 20);
        add(bin);
        dec.setBounds(390, 20, 75, 20);
        add(dec);
        oct.setBounds(465, 20, 60, 20);
        add(oct);
        hex.setBounds(530, 20, 80, 20);
        add(hex);

        bOblicz = new JButton(co);
        bDodaj = new JButton(dod);
        bOdejmij = new JButton(ode);
        bPodziel = new JButton(dzi);
        bPomnoz = new JButton(mno);
        liczba1 = new JLabel();
        liczba1.setOpaque(true);
        liczba2 = new JLabel();
        liczba2.setOpaque(true);
        dzialanie = new JLabel();
        lWynik = new JLabel();
        binWynik = new JLabel();
        binWynik.setOpaque(true);
        decWynik = new JLabel();
        decWynik.setOpaque(true);
        octWynik = new JLabel();
        octWynik.setOpaque(true);
        hexWynik = new JLabel();
        hexWynik.setOpaque(true);
        tfValue.setBounds(40, 70, 240, 20);
        add(tfValue);
        tfValue2.setBounds(630, 70, 240, 20);
        add(tfValue2);
        bOblicz.setBounds(110, 100, 100, 20);
        add(bOblicz);
        bDodaj.setBounds(320, 70, 50, 20);
        add(bDodaj);
        bOdejmij.setBounds(400, 70, 50, 20);
        add(bOdejmij);
        bPodziel.setBounds(470, 70, 50, 20);
        add(bPodziel);
        bPomnoz.setBounds(540, 70, 50, 20);
        add(bPomnoz);
        liczba1.setFont(liczba1.getFont().deriveFont(20.0f));
        liczba1.setBounds(180, 170, 120, 20);
        add(liczba1);
        dzialanie.setFont(dzialanie.getFont().deriveFont(20.0f));
        dzialanie.setBounds(300, 170, 40, 20);
        add(dzialanie);
        liczba2.setFont(liczba2.getFont().deriveFont(20.0f));
        liczba2.setBounds(320, 170, 120, 20);
        add(liczba2);
        lWynik.setFont(lWynik.getFont().deriveFont(20.0f));
        lWynik.setBounds(480, 110, 250, 30);
        add(lWynik);
        binWynik.setFont(binWynik.getFont().deriveFont(20.0f));
        binWynik.setBounds(480, 140, 250, 20);
        add(binWynik);
        decWynik.setFont(decWynik.getFont().deriveFont(20.0f));
        decWynik.setBounds(480, 160, 250, 20);
        add(decWynik);
        octWynik.setFont(octWynik.getFont().deriveFont(20.0f));
        octWynik.setBounds(480, 180, 250, 20);
        add(octWynik);
        hexWynik.setFont(hexWynik.getFont().deriveFont(20.0f));
        hexWynik.setBounds(480, 200, 250, 20);
        add(hexWynik);
        Container cp = getContentPane();
        cp.add(p);
        setVisible(true);
    }
    private JFrame getMainWindow(){
        return this;
    }


    @Override
    public void run() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }
    class CloseAction extends AbstractAction{

        public CloseAction(){
            putValue(Action.NAME, "Zamknij");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            KalkulatorFrame kf = (KalkulatorFrame)getMainWindow();
            kf.dispatchEvent(new WindowEvent(kf, WindowEvent.WINDOW_CLOSING));
        }
    }
    class ComputeAction extends AbstractAction{

        public ComputeAction(){
            putValue(Action.NAME, "Konwertuj");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl K") );
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tfValue.getText().length()>0)
            {
                String tempVal = tfValue.getText();
                System.out.println(tempVal);
                if(bin.isSelected()){
                    System.out.println(tempVal+" Binary");
                    int decimal = Integer.parseInt(tempVal, 2);
                    binWynik.setText("BIN: "+tempVal);
                    decWynik.setText("DEC: "+Integer.toString(decimal));
                    octWynik.setText("OCT: "+Integer.toOctalString(decimal));
                    hexWynik.setText("HEX: "+Integer.toHexString(decimal));
                    lWynik.setText("Wynik konwertowania:");
                }
                if(dec.isSelected())
                {
                    System.out.println(tempVal+" Decimal");
                    int decimal = Integer.parseInt(tempVal);
                    binWynik.setText("BIN: "+Integer.toBinaryString(decimal));
                    decWynik.setText("DEC: "+decimal);
                    octWynik.setText("OCT: "+Integer.toOctalString(decimal));
                    hexWynik.setText("HEX: "+Integer.toHexString(decimal));
                    lWynik.setText("Wynik konwertowania:");
                }
                if(oct.isSelected())
                {
                    System.out.println(tempVal+" Octal");

                    int decimal = Integer.parseInt(tempVal, 8);
                    binWynik.setText("BIN: "+Integer.toBinaryString(decimal));
                    decWynik.setText("DEC: "+decimal);
                    octWynik.setText("OCT: "+Integer.toOctalString(decimal));
                    hexWynik.setText("HEX: "+Integer.toHexString(decimal));
                    lWynik.setText("Wynik konwertowania:");
                }
                if(hex.isSelected())
                {
                    System.out.println(tempVal+" Hexa");
                    int decimal = Integer.parseInt(tempVal, 16);
                    binWynik.setText("BIN: "+Integer.toBinaryString(decimal));
                    decWynik.setText("DEC: "+decimal);
                    octWynik.setText("OCT: "+Integer.toOctalString(decimal));
                    hexWynik.setText("HEX: "+Integer.toHexString(decimal));
                    lWynik.setText("Wynik konwertowania:");
                }
                 //   lWynik.setText(Double.toString(((UnitChangeAlgorithm)cbJednostki.getSelectedItem()).changeUnit(val)));
            }
        }
    }
    class ClearAction extends AbstractAction{

        public ClearAction(){
            putValue(Action.NAME, "WyczyÅ›Ä‡");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl W") );
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            tfValue.setText("");
            tfValue2.setText("");
            lWynik.setText("");
            liczba1.setText("");
            liczba1.setBackground(null);
            dzialanie.setText("");
            liczba2.setText("");
            liczba2.setBackground(null);
            binWynik.setText("");
            binWynik.setBackground(null);
            decWynik.setText("");
            decWynik.setBackground(null);
            octWynik.setText("");
            octWynik.setBackground(null);
            hexWynik.setText("");
            hexWynik.setBackground(null);
        }
    }
    class Dodawanie extends AbstractAction{

        public Dodawanie(){
            putValue(Action.NAME, "+");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D") );
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(tfValue.getText().length()>0 && tfValue2.getText().length()>0)
            {
                String tempVal = tfValue.getText();
                String tempVal2 = tfValue2.getText();

                if(bin.isSelected())
                {
                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);
                    int decimal = Integer.parseInt(tempVal, 2);
                    int decimal2 = Integer.parseInt(tempVal2, 2);
                    int wynik = decimal + decimal2;
                    liczba1.setText(tempVal);
                    dzialanie.setText(" + ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setBackground(Color.lightGray);
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(dec.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal);
                    int decimal2 = Integer.parseInt(tempVal2);
                    int wynik = decimal + decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" + ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setBackground(Color.lightGray);
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(oct.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,8);
                    int decimal2 = Integer.parseInt(tempVal2,8);
                    int wynik = decimal + decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" + ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setBackground(Color.lightGray);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(hex.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,16);
                    int decimal2 = Integer.parseInt(tempVal2,16);
                    int wynik = decimal + decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" + ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setBackground(Color.lightGray);
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
            }

        }
    }
    class Odejmowanie extends AbstractAction{

        public Odejmowanie(){
            putValue(Action.NAME, "-");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O") );
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(tfValue.getText().length()>0 && tfValue2.getText().length()>0)
            {
                String tempVal = tfValue.getText();
                String tempVal2 = tfValue2.getText();

                if(bin.isSelected())
                {
                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);
                    int decimal = Integer.parseInt(tempVal, 2);
                    int decimal2 = Integer.parseInt(tempVal2, 2);
                    int wynik = decimal - decimal2;
                    liczba1.setText(tempVal);
                    dzialanie.setText(" - ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setBackground(Color.lightGray);
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(dec.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal);
                    int decimal2 = Integer.parseInt(tempVal2);
                    int wynik = decimal - decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" - ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setBackground(Color.lightGray);
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(oct.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,8);
                    int decimal2 = Integer.parseInt(tempVal2,8);
                    int wynik = decimal - decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" - ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setBackground(Color.lightGray);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(hex.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,16);
                    int decimal2 = Integer.parseInt(tempVal2,16);
                    int wynik = decimal - decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" - ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setBackground(Color.lightGray);
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
            }

        }
    }
    class Dzielenie extends AbstractAction{

        public Dzielenie(){
            putValue(Action.NAME, "÷");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S") );
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(tfValue.getText().length()>0 && tfValue2.getText().length()>0)
            {
                String tempVal = tfValue.getText();
                String tempVal2 = tfValue2.getText();

                if(bin.isSelected())
                {
                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);
                    int decimal = Integer.parseInt(tempVal, 2);
                    int decimal2 = Integer.parseInt(tempVal2, 2);
                    int wynik = decimal / decimal2;
                    liczba1.setText(tempVal);
                    dzialanie.setText(" ÷ ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setBackground(Color.lightGray);
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                    

                }
                if(dec.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal);
                    int decimal2 = Integer.parseInt(tempVal2);
                    int wynik = decimal / decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" ÷ ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setBackground(Color.lightGray);
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(oct.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,8);
                    int decimal2 = Integer.parseInt(tempVal2,8);
                    int wynik = decimal / decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" ÷ ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setBackground(Color.lightGray);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(hex.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,16);
                    int decimal2 = Integer.parseInt(tempVal2,16);
                    int wynik = decimal / decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" ÷ ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setBackground(Color.lightGray);
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
            }

        }
    }
    class Mnozenie extends AbstractAction{

        public Mnozenie(){
            putValue(Action.NAME, "×");
            putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl M") );
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if(tfValue.getText().length()>0 && tfValue2.getText().length()>0)
            {
                String tempVal = tfValue.getText();
                String tempVal2 = tfValue2.getText();

                if(bin.isSelected())
                {
                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);
                    int decimal = Integer.parseInt(tempVal, 2);
                    int decimal2 = Integer.parseInt(tempVal2, 2);
                    int wynik = decimal * decimal2;
                    liczba1.setText(tempVal);
                    dzialanie.setText(" × ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setBackground(Color.lightGray);
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(dec.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal);
                    int decimal2 = Integer.parseInt(tempVal2);
                    int wynik = decimal * decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" × ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setBackground(Color.lightGray);
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(oct.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,8);
                    int decimal2 = Integer.parseInt(tempVal2,8);
                    int wynik = decimal * decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" × ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setBackground(Color.lightGray);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
                if(hex.isSelected())
                {
                    int decimal = Integer.parseInt(tempVal,16);
                    int decimal2 = Integer.parseInt(tempVal2,16);
                    int wynik = decimal * decimal2;
                    liczba1.setText(tempVal);

                    liczba1.setBackground(Color.lightGray);
                    liczba2.setBackground(Color.lightGray);

                    dzialanie.setText(" × ");
                    liczba2.setText(tempVal2+ " = ");
                    binWynik.setText("BIN: "+Integer.toBinaryString(wynik));
                    decWynik.setText("DEC: "+wynik);
                    octWynik.setText("OCT: "+Integer.toOctalString(wynik));
                    hexWynik.setBackground(Color.lightGray);
                    hexWynik.setText("HEX: "+Integer.toHexString(wynik));
                    lWynik.setText("Wynik dzialania:");
                }
            }

        }
    }
    class WindowClosingListener extends WindowAdapter{

        @Override
        public void windowClosing(WindowEvent e) {
            if(0==JOptionPane.showOptionDialog(e.getWindow(), "Czy na pewno chcesz zamknÄ…Ä‡ okno ?",
                    "Potwierdzenie", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new String[] {"Tak", "Nie"}, 1))
                System.exit(0);


        }
    }

}