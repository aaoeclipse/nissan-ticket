/*     */ package zeroticketsignup.Controller;
/*     */ 
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.PreparedStatement;
/*     */ import com.mysql.jdbc.Statement;
/*     */ import com.sltech.dpi.smartcard.SmartCardDPIReader;
/*     */ import com.toedter.calendar.JDateChooser;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.sql.Date;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.smartcardio.CardTerminal;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.SwingWorker;
import javax.swing.table.TableModel;
/*     */ import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
/*     */ import zeroticketsignup.Utils.DPIUtils;
/*     */ import zeroticketsignup.Utils.ImageUtils;
/*     */ import zeroticketsignup.View.IndexView;
/*     */ import zeroticketsignup.View.LoadView;
/*     */ import zeroticketsignup.ZeroTicketSignUp;
/*     */ 
/*     */ 
/*     */ public class IndexController
/*     */   implements MouseListener, ActionListener
/*     */ {
/*     */   private IndexView view;
/*     */   private DPIinfo info;
/*     */   private String uid;
/*     */   private int num;
/*     */   private Statement st;
/*     */   private Connection connection;
/*     */   
/*     */   public IndexController(IndexView view) {
/*  50 */     this.info = null;
/*     */     
/*  52 */     this.uid = "";
/*  53 */     this.num = -1;
/*     */ 
/*     */     
/*  56 */     this.st = DataBaseClass.getSt();
/*  57 */     this.connection = DataBaseClass.getConnection();
/*  58 */     this.resultSet = DataBaseClass.getResultSet();
/*     */     
/*  60 */     this.goodDPI = false;
/*  61 */     this.status = "";
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
/*  75 */     this.view = view;
/*     */ 
/*     */     
/*  78 */     view.getCancelarBTN().addMouseListener(this);
/*  79 */     view.getValidateBTN().addMouseListener(this);
/*  80 */     view.getFindField().addActionListener(this);
/*  81 */     view.getTableMain().addMouseListener(this);
/*     */ 
/*     */     
/*  84 */     view.getSexCombo().removeAllItems();
/*  85 */     view.getSexCombo().addItem("FEMENINO");
/*  86 */     view.getSexCombo().addItem("MASCULINO");
/*  87 */     AutoCompleteDecorator.decorate(view.getSexCombo());
/*  88 */     view.getSexCombo().setSelectedIndex(0);
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
/* 126 */     blankField();
/*     */ 
/*     */     
/* 129 */     ScheduledExecutorService scheduler2 = Executors.newScheduledThreadPool(1);
/* 130 */     ScheduledFuture<?> sF2 = scheduler2.scheduleAtFixedRate(new ReadDPI(), 500L, 250L, TimeUnit.MILLISECONDS);
/*     */ 
/*     */     
/* 133 */     ScheduledExecutorService scheduler3 = Executors.newScheduledThreadPool(1);
/* 134 */     ScheduledFuture<?> sF3 = scheduler3.scheduleAtFixedRate(new ReadTag(), 500L, 250L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */   
/*     */   private ResultSet resultSet;
/*     */   
/*     */   private boolean goodDPI;
/*     */   private String status;
/*     */   
/*     */   private Date getCurrentDate(JDateChooser calendar) {
/* 144 */     java.util.Date today = calendar.getDate();
/* 145 */
 return new Date(today.getTime());
/*     */   }
/*     */   private InputStream photoInputStream; private TablesModel modelTable; private String errorQuery;
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 150 */     if (e.getSource() == this.view.getCancelarBTN()) {
/* 151 */       blankField();
/*     */     }
/* 153 */     else if (e.getSource() == this.view.getValidateBTN()) {
/*     */       
/* 155 */       if (!this.view.getNameField().getText().isEmpty() && !this.view.getLastNameField().getText().isEmpty() && !this.view.getTagField().getText().isEmpty()) {
/*     */ 
/*     */         
/* 158 */         int codigoRecienIngresado = -1;
/*     */         
/* 160 */         String query = "";
/* 161 */         if (this.num == -1) {
/* 162 */           query = "INSERT INTO user (Nombre, Apellido, Fecha_Nacimiento, Sexo, Mail, DPI, Id_event, Empresa,Time, Date, Cel, Foto  ) VALUES (?,?,?,?,?,?,?,?,now(),now(),?,?)";
/*     */         } else {
/* 164 */           codigoRecienIngresado = this.num;
/* 165 */           query = "UPDATE user SET  Nombre=?, Apellido=?,Fecha_Nacimiento=?,Sexo=?,Mail=?,DPI=?, Id_event = ?, Empresa = ?, Time = now(), Date=now(), Cel = ? , Foto = ? WHERE IdUser = " + this.num;
/*     */         } 
/*     */         
/* 168 */         int resultado = 0;
/*     */         try {
/* 170 */           PreparedStatement ps = null;
/* 171 */           ps = (PreparedStatement)this.connection.prepareStatement(query, 1);
/*     */           
/* 173 */           ps.setString(1, this.view.getNameField().getText());
/*     */           
/* 175 */           ps.setString(2, this.view.getLastNameField().getText());
/*     */           
/* 177 */           ps.setDate(3, getCurrentDate(IndexView.birthCalendar));
/*     */           
/* 179 */           ps.setString(4, this.view.getSexCombo().getSelectedItem().toString());
/*     */           
/* 181 */           ps.setString(5, this.view.getMailField().getText());
/*     */           
/* 183 */           ps.setString(6, this.view.getDpiField().getText());
/*     */           
/* 185 */           ps.setInt(7, ZeroTicketSignUp.codeEvent);
/*     */           
/* 187 */           ps.setString(8, this.view.getCompanyField().getText());
/*     */           
/* 189 */           ps.setString(9, this.view.getPhoneField().getText());
/*     */           
/* 191 */           if (this.goodDPI) {
/*     */             
/* 193 */             if (this.photoInputStream != null) {
/* 194 */               ps.setBinaryStream(10, this.photoInputStream);
/*     */             } else {
/*     */               
/* 197 */               ps.setString(10, null);
/*     */             }
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 203 */             ps.setString(10, null);
/*     */           } 
/*     */ 
/*     */           
/* 207 */           resultado = ps.executeUpdate();
/*     */           
/* 209 */           this.resultSet = ps.getGeneratedKeys();
/* 210 */           while (this.resultSet.next()) {
/* 211 */             codigoRecienIngresado = this.resultSet.getInt(1);
/*     */           }
/* 213 */         } catch (SQLException ex) {
/* 214 */           JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ingresar datos : Nuevo Usuario <br/>" + ex + " </p></pre></body></html>", "Error al ingresar datos a la base de datos", 0);
/*     */         } 
/*     */ 
/*     */         
/* 218 */         DataBaseClass.executeQuery("DELETE FROM tag WHERE UID LIKE '" + this.view.getTagField().getText() + "'");
/* 219 */         DataBaseClass.executeQuery("INSERT INTO tag (UID, User_ID) VALUES ('" + this.view.getTagField().getText() + "'," + codigoRecienIngresado + ")");
/*     */         
/* 221 */         JOptionPane.showMessageDialog(null, "Dato ingresado exitosamente", "Nuevo usuario", 1);
/* 222 */         blankField();
/*     */       } else {
/* 224 */         JOptionPane.showMessageDialog(null, "Los datos obligatorios son : \n- Nombre\n- Apellido\n- Brazalete", "Faltan datos para completar", 2);
/*     */       } 
/* 226 */     } else if (e.getSource() == this.view.getTableMain()) {
/* 227 */       int num = -1;
/* 228 */       int fila = this.view.getTableMain().rowAtPoint(e.getPoint());
/* 229 */       if (fila > -1) {
/* 230 */         Object ob = this.view.getTableMain().getModel().getValueAt(fila, 0);
/* 231 */         num = Integer.parseInt(ob.toString());
/*     */       } 
/* 233 */       if (this.status.equalsIgnoreCase("FindEmployee")) {
/* 234 */         String empresa, mail, dpi, celular, apellido, nombre; this.view.getTabContainer().setSelectedIndex(0);
/*     */         
/* 236 */         Object[] data4 = (new DataBaseClass()).giveData("SELECT  Nombre, Apellido, Sexo, Cel, DPI, Mail, Empresa, Fecha_Nacimiento FROM user WHERE idUser = " + num);
/* 237 */         this.num = num;
/* 238 */         String sexo = null;
/*     */         try {
/* 240 */           nombre = data4[0].toString();
/* 241 */         } catch (Exception ex) {
/* 242 */           nombre = "";
/*     */         } 
/*     */         try {
/* 245 */           apellido = data4[1].toString();
/* 246 */         } catch (Exception ex) {
/* 247 */           apellido = "";
/*     */         } 
/*     */         try {
/* 250 */           sexo = data4[2].toString();
/* 251 */         } catch (Exception exception) {}
/*     */ 
/*     */         
/*     */         try {
/* 255 */           celular = data4[3].toString();
/* 256 */         } catch (Exception ex) {
/* 257 */           celular = "";
/*     */         } 
/*     */         try {
/* 260 */           dpi = data4[4].toString();
/* 261 */         } catch (Exception ex) {
/* 262 */           dpi = "";
/*     */         } 
/*     */         try {
/* 265 */           mail = data4[5].toString();
/* 266 */         } catch (Exception ex) {
/* 267 */           mail = "";
/*     */         } 
/*     */         try {
/* 270 */           empresa = data4[6].toString();
/* 271 */         } catch (Exception ex) {
/* 272 */           empresa = "";
/*     */         } 
/*     */         
/*     */         try {
/* 276 */           IndexView.birthCalendar.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(data4[7].toString()));
/* 277 */         } catch (Exception ex) {
/* 278 */           IndexView.birthCalendar.setDate(Calendar.getInstance().getTime());
/*     */         } 
/*     */         
/* 281 */         this.view.getNameField().setText(nombre);
/* 282 */         this.view.getLastNameField().setText(apellido);
/* 283 */         if (sexo.equalsIgnoreCase("")) {
/* 284 */           sexo = "FEMENINO";
/*     */         }
/*     */         
/* 287 */         this.view.getSexCombo().setSelectedItem(sexo);
/* 288 */         this.view.getPhoneField().setText(celular);
/* 289 */         this.view.getCompanyField().setText(empresa);
/* 290 */         this.view.getMailField().setText(mail);
/* 291 */         this.view.getDpiField().setText(dpi);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */   
/*     */   private void blankField() {
/* 314 */     this.view.getNameField().setText("");
/* 315 */     this.view.getLastNameField().setText("");
/* 316 */     this.view.getMailField().setText("");
/* 317 */     this.view.getDpiField().setText("");
/* 318 */     this.view.getTagField().setText("");
/* 319 */     this.view.getCompanyField().setText("");
/* 320 */     this.view.getPhoneField().setText("");
/* 321 */     this.view.getSexCombo().setSelectedIndex(0);
/*     */ 
/*     */     
/* 324 */     IndexView.birthCalendar.setDate(Calendar.getInstance().getTime());
/* 325 */     this.num = -1;
/* 326 */     this.goodDPI = false;
/* 327 */     this.photoInputStream = null;
/* 328 */     this.view.getFindField().setText("");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 334 */     if (e.getSource() == this.view.getFindField()) {
/* 335 */       this.status = "FindEmployee";
/* 336 */       (new LoadDataFromDataBase("Empleado")).execute();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnWidth(JTable table) {
/* 343 */     table.setAutoResizeMode(0);
/* 344 */     for (int j = 0; j < table.getColumnCount(); j++) {
/* 345 */       table.getColumnModel().getColumn(j).setPreferredWidth(this.modelTable.getColumnWidth(j));
/*     */     }
/*     */   }
/*     */   
/*     */   public void updateUsersTable() {
/* 350 */     if (this.status.equalsIgnoreCase("FindEmployee")) {
/* 351 */       String[] parameters; this.errorQuery = "DELETE FROM user WHERE idUser= ";
/*     */       
/* 353 */       String findTextField = this.view.getFindField().getText();
/*     */ 
/*     */       
/* 356 */       if (!findTextField.equalsIgnoreCase("")) {
/* 357 */         String findParameter = " Id_Event = " + ZeroTicketSignUp.codeEvent + " AND ((CONCAT(Nombre,' ',Apellido)) LIKE '%" + findTextField + "%' OR  user.Nombre  LIKE '%" + findTextField + "%' OR  user.Apellido  LIKE '%" + findTextField + "%' OR  CAST(Fecha_Nacimiento AS CHAR)  LIKE '%" + findTextField + "%' OR  Sexo  LIKE '%" + findTextField + "%' OR Mail LIKE '%" + findTextField + "%' OR  Cel LIKE '%" + findTextField + "%' OR  DPI  LIKE '%" + findTextField + "%') ORDER BY Nombre, Apellido ";
/*     */         
/* 359 */         parameters = new String[] { " WHERE " + findParameter };
/*     */       } else {
/* 361 */         parameters = new String[] { "WHERE Id_Event = " + ZeroTicketSignUp.codeEvent + " ORDER BY nombre, apellido" };
/*     */       } 
/*     */       
/* 364 */       this.modelTable = new TablesModel("Users", parameters);
/* 365 */       this.view.getTableMain().setModel((TableModel) this.modelTable);
/* 366 */       this.view.getTableMain().setDefaultRenderer(JLabel.class, new PhotoCell());
/* 367 */       setColumnWidth(this.view.getTableMain());
/*     */ 
/*     */       
/* 370 */       this.view.getCountRows().setText("" + this.view.getTableMain().getRowCount());
/*     */     } else {
/* 372 */       this.errorQuery = "DELETE FROM Employee WHERE idEmployee= ";
/*     */       
/* 374 */       String[] parameters = { " ORDER BY nombre, apellido" };
/* 375 */       this.modelTable = new TablesModel(this.status, parameters);
/*     */       
/* 377 */       this.view.getTableMain().setModel((TableModel) this.modelTable);
/* 378 */       this.view.getTableMain().setDefaultRenderer(JLabel.class, new PhotoCell());
/* 379 */       setColumnWidth(this.view.getTableMain());
/*     */     } 
/*     */   }
/*     */   
/*     */   public class LoadDataFromDataBase
/*     */     extends SwingWorker {
/*     */     private LoadView load;
/*     */     private String tipo;
/*     */     private int codigo;
/*     */     private IndexController ic;
/*     */     
/*     */     public LoadDataFromDataBase(String tipo, int codigo, IndexController ic) {
/* 391 */       this.load = new LoadView(null, true);
/* 392 */       this.tipo = tipo;
/* 393 */       this.codigo = codigo;
/* 394 */       this.ic = ic;
/*     */     }
/*     */     
/*     */     public LoadDataFromDataBase(String tipo) {
/* 398 */       this.load = new LoadView(null, true);
/* 399 */       this.tipo = tipo;
/*     */     }
/*     */     
/*     */     public String doInBackground() {
/* 403 */       show();
/*     */       
/* 405 */       if (this.tipo.equalsIgnoreCase("Empleado")) {
/* 406 */         IndexController.this.updateUsersTable();
/*     */       }
/* 408 */       return "";
/*     */     }
/*     */ 
/*     */     
/* 412 */     protected void done() { hide(); }
/*     */ 
/*     */ 
/*     */     
/*     */     public void show() {
/* 417 */       SwingUtilities.invokeLater(new Runnable() {
/*     */             public void run() {
/* 419 */               IndexController.LoadDataFromDataBase.this.load.setLocationRelativeTo(null);
/* 420 */               IndexController.LoadDataFromDataBase.this.load.setVisible(true);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/*     */     public void hide() {
/* 426 */       SwingUtilities.invokeLater(new Runnable()
/*     */           {
/* 428 */             public void run() { IndexController.LoadDataFromDataBase.this.load.setVisible(false); }
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public class ReadDPI
/*     */     implements Runnable
/*     */   {
/*     */     public ReadDPI() {
/* 437 */       Thread t = new Thread(this);
/* 438 */       t.setDaemon(true);
/* 439 */       t.start();
/*     */     }
/*     */     
/*     */     public void run() {
/* 443 */       synchronized (this) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 448 */         SmartCardTerminal.connectDPIReader();
/*     */         
/* 450 */         DPIReader ccr = new DPIReader();
/*     */         
/* 452 */         CardTerminal terminal = null;
/*     */         
/* 454 */         if (SmartCardTerminal.getTerminal() != null) {
/* 455 */           terminal = SmartCardTerminal.getTerminal();
/*     */ 
/*     */           
/*     */           try {
/* 459 */             ccr.doCardReaderCommunication(terminal);
/*     */ 
/*     */ 
/*     */             
/* 463 */             IndexController.this.info = ccr.getDPI();
/* 464 */             if (IndexController.this.info != null)
/*     */             {
/* 466 */               IndexController.this.view.getDpiField().setText(IndexController.this.info.getDpi());
/* 467 */               IndexController.this.view.getNameField().setText("" + IndexController.this.info.getPrimerNombre().replace("  ", "") + " " + IndexController.this.info.getSegundoNombre().replace("  ", ""));
/* 468 */               IndexController.this.view.getLastNameField().setText("" + IndexController.this.info.getPrimerApellido().replace("  ", "") + " " + IndexController.this.info.getSegundoApellido().replace("  ", ""));
/* 469 */               IndexController.this.view.getSexCombo().setSelectedItem(IndexController.this.info.getSexo().replace(" ", "").toUpperCase());
/*     */               try {
/* 471 */                 java.util.Date dateTMP = (new SimpleDateFormat("yyyy-MM-dd")).parse(DPIUtils.transformDate(IndexController.this.info.getNacimiento()));
/* 472 */
 IndexView.birthCalendar.setDate(dateTMP);
/* 473 */               } catch (ParseException ex) {
/* 474 */                 JOptionPane.showMessageDialog(null, "Error en configurar fecha en DPI \nConfigurelo manualmente ", "Error en fecha", 0);
/*     */               } 
/*     */ 
/*     */               
/* 478 */               SmartCardDPIReader sd = new SmartCardDPIReader(terminal);
/*     */ 
/*     */               
/*     */               try {
/* 482 */                 IndexController.this.photoInputStream = ImageUtils.byteToInputStream(sd.readPhoto());
/*     */               }
/* 484 */               catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */               
/* 488 */               IndexController.this.goodDPI = true;
/*     */             }
/*     */           
/*     */           }
/* 492 */           catch (DPIParseException ex) {
/*     */             
/* 494 */             JOptionPane.showMessageDialog(null, "<html><body><p style='width: 300px;'> Descripción del error : <br/>" + ex + "</p></body></html>", "Error en leer el dpi", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 500 */             IndexController.this.goodDPI = false;
/* 501 */           } catch (UnsupportedEncodingException ex) {
/*     */             
/* 503 */             IndexController.this.goodDPI = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class ReadTag
/*     */     implements Runnable
/*     */   {
/*     */     public ReadTag() {
/* 514 */       Thread t = new Thread(this);
/* 515 */       t.setDaemon(true);
/* 516 */       t.start();
/*     */     }
/*     */     
/*     */     public void run() {
/* 520 */       synchronized (this) {
/*     */         
/* 522 */         SmartCardTerminal.connectNFCReader();
/*     */         
/* 524 */         CardTerminal terminal = null;
/* 525 */         if (SmartCardTerminal.getTerminal() != null) {
/* 526 */           terminal = SmartCardTerminal.getTerminal();
/*     */           try {
/* 528 */             String[] data = ReaderUtils.readUID(terminal);
/*     */             
/* 530 */             if (data[2].equalsIgnoreCase("9000")) {
/* 531 */               IndexController.this.uid = data[0].toUpperCase();
/*     */               
/* 533 */               if (!IndexController.this.uid.equalsIgnoreCase(""))
/*     */               {
/*     */                 
/* 536 */                 IndexController.this.setData(IndexController.this.uid);
/* 537 */                 IndexController.this.view.getTagField().setText(IndexController.this.uid);
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 543 */           catch (Exception exception) {}
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(String uid) {
/* 554 */     int codeTag = -1;
/* 555 */     int userCode = -1;
/*     */     
/* 557 */     Object[] data = (new DataBaseClass()).giveData("SELECT  idTag, User_ID FROM tag WHERE UID LIKE '" + uid + "'");
/*     */     
/*     */     try {
/* 560 */       codeTag = Integer.parseInt(data[0].toString());
/*     */     }
/* 562 */     catch (Exception er) {
/* 563 */       codeTag = -1;
/*     */     } 
/*     */     
/*     */     try {
/* 567 */       userCode = Integer.parseInt(data[1].toString());
/*     */     }
/* 569 */     catch (Exception er) {
/* 570 */       userCode = -1;
/*     */     } 
/*     */ 
/*     */     
/* 574 */     if (codeTag != -1 && userCode != -1) {
/*     */       String mail, dpi, sexo, apellido, nombre;
/* 576 */       Object[] data4 = (new DataBaseClass()).giveData("SELECT  Nombre, Apellido, Sexo, Categoria, DPI, Mail, Region, Fecha_Nacimiento FROM user WHERE idUser = " + userCode);
/* 577 */       this.num = userCode;
/*     */       
/*     */       try {
/* 580 */         nombre = data4[0].toString();
/* 581 */       } catch (Exception e) {
/* 582 */         nombre = "";
/*     */       } 
/*     */       try {
/* 585 */         apellido = data4[1].toString();
/* 586 */       } catch (Exception e) {
/* 587 */         apellido = "";
/*     */       } 
/*     */       try {
/* 590 */         sexo = data4[2].toString();
/* 591 */       } catch (Exception e) {
/* 592 */         sexo = "FEMENINO";
/*     */       } 
/*     */       try {
/* 595 */         String categoria = data4[3].toString();
/* 596 */       } catch (Exception e) {
/* 597 */         String categoria = "";
/*     */       } 
/*     */       try {
/* 600 */         dpi = data4[4].toString();
/* 601 */       } catch (Exception e) {
/* 602 */         dpi = "";
/*     */       } 
/*     */       try {
/* 605 */         mail = data4[5].toString();
/* 606 */       } catch (Exception e) {
/* 607 */         mail = "";
/*     */       } 
/*     */       try {
/* 610 */         String region = data4[6].toString();
/* 611 */       } catch (Exception e) {
/* 612 */         String region = "";
/*     */       } 
/*     */       
/*     */       try {
/* 616 */         IndexView.birthCalendar.setDate((new SimpleDateFormat("yyyy-MM-dd")).parse(data4[7].toString()));
/* 617 */       } catch (Exception ex) {
/* 618 */         IndexView.birthCalendar.setDate(Calendar.getInstance().getTime());
/*     */       } 
/*     */       
/* 621 */       this.view.getNameField().setText(nombre);
/* 622 */       this.view.getLastNameField().setText(apellido);
/* 623 */       this.view.getSexCombo().setSelectedItem(sexo);
/*     */ 
/*     */       
/* 626 */       this.view.getMailField().setText(mail);
/* 627 */       this.view.getDpiField().setText(dpi);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/IndexController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */