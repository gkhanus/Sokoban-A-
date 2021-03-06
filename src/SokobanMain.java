import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ErdoganCIVIL
 */
public class SokobanMain extends javax.swing.JFrame {

    char secilenUzunluk;
    public String dosyaYolu;
    HaritaDurumu baslangicDurumu;
    BufferedImage tiles1[] = new BufferedImage[6];
    BufferedImage bg;   
    double screenScale;
    String cozum;
    int toplamGidilenDugum;
    int oncedenGidilenDugum;
    int dallanmaSayisi;
    int gidilenDugum; 
    long gecenSure;
    Point ajanKonum = new Point(), kutuKonum = new Point(), hedefKonum = new Point(); 
    int sayac=0;
    char yon;
    BufferedImage imageAjan, imageKutu, imageMaviKutu, imageHedef, imageYer;
    int a=50, b=50;
    /**
     * Creates new form SokobanMain
     */
    public SokobanMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ustPanel = new javax.swing.JPanel();
        algoritmaLabel = new javax.swing.JLabel();
        haritaButon = new javax.swing.JButton();
        dosyaYoluLabel = new javax.swing.JTextField();
        uzaklikLabel = new javax.swing.JLabel();
        uzaklikFormulu = new javax.swing.JComboBox<>();
        altPanel = new javax.swing.JPanel();
        oynatButon = new javax.swing.JButton();
        cozumLabel = new javax.swing.JLabel();
        sureSonucLabel = new javax.swing.JLabel();
        cozButon = new javax.swing.JButton();
        sureLabel = new javax.swing.JLabel();
        cozumSonucField = new javax.swing.JTextField();
        oyunPaneli = new javax.swing.JPanel();

        ustPanel.setPreferredSize(new java.awt.Dimension(750, 50));

        algoritmaLabel.setText("A* Algoritması");

        haritaButon.setText("Harita");
        haritaButon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                haritaButonMouseClicked(evt);
            }
        });
        haritaButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                haritaButonActionPerformed(evt);
            }
        });

        dosyaYoluLabel.setEditable(false);
        dosyaYoluLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosyaYoluLabelActionPerformed(evt);
            }
        });

        uzaklikLabel.setText("Uzaklık Formülü:");
        uzaklikLabel.setVisible(false);

        uzaklikFormulu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Manhattan", "Euclidean" }));
        uzaklikFormulu.setVisible(false);
        uzaklikFormulu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uzaklikFormuluActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ustPanelLayout = new javax.swing.GroupLayout(ustPanel);
        ustPanel.setLayout(ustPanelLayout);
        ustPanelLayout.setHorizontalGroup(
            ustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ustPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(algoritmaLabel)
                .addGap(40, 40, 40)
                .addComponent(haritaButon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dosyaYoluLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(uzaklikLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uzaklikFormulu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ustPanelLayout.setVerticalGroup(
            ustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ustPanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(ustPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(algoritmaLabel)
                    .addComponent(haritaButon)
                    .addComponent(dosyaYoluLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uzaklikLabel)
                    .addComponent(uzaklikFormulu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sokoban Oyunu");
        setResizable(false);

        altPanel.setPreferredSize(new java.awt.Dimension(750, 100));

        oynatButon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        oynatButon.setVisible(false);
        oynatButon.setText("OYNAT");
        oynatButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oynatButonActionPerformed(evt);
            }
        });

        cozumLabel.setText("           ");

        sureSonucLabel.setText(" ");

        cozButon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cozButon.setVisible(false);
        cozButon.setText("ÇÖZ");
        cozButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cozButonActionPerformed(evt);
            }
        });

        sureLabel.setText("               ");

        cozumSonucField.setVisible(false);
        cozumSonucField.setEditable(false);

        javax.swing.GroupLayout altPanelLayout = new javax.swing.GroupLayout(altPanel);
        altPanel.setLayout(altPanelLayout);
        altPanelLayout.setHorizontalGroup(
            altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(altPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cozumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sureLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(altPanelLayout.createSequentialGroup()
                        .addComponent(sureSonucLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 252, Short.MAX_VALUE))
                    .addComponent(cozumSonucField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cozButon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(oynatButon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        altPanelLayout.setVerticalGroup(
            altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(altPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cozumLabel)
                        .addComponent(cozumSonucField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cozButon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(oynatButon)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(altPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(altPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sureLabel)
                    .addComponent(sureSonucLabel))
                .addGap(24, 24, 24))
        );

        getContentPane().add(altPanel, java.awt.BorderLayout.PAGE_END);

        oyunPaneli.setPreferredSize(new java.awt.Dimension(750, 400));

        javax.swing.GroupLayout oyunPaneliLayout = new javax.swing.GroupLayout(oyunPaneli);
        oyunPaneli.setLayout(oyunPaneliLayout);
        oyunPaneliLayout.setHorizontalGroup(
            oyunPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 696, Short.MAX_VALUE)
        );
        oyunPaneliLayout.setVerticalGroup(
            oyunPaneliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        getContentPane().add(oyunPaneli, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void haritaButonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_haritaButonMouseClicked

    }//GEN-LAST:event_haritaButonMouseClicked

    private void haritaButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_haritaButonActionPerformed
        oyunPaneli.repaint();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt Files", "txt");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            dosyaYolu=selectedFile.getAbsolutePath();
            dosyaYoluLabel.setText(dosyaYolu);
        }
         
        try {
             baslangicDurumu = HaritaDurumu.haritaOlustur(dosyaYolu);
        }  catch (IOException e) {
	     System.out.println("Harita Bulunamadı!");
        }
        
        double screenScaleX = (double) (64 * HaritaDurumu.width + 0) / getWidth();
        double screenScaleY = (double) (64 * HaritaDurumu.height + 25) / getHeight();
        screenScale = screenScaleX > screenScaleY ? screenScaleX : screenScaleY;
        
        try {
            tiles1[0] = ImageIO.read(getClass().getResource("character.png"));
            tiles1[1] = ImageIO.read(getClass().getResource("wall.png"));
            tiles1[2] = ImageIO.read(getClass().getResource("ground.png"));
            tiles1[3] = ImageIO.read(getClass().getResource("box.png"));
            tiles1[4] = ImageIO.read(getClass().getResource("box_blue.png"));
            tiles1[5] = ImageIO.read(getClass().getResource("dest.png"));
        }catch (IOException e) {
            System.out.println("Resim dosyaları yüklenemedi!");
        }
        

        bg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        draw(bg.createGraphics());
        uzaklikLabel.setVisible(true);
        uzaklikFormulu.setVisible(true);
        
    }//GEN-LAST:event_haritaButonActionPerformed

    private void dosyaYoluLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosyaYoluLabelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dosyaYoluLabelActionPerformed

    private void uzaklikFormuluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uzaklikFormuluActionPerformed
        JComboBox comboBox = (JComboBox) evt.getSource();
        String selected = comboBox.getSelectedItem().toString();
        secilenUzunluk = selected.charAt(0);
        cozButon.setVisible(true);
    }//GEN-LAST:event_uzaklikFormuluActionPerformed

    private void oynatButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oynatButonActionPerformed
    String hareket = cozum.replaceAll(", ", "");
    sayac=0;
        try {
            imageAjan=ImageIO.read(getClass().getResource("character.png"));
            imageKutu=ImageIO.read(getClass().getResource("box.png"));
            imageMaviKutu=ImageIO.read(getClass().getResource("box_blue.png"));
            imageHedef=ImageIO.read(getClass().getResource("dest.png"));
            imageYer=ImageIO.read(getClass().getResource("ground.png"));
        } catch (IOException ex) {
            Logger.getLogger(SokobanMain.class.getName()).log(Level.SEVERE, null, ex);
        }
            Timer myTimer=new Timer();
            TimerTask gorev =new TimerTask() {
 
                    @Override
                    public void run() {
                       
                        yon = hareket.charAt(sayac);
                        sayac++;
                        if(sayac == hareket.length())
                            myTimer.cancel();
                        
                        cizdir(imageAjan.createGraphics(), ajanKonum, kutuKonum, hedefKonum);

                        switch(yon){
                        case 'r':
                            System.out.println("R");
                            break;
                        case 'l':
                            System.out.println("L");
                            break;
                        case 'u':
                            System.out.println("U");
                            break;
                        case 'd':
                            System.out.println("D");
                            break;
                        }
                        }                
                };
 
             myTimer.schedule(gorev,0,500);
    }//GEN-LAST:event_oynatButonActionPerformed

    private void cozButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cozButonActionPerformed
        cozumLabel.setText("Çözüm:");
        sureLabel.setText("Süre:");
        HaritaCozucu solver = null;
         
        switch (secilenUzunluk) {
            case 'E':
                solver = new AYıldız(baslangicDurumu, new EuclideanHeuristic());
                break;
            case 'M':
                solver = new AYıldız(baslangicDurumu, new ManhattanHeuristic());
                break;
            default:
                break;
        }

            if (solver != null) {
            cozum = solver.arama();
            toplamGidilenDugum = solver.toplamGidilenDugumleriHesapla();
            oncedenGidilenDugum = solver.oncedenGidilenleriHesapla();
            dallanmaSayisi = solver.DallanmaBoyutunuHesapla();
            gidilenDugum = solver.gidilenDugumleriHesapla();
            gecenSure = solver.gecenSureyiBul();
            System.out.println("Çözüm: " + cozum);
            cozumSonucField.setText(cozum);
            System.out.println("Toplamda gidilen düğüm sayısı: " + toplamGidilenDugum);
            System.out.println("Daha önceden gidilmiş düğüm sayısı: " + oncedenGidilenDugum);
            System.out.println("Dallanma Sayisi: " + dallanmaSayisi);
            System.out.println("Gidilen düğüm sayısı: " + gidilenDugum);
            System.out.println("Geçen süre(ms): " + gecenSure);
            sureSonucLabel.setText("Harita " +gecenSure+ " ms'de çözüldü.." );
            }
       
        cozumSonucField.setVisible(true);
        oynatButon.setVisible(true);
    }//GEN-LAST:event_cozButonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SokobanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SokobanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SokobanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SokobanMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SokobanMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algoritmaLabel;
    private javax.swing.JPanel altPanel;
    private javax.swing.JButton cozButon;
    private javax.swing.JLabel cozumLabel;
    private javax.swing.JTextField cozumSonucField;
    private javax.swing.JTextField dosyaYoluLabel;
    private javax.swing.JButton haritaButon;
    private javax.swing.JButton oynatButon;
    private javax.swing.JPanel oyunPaneli;
    private javax.swing.JLabel sureLabel;
    private javax.swing.JLabel sureSonucLabel;
    private javax.swing.JPanel ustPanel;
    private javax.swing.JComboBox<String> uzaklikFormulu;
    private javax.swing.JLabel uzaklikLabel;
    // End of variables declaration//GEN-END:variables

    private void draw(Graphics2D g) {
        g = (Graphics2D)oyunPaneli.getGraphics();
        //g.setBackground(Color.ORANGE);
        //g.clearRect(0, 0, 654, 493);
        g.translate(190, 0);       
        g.scale(0.7 / screenScale, 0.7 / screenScale);
        int tileSize = 64;
        
        try{
            BufferedReader br = new BufferedReader (new FileReader (dosyaYolu));
            String satir;
            char ch;
            int count = 0;
            br.readLine();
            br.readLine();
            while((satir = br.readLine()) != null){
                for (int x = 0; x<satir.length(); x++){
                    ch = satir.charAt(x);
                        g.drawImage(tiles1[2], x*tileSize, count*tileSize, null);
                    switch (ch) {
                        case '#':
                            g.drawImage(tiles1[1], x*tileSize, count*tileSize, null);              
                            break;
                        case '@':
                            g.drawImage(tiles1[0], x*tileSize, count*tileSize, null);
                            ajanKonum.x = x*tileSize;
                            ajanKonum.y = count*tileSize;
                            break;
                        case '.':
                            g.drawImage(tiles1[5], x*tileSize, count*tileSize, null);
                            hedefKonum.x = x*tileSize;
                            hedefKonum.y = count*tileSize;
                            break;
                        case '$':
                            g.drawImage(tiles1[3], x*tileSize, count*tileSize, null);
                            kutuKonum.x = x*tileSize;
                            kutuKonum.y = count*tileSize;
                            break;
                        case '*': 
                            g.drawImage(tiles1[3], x*tileSize, count*tileSize, null);
                            break;
                        case '+':
                            g.drawImage(tiles1[1], x*tileSize, count*tileSize, null);
                            break;
                        case ' ':
                            g.drawImage(tiles1[2], x*tileSize, count*tileSize, null);
                            break;
                        default:
                            break;
                    }
                }
                count++;
            }
        }catch (IOException e){
            System.out.println("Dosya Bulunamadı!");
        }
    }

    private void cizdir (Graphics2D h, Point o, Point k, Point d){
        h= (Graphics2D) oyunPaneli.getGraphics();
        h.translate(190, 0);       
        h.scale(0.7 / screenScale, 0.7 / screenScale);
        int imgBoyut=64;
        switch(yon){
                case 'd':                       
                    if((ajanKonum.x == kutuKonum.x)&&(ajanKonum.y+imgBoyut== kutuKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.y += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                        kutuKonum.y+=imgBoyut;
                        h.drawImage(imageKutu, kutuKonum.x, kutuKonum.y, null);
                        h.drawImage(imageYer, kutuKonum.x, kutuKonum.y-imgBoyut, null);
                        h.drawImage(imageAjan, kutuKonum.x, kutuKonum.y-imgBoyut, null); 
                    }
                    else if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y+imgBoyut== hedefKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.y += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);                           
                    }else{
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                            h.drawImage(imageHedef, hedefKonum.x, hedefKonum.y, null);
                        }
                        ajanKonum.y += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);}
                    break;
                case 'u':                                              
                    if((ajanKonum.x == kutuKonum.x)&&(ajanKonum.y-imgBoyut == kutuKonum.y)) {
                         h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.y -= imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                        kutuKonum.y-=imgBoyut;
                        h.drawImage(imageKutu, kutuKonum.x, kutuKonum.y, null);
                        h.drawImage(imageYer, kutuKonum.x, kutuKonum.y+imgBoyut, null);
                        h.drawImage(imageAjan, kutuKonum.x, kutuKonum.y+imgBoyut, null);                            
                    }
                    else if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y-imgBoyut== hedefKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.y -= imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                    }else{
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                            h.drawImage(imageHedef, hedefKonum.x, hedefKonum.y, null);
                        }
                        ajanKonum.y -= imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);}
                    break;
                case 'r':                                                                       
                    if((ajanKonum.x+imgBoyut == kutuKonum.x)&&(ajanKonum.y == kutuKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.x += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                        kutuKonum.x+=imgBoyut;
                        h.drawImage(imageKutu, kutuKonum.x, kutuKonum.y, null);
                        h.drawImage(imageYer, kutuKonum.x-imgBoyut, kutuKonum.y, null);
                        h.drawImage(imageAjan, kutuKonum.x-imgBoyut, kutuKonum.y, null);
                    }
                    else if((ajanKonum.x+imgBoyut == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.x += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                    }else{
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                            h.drawImage(imageHedef, hedefKonum.x, hedefKonum.y, null);
                        }
                        ajanKonum.x += imgBoyut;
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);}
                    break;
                case 'l':                                               
                    if((ajanKonum.x-imgBoyut == kutuKonum.x)&&(ajanKonum.y == kutuKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.x -= imgBoyut;  
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                        kutuKonum.x-=imgBoyut;
                        h.drawImage(imageKutu, kutuKonum.x, kutuKonum.y, null);
                        h.drawImage(imageYer, kutuKonum.x+imgBoyut, kutuKonum.y, null);
                        h.drawImage(imageAjan, kutuKonum.x+imgBoyut, kutuKonum.y, null);
                    }
                    else if((ajanKonum.x-imgBoyut == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        ajanKonum.x -= imgBoyut;  
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);
                    }else{
                        h.drawImage(imageYer, ajanKonum.x, ajanKonum.y, null);
                        if((ajanKonum.x == hedefKonum.x)&&(ajanKonum.y == hedefKonum.y)) {
                            h.drawImage(imageHedef, hedefKonum.x, hedefKonum.y, null);
                        }   
                        ajanKonum.x -= imgBoyut; 
                        h.drawImage(imageAjan, ajanKonum.x, ajanKonum.y, null);}
                    break;
                default:
                    break;
                }
            }           
    }
