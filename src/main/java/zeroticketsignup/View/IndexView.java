/*     */ package zeroticketsignup.View;
/*     */ import com.toedter.calendar.JDateChooser;
import java.awt.Color;
/*     */ import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
          import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ public class IndexView extends JFrame {
/*     */   public static JDateChooser birthCalendar;
/*     */   private JButton cancelarBTN;
/*     */   private JTextField companyField;
/*     */   private JLabel countRows;
/*     */   private JTextField dpiField;
/*     */   private JTextField findField;
/*     */   private JLabel jLabel10;
/*     */   private JLabel jLabel11;
/*     */   
/*     */   public IndexView() {
/*  29 */     initComponents();
/*  31 */     setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/zeroticketsignup/Images/logo2.jpg")));
/*  32 */     setTitle("Zero Innovation");
/*     */   }
/*     */   private JLabel jLabel12; private JLabel jLabel13; private JLabel jLabel14; private JLabel jLabel2; private JLabel jLabel3; private JLabel jLabel4; private JLabel jLabel6; private JLabel jLabel7; private JLabel jLabel8; private JLabel jLabel9; private JPanel jPanel1; private JPanel jPanel2; private JPanel jPanel3; private JPanel jPanel4; private JScrollPane jScrollPane2; private JTextField lastNameField; private JTextField mailField;
/*     */   private JTextField nameField;
/*     */   private JTextField phoneField;
/*     */   private JComboBox<String> sexCombo;
/*     */   private JTabbedPane tabContainer;
/*     */   private JTable tableMain;
/*     */   private JTextField tagField;
/*     */   private JButton validateBTN;
/*     */   
/*     */   private void initComponents() {
/*  44 */     this.jPanel1 = new JPanel();
/*  45 */     this.jLabel3 = new JLabel();
/*  46 */     this.jLabel4 = new JLabel();
/*  47 */     this.tabContainer = new JTabbedPane();
/*  48 */     this.jPanel3 = new JPanel();
/*  49 */     this.jPanel2 = new JPanel();
/*  50 */     this.jLabel8 = new JLabel();
/*  51 */     this.nameField = new JTextField();
/*  52 */     this.lastNameField = new JTextField();
/*  53 */     this.jLabel7 = new JLabel();
/*  54 */     this.jLabel9 = new JLabel();
/*  55 */     this.jLabel6 = new JLabel();
/*  56 */     this.jLabel10 = new JLabel();
/*  57 */     birthCalendar = new JDateChooser();
/*  58 */     this.dpiField = new JTextField();
/*  59 */     this.sexCombo = new JComboBox<>();
/*  60 */     this.jLabel11 = new JLabel();
/*  61 */     this.jLabel12 = new JLabel();
/*  62 */     this.jLabel13 = new JLabel();
/*  63 */     this.tagField = new JTextField();
/*  64 */     this.jLabel14 = new JLabel();
/*  65 */     this.mailField = new JTextField();
/*  66 */     this.phoneField = new JTextField();
/*  67 */     this.companyField = new JTextField();
/*  68 */     this.validateBTN = new JButton();
/*  69 */     this.cancelarBTN = new JButton();
/*  70 */     this.jPanel4 = new JPanel();
/*  71 */     this.findField = new JTextField();
/*  72 */     this.countRows = new JLabel();
/*  73 */     this.jScrollPane2 = new JScrollPane();
/*  74 */     this.tableMain = new JTable();
/*  75 */     this.jLabel2 = new JLabel();
/*     */     
/*  77 */     setDefaultCloseOperation(3);
/*  78 */     setResizable(false);
/*     */     
/*  80 */     this.jPanel1.setBackground(new Color(255, 255, 255));
/*     */     
/*  82 */     this.jLabel3.setHorizontalAlignment(0);
/*  83 */     this.jLabel3.setIcon(new ImageIcon(getClass().getResource("/zeroticketsignup/Images/logo2.jpg")));
/*     */     
/*  85 */     this.jLabel4.setFont(new Font("Yu Gothic Medium", 1, 18));
/*  86 */     this.jLabel4.setHorizontalAlignment(0);
/*  87 */     this.jLabel4.setText("Zero Innovation");
/*     */     
/*  89 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  90 */     this.jPanel1.setLayout(jPanel1Layout);
/*  91 */     jPanel1Layout.setHorizontalGroup(jPanel1Layout
/*  92 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  93 */         .addGroup(jPanel1Layout.createSequentialGroup()
/*  94 */           .addContainerGap()
/*  95 */           .addComponent(this.jLabel3, -2, 79, -2)
/*  96 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  97 */           .addComponent(this.jLabel4, -1, -1, 32767)
/*  98 */           .addContainerGap()));
/*     */     
/* 100 */     jPanel1Layout.setVerticalGroup(jPanel1Layout
/* 101 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 103 */           .addContainerGap()
/* 104 */           .addComponent(this.jLabel4, -2, 46, -2)
/* 105 */           .addContainerGap(-1, 32767))
/* 106 */         .addComponent(this.jLabel3, -1, -1, 32767));
/*     */ 
/*     */     
/* 109 */     this.jPanel2.setBorder(BorderFactory.createBevelBorder(0));
/*     */     
/* 111 */     this.jLabel8.setText("Nombre");
/*     */     
/* 113 */     this.nameField.setText("Geovanni Gerardo Rodolfo Eduardo");
/*     */     
/* 115 */     this.lastNameField.setText("Rojas Mazariegos");
/*     */     
/* 117 */     this.jLabel7.setText("Mail");
/*     */     
/* 119 */     this.jLabel9.setText("Fecha Nacimiento");
/*     */     
/* 121 */     this.jLabel6.setText("Empresa");
/*     */     
/* 123 */     this.jLabel10.setText("DPI");
/*     */     
/* 125 */     birthCalendar.setFocusCycleRoot(true);
/*     */     
/* 127 */     this.dpiField.addKeyListener(new KeyAdapter() {
/*     */           public void keyTyped(KeyEvent evt) {
/* 129 */             IndexView.this.dpiFieldKeyTyped(evt);
/*     */           }
/*     */         });
/*     */     
/* 133 */     this.sexCombo.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
/*     */     
/* 135 */     this.jLabel11.setText("Sexo");
/*     */     
/* 137 */     this.jLabel12.setText("Celular");
/*     */     
/* 139 */     this.jLabel13.setText("Tag UID");
/*     */     
/* 141 */     this.tagField.setEditable(false);
/* 142 */     this.tagField.setText("AGBKEJISLDKSL");
/*     */     
/* 144 */     this.jLabel14.setText("Apellido");
/*     */     
/* 146 */     this.mailField.setText("geovaroma@gmail.com");
/*     */     
/* 148 */     this.phoneField.addKeyListener(new KeyAdapter() {
/*     */           public void keyTyped(KeyEvent evt) {
/* 150 */             IndexView.this.phoneFieldKeyTyped(evt);
/*     */           }
/*     */         });
/*     */     
/* 154 */     this.companyField.setText("Zero Innovation");
/*     */     
/* 156 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 157 */     this.jPanel2.setLayout(jPanel2Layout);
/* 158 */     jPanel2Layout.setHorizontalGroup(jPanel2Layout
/* 159 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 160 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 161 */           .addContainerGap()
/* 162 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 163 */             .addComponent(this.nameField, -2, 198, -2)
/* 164 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 165 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 166 */                 .addComponent(this.jLabel8, -2, 103, -2)
/* 167 */                 .addComponent(this.jLabel10, -2, 103, -2)
/* 168 */                 .addComponent(this.dpiField, -2, 193, -2)
/* 169 */                 .addComponent((Component)birthCalendar, -2, 129, -2)
/* 170 */                 .addComponent(this.jLabel9, -2, 103, -2))
/* 171 */               .addGap(25, 25, 25)
/* 172 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 173 */                 .addComponent(this.sexCombo, -2, -1, -2)
/* 174 */                 .addComponent(this.lastNameField, -2, 198, -2)
/* 175 */                 .addComponent(this.jLabel11, -2, 103, -2)
/* 176 */                 .addComponent(this.jLabel12, -2, 103, -2)
/* 177 */                 .addComponent(this.jLabel14, -2, 103, -2)
/* 178 */                 .addComponent(this.phoneField, -2, 193, -2))))
/* 179 */           .addGap(33, 33, 33)
/* 180 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 181 */             .addComponent(this.jLabel13, -2, 103, -2)
/* 182 */             .addComponent(this.tagField, -2, 198, -2)
/* 183 */             .addComponent(this.jLabel6, -2, 103, -2)
/* 184 */             .addComponent(this.jLabel7, -2, 103, -2)
/* 185 */             .addComponent(this.mailField, -2, 198, -2)
/* 186 */             .addComponent(this.companyField, -2, 198, -2))
/* 187 */           .addGap(0, 0, 32767)));
/*     */ 
/*     */     
/* 190 */     jPanel2Layout.linkSize(0, new Component[] { (Component)birthCalendar, this.dpiField, this.lastNameField, this.nameField, this.sexCombo, this.tagField });
/*     */     
/* 192 */     jPanel2Layout.setVerticalGroup(jPanel2Layout
/* 193 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 194 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 195 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 196 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 197 */               .addGap(19, 19, 19)
/* 198 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 199 */                 .addComponent(this.jLabel8, -2, 27, -2)
/* 200 */                 .addComponent(this.jLabel7, -2, 27, -2)))
/* 201 */             .addGroup(jPanel2Layout.createSequentialGroup()
/* 202 */               .addGap(18, 18, 18)
/* 203 */               .addComponent(this.jLabel14, -2, 27, -2)
/* 204 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 205 */               .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 206 */                 .addComponent(this.nameField, -2, -1, -2)
/* 207 */                 .addComponent(this.lastNameField, -2, 33, -2)
/* 208 */                 .addComponent(this.mailField, -2, 33, -2))))
/* 209 */           .addGap(18, 18, 18)
/* 210 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 211 */             .addComponent(this.jLabel11, -2, 27, -2)
/* 212 */             .addComponent(this.jLabel6, -2, 27, -2)
/* 213 */             .addComponent(this.jLabel9, -2, 27, -2))
/* 214 */           .addGap(14, 14, 14)
/* 215 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 216 */             .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 217 */               .addComponent(this.sexCombo, -2, -1, -2)
/* 218 */               .addComponent(this.companyField, -2, 33, -2))
/* 219 */             .addComponent((Component)birthCalendar, -2, 31, -2))
/* 220 */           .addGap(12, 12, 12)
/* 221 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 222 */             .addComponent(this.jLabel10, -2, 26, -2)
/* 223 */             .addComponent(this.jLabel12, -2, 27, -2)
/* 224 */             .addComponent(this.jLabel13, -2, 27, -2))
/* 225 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 226 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 227 */             .addComponent(this.dpiField, -2, 37, -2)
/* 228 */             .addComponent(this.tagField, -2, 33, -2)
/* 229 */             .addComponent(this.phoneField, -2, 37, -2))
/* 230 */           .addContainerGap(14, 32767)));
/*     */ 
/*     */     
/* 233 */     jPanel2Layout.linkSize(1, new Component[] { this.lastNameField, this.nameField });
/*     */     
/* 235 */     jPanel2Layout.linkSize(1, new Component[] { (Component)birthCalendar, this.dpiField, this.sexCombo, this.tagField });
/*     */     
/* 237 */     this.validateBTN.setIcon(new ImageIcon(getClass().getResource("/zeroticketsignup/Images/aceptar.png")));
/* 238 */     this.validateBTN.setText("Guardar");
/* 239 */     this.validateBTN.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 241 */             IndexView.this.validateBTNActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 245 */     this.cancelarBTN.setIcon(new ImageIcon(getClass().getResource("/zeroticketsignup/Images/cancelar.png")));
/* 246 */     this.cancelarBTN.setText("Cancelar");
/*     */     
/* 248 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 249 */     this.jPanel3.setLayout(jPanel3Layout);
/* 250 */     jPanel3Layout.setHorizontalGroup(jPanel3Layout
/* 251 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 252 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 253 */           .addContainerGap()
/* 254 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 255 */             .addGroup(jPanel3Layout.createSequentialGroup()
/* 256 */               .addGap(420, 420, 420)
/* 257 */               .addComponent(this.validateBTN, -2, 125, -2)
/* 258 */               .addGap(18, 18, 18)
/* 259 */               .addComponent(this.cancelarBTN, -1, 111, 32767))
/* 260 */             .addComponent(this.jPanel2, -1, -1, 32767))
/* 261 */           .addContainerGap()));
/*     */ 
/*     */     
/* 264 */     jPanel3Layout.linkSize(0, new Component[] { this.cancelarBTN, this.validateBTN });
/*     */     
/* 266 */     jPanel3Layout.setVerticalGroup(jPanel3Layout
/* 267 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 268 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 269 */           .addContainerGap()
/* 270 */           .addComponent(this.jPanel2, -2, -1, -2)
/* 271 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 272 */           .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 273 */             .addComponent(this.validateBTN, -2, 77, -2)
/* 274 */             .addComponent(this.cancelarBTN, -2, 32, -2))
/* 275 */           .addGap(44, 44, 44)));
/*     */ 
/*     */     
/* 278 */     jPanel3Layout.linkSize(1, new Component[] { this.cancelarBTN, this.validateBTN });
/*     */     
/* 280 */     this.tabContainer.addTab("REGISTRO", this.jPanel3);
/*     */     
/* 282 */     this.findField.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
/*     */     
/* 284 */     this.countRows.setFont(new Font("Tahoma", 0, 18));
/* 285 */     this.countRows.setText("0");
/*     */     
/* 287 */     this.tableMain.setModel(new DefaultTableModel(new Object[0][], (Object[])new String[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     this.tableMain.setRowHeight(50);
/* 296 */     this.jScrollPane2.setViewportView(this.tableMain);
/*     */     
/* 298 */     this.jLabel2.setFont(new Font("Tahoma", 0, 18));
/* 299 */     this.jLabel2.setText("Buscar :");
/*     */     
/* 301 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 302 */     this.jPanel4.setLayout(jPanel4Layout);
/* 303 */     jPanel4Layout.setHorizontalGroup(jPanel4Layout
/* 304 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 305 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 306 */           .addContainerGap()
/* 307 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 308 */             .addGroup(GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
/* 309 */               .addComponent(this.jLabel2, -2, 103, -2)
/* 310 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 311 */               .addComponent(this.findField, -2, 459, -2)
/* 312 */               .addGap(18, 18, 18)
/* 313 */               .addComponent(this.countRows, -1, -1, 32767))
/* 314 */             .addComponent(this.jScrollPane2, -1, 703, 32767))
/* 315 */           .addContainerGap()));
/*     */     
/* 317 */     jPanel4Layout.setVerticalGroup(jPanel4Layout
/* 318 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 319 */         .addGroup(jPanel4Layout.createSequentialGroup()
/* 320 */           .addContainerGap()
/* 321 */           .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 322 */             .addComponent(this.findField, -2, 41, -2)
/* 323 */             .addComponent(this.countRows, -2, 41, -2)
/* 324 */             .addComponent(this.jLabel2, -2, 41, -2))
/* 325 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 326 */           .addComponent(this.jScrollPane2, -1, 346, 32767)
/* 327 */           .addContainerGap()));
/*     */ 
/*     */     
/* 330 */     this.tabContainer.addTab("LISTADO DE INVITADOS", this.jPanel4);
/*     */     
/* 332 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 333 */     getContentPane().setLayout(layout);
/* 334 */     layout.setHorizontalGroup(layout
/* 335 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 336 */         .addComponent(this.jPanel1, -1, -1, 32767)
/* 337 */         .addGroup(layout.createSequentialGroup()
/* 338 */           .addContainerGap()
/* 339 */           .addComponent(this.tabContainer)
/* 340 */           .addContainerGap()));
/*     */     
/* 342 */     layout.setVerticalGroup(layout
/* 343 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 344 */         .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 345 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 346 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 347 */           .addComponent(this.tabContainer)
/* 348 */           .addContainerGap()));
/*     */ 
/*     */     
/* 351 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void validateBTNActionPerformed(ActionEvent evt) {}
/*     */ 
/*     */   
/*     */   private void dpiFieldKeyTyped(KeyEvent evt) {
/* 359 */     char c = evt.getKeyChar();
/* 360 */     if (!Character.isDigit(c) && c != '\b' && c != '' && c != ' ') {
/* 361 */       getToolkit().beep();
/* 362 */       evt.consume();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void phoneFieldKeyTyped(KeyEvent evt) {
/* 367 */     char c = evt.getKeyChar();
/* 368 */     if (!Character.isDigit(c) && c != '\b' && c != '' && c != ' ') {
/* 369 */       getToolkit().beep();
/* 370 */       evt.consume();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 375 */   public static JDateChooser getBirthCalendar() { return birthCalendar; }
/*     */ 
/*     */ 
/*     */   
/* 379 */   public static void setBirthCalendar(JDateChooser birthCalendar) { IndexView.birthCalendar = birthCalendar; }
/*     */ 
/*     */ 
/*     */   
/* 383 */   public JButton getCancelarBTN() { return this.cancelarBTN; }
/*     */ 
/*     */ 
/*     */   
/* 387 */   public void setCancelarBTN(JButton cancelarBTN) { this.cancelarBTN = cancelarBTN; }
/*     */ 
/*     */ 
/*     */   
/* 391 */   public JTextField getCompanyField() { return this.companyField; }
/*     */ 
/*     */ 
/*     */   
/* 395 */   public void setCompanyField(JTextField companyField) { this.companyField = companyField; }
/*     */ 
/*     */ 
/*     */   
/* 399 */   public JTextField getDpiField() { return this.dpiField; }
/*     */ 
/*     */ 
/*     */   
/* 403 */   public void setDpiField(JTextField dpiField) { this.dpiField = dpiField; }
/*     */ 
/*     */ 
/*     */   
/* 407 */   public JLabel getjLabel10() { return this.jLabel10; }
/*     */ 
/*     */ 
/*     */   
/* 411 */   public void setjLabel10(JLabel jLabel10) { this.jLabel10 = jLabel10; }
/*     */ 
/*     */ 
/*     */   
/* 415 */   public JLabel getjLabel11() { return this.jLabel11; }
/*     */ 
/*     */ 
/*     */   
/* 419 */   public void setjLabel11(JLabel jLabel11) { this.jLabel11 = jLabel11; }
/*     */ 
/*     */ 
/*     */   
/* 423 */   public JLabel getjLabel12() { return this.jLabel12; }
/*     */ 
/*     */ 
/*     */   
/* 427 */   public void setjLabel12(JLabel jLabel12) { this.jLabel12 = jLabel12; }
/*     */ 
/*     */ 
/*     */   
/* 431 */   public JLabel getjLabel13() { return this.jLabel13; }
/*     */ 
/*     */ 
/*     */   
/* 435 */   public void setjLabel13(JLabel jLabel13) { this.jLabel13 = jLabel13; }
/*     */ 
/*     */ 
/*     */   
/* 439 */   public JLabel getjLabel14() { return this.jLabel14; }
/*     */ 
/*     */ 
/*     */   
/* 443 */   public void setjLabel14(JLabel jLabel14) { this.jLabel14 = jLabel14; }
/*     */ 
/*     */ 
/*     */   
/* 447 */   public JLabel getjLabel3() { return this.jLabel3; }
/*     */ 
/*     */ 
/*     */   
/* 451 */   public void setjLabel3(JLabel jLabel3) { this.jLabel3 = jLabel3; }
/*     */ 
/*     */ 
/*     */   
/* 455 */   public JLabel getjLabel4() { return this.jLabel4; }
/*     */ 
/*     */ 
/*     */   
/* 459 */   public void setjLabel4(JLabel jLabel4) { this.jLabel4 = jLabel4; }
/*     */ 
/*     */ 
/*     */   
/* 463 */   public JLabel getjLabel6() { return this.jLabel6; }
/*     */ 
/*     */ 
/*     */   
/* 467 */   public void setjLabel6(JLabel jLabel6) { this.jLabel6 = jLabel6; }
/*     */ 
/*     */ 
/*     */   
/* 471 */   public JLabel getjLabel7() { return this.jLabel7; }
/*     */ 
/*     */ 
/*     */   
/* 475 */   public void setjLabel7(JLabel jLabel7) { this.jLabel7 = jLabel7; }
/*     */ 
/*     */ 
/*     */   
/* 479 */   public JLabel getjLabel8() { return this.jLabel8; }
/*     */ 
/*     */ 
/*     */   
/* 483 */   public void setjLabel8(JLabel jLabel8) { this.jLabel8 = jLabel8; }
/*     */ 
/*     */ 
/*     */   
/* 487 */   public JLabel getjLabel9() { return this.jLabel9; }
/*     */ 
/*     */ 
/*     */   
/* 491 */   public void setjLabel9(JLabel jLabel9) { this.jLabel9 = jLabel9; }
/*     */ 
/*     */ 
/*     */   
/* 495 */   public JPanel getjPanel1() { return this.jPanel1; }
/*     */ 
/*     */ 
/*     */   
/* 499 */   public void setjPanel1(JPanel jPanel1) { this.jPanel1 = jPanel1; }
/*     */ 
/*     */ 
/*     */   
/* 503 */   public JPanel getjPanel2() { return this.jPanel2; }
/*     */ 
/*     */ 
/*     */   
/* 507 */   public void setjPanel2(JPanel jPanel2) { this.jPanel2 = jPanel2; }
/*     */ 
/*     */ 
/*     */   
/* 511 */   public JTextField getLastNameField() { return this.lastNameField; }
/*     */ 
/*     */ 
/*     */   
/* 515 */   public void setLastNameField(JTextField lastNameField) { this.lastNameField = lastNameField; }
/*     */ 
/*     */ 
/*     */   
/* 519 */   public JTextField getMailField() { return this.mailField; }
/*     */ 
/*     */ 
/*     */   
/* 523 */   public void setMailField(JTextField mailField) { this.mailField = mailField; }
/*     */ 
/*     */ 
/*     */   
/* 527 */   public JTextField getNameField() { return this.nameField; }
/*     */ 
/*     */ 
/*     */   
/* 531 */   public void setNameField(JTextField nameField) { this.nameField = nameField; }
/*     */ 
/*     */ 
/*     */   
/* 535 */   public JTextField getPhoneField() { return this.phoneField; }
/*     */ 
/*     */ 
/*     */   
/* 539 */   public void setPhoneField(JTextField phoneField) { this.phoneField = phoneField; }
/*     */ 
/*     */ 
/*     */   
/* 543 */   public JComboBox<String> getSexCombo() { return this.sexCombo; }
/*     */ 
/*     */ 
/*     */   
/* 547 */   public void setSexCombo(JComboBox<String> sexCombo) { this.sexCombo = sexCombo; }
/*     */ 
/*     */ 
/*     */   
/* 551 */   public JTextField getTagField() { return this.tagField; }
/*     */ 
/*     */ 
/*     */   
/* 555 */   public void setTagField(JTextField tagField) { this.tagField = tagField; }
/*     */ 
/*     */ 
/*     */   
/* 559 */   public JButton getValidateBTN() { return this.validateBTN; }
/*     */ 
/*     */ 
/*     */   
/* 563 */   public void setValidateBTN(JButton validateBTN) { this.validateBTN = validateBTN; }
/*     */ 
/*     */ 
/*     */   
/* 567 */   public JTextField getFindField() { return this.findField; }
/*     */ 
/*     */ 
/*     */   
/* 571 */   public void setFindField(JTextField findField) { this.findField = findField; }
/*     */ 
/*     */ 
/*     */   
/* 575 */   public JLabel getjLabel1() { return this.countRows; }
/*     */ 
/*     */ 
/*     */   
/* 579 */   public void setjLabel1(JLabel jLabel1) { this.countRows = jLabel1; }
/*     */ 
/*     */ 
/*     */   
/* 583 */   public JPanel getjPanel3() { return this.jPanel3; }
/*     */ 
/*     */ 
/*     */   
/* 587 */   public void setjPanel3(JPanel jPanel3) { this.jPanel3 = jPanel3; }
/*     */ 
/*     */ 
/*     */   
/* 591 */   public JPanel getjPanel4() { return this.jPanel4; }
/*     */ 
/*     */ 
/*     */   
/* 595 */   public void setjPanel4(JPanel jPanel4) { this.jPanel4 = jPanel4; }
/*     */ 
/*     */ 
/*     */   
/* 599 */   public JScrollPane getjScrollPane2() { return this.jScrollPane2; }
/*     */ 
/*     */ 
/*     */   
/* 603 */   public void setjScrollPane2(JScrollPane jScrollPane2) { this.jScrollPane2 = jScrollPane2; }
/*     */ 
/*     */ 
/*     */   
/* 607 */   public JTabbedPane getTabContainer() { return this.tabContainer; }
/*     */ 
/*     */ 
/*     */   
/* 611 */   public void setTabContainer(JTabbedPane tabContainer) { this.tabContainer = tabContainer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 617 */   public JTable getTableMain() { return this.tableMain; }
/*     */ 
/*     */ 
/*     */   
/* 621 */   public void setTableMain(JTable tableMain) { this.tableMain = tableMain; }
/*     */ 
/*     */ 
/*     */   
/* 625 */   public JLabel getCountRows() { return this.countRows; }
/*     */ 
/*     */ 
/*     */   
/* 629 */   public void setCountRows(JLabel countRows) { this.countRows = countRows; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*     */     try {
/* 653 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 654 */         if ("Nimbus".equals(info.getName())) {
/* 655 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 659 */     } catch (ClassNotFoundException ex) {
/* 660 */       Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
/* 661 */     } catch (InstantiationException ex) {
/* 662 */       Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
/* 663 */     } catch (IllegalAccessException ex) {
/* 664 */       Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
/* 665 */     } catch (UnsupportedLookAndFeelException ex) {
/* 666 */       Logger.getLogger(IndexView.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 672 */     EventQueue.invokeLater(new Runnable()
/*     */         {
/* 674 */           public void run() { (new IndexView()).setVisible(true); }
/*     */         });
/*     */   }
/*     */

    public void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/View/IndexView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */