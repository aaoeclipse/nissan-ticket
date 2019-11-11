/*     */ package zeroticketsignup.Controller;
import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.ResultSetMetaData;
/*     */ import com.mysql.jdbc.Statement;
/*     */ import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.sql.Date;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
import java.util.Calendar;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
        import javax.swing.table.AbstractTableModel;
/*     */ import zeroticketsignup.ZeroTicketSignUp;
/*     */ 
/*     */ public class TablesModel extends AbstractTableModel {
/*     */   private Object[][] data;
/*     */   private String[] columnNames;
/*     */   private String consulta;
/*     */   private String type;
/*     */   private int numfilas;
/*     */   
/*     */   public TablesModel(String kind) {
/*  29 */     this.data = (Object[][])null;
/*     */     
/*  31 */     this.consulta = "";
/*  32 */     this.type = "";
/*     */ 
/*     */ 
/*     */     
/*  36 */     this.st = DataBaseClass.getSt();
/*  37 */     this.connection = DataBaseClass.getConnection();
/*  38 */     this.resultSet = DataBaseClass.getResultSet();
/*  39 */     this.metaData = DataBaseClass.getMetaData();
/*     */ 
/*     */ 
/*     */     
/*  43 */     this.type = kind;
/*  44 */     setColumnNames(this.type);
/*  45 */     fillTableData(this.type);
/*     */   }
/*     */   private int numcolumn; private String[] parameters; private Statement st; private Connection connection; private ResultSet resultSet; private ResultSetMetaData metaData;
/*     */   
/*     */   public void fillTableData(String kind) {
/*     */     try {
/*  51 */       this.st = (Statement)this.connection.createStatement();
/*  52 */       this.resultSet = this.st.executeQuery(this.consulta);
/*  53 */       this.metaData = (ResultSetMetaData)this.resultSet.getMetaData();
/*     */       
/*  55 */       this.resultSet.last();
/*  56 */       this.numfilas = this.resultSet.getRow();
/*     */       
/*  58 */       this.data = new Object[this.columnNames.length][this.numfilas];
/*  59 */       this.resultSet.beforeFirst();
/*  60 */       int j = 0;
/*  61 */       while (this.resultSet.next()) {
/*  62 */         for (int i = 0; i < this.columnNames.length; i++) {
/*  63 */           if (this.type.equalsIgnoreCase("Users")) {
/*     */             
/*  65 */             this.data[i][j] = this.resultSet.getObject(i + 1);
/*     */           }
/*  67 */           else if (this.type.equalsIgnoreCase("Reportes")) {
/*  68 */             if (i == 1) {
/*  69 */               JLabel labelnew = new JLabel();
/*  70 */               ImageIcon imagef = null;
/*     */               
/*  72 */               File fileFoto = null;
/*  73 */               boolean fileExist = true;
/*     */               try {
/*  75 */                 fileFoto = new File(ZeroTicketSignUp.getPathJar() + File.separator + "Attendance" + File.separator + this.resultSet.getString(10));
/*  76 */                 imagef = new ImageIcon(fileFoto.getAbsolutePath());
/*  77 */                 fileExist = fileFoto.exists();
/*  78 */                 imagef.getImage();
/*  79 */               } catch (Exception e) {
/*  80 */                 fileExist = false;
/*     */               } 
/*     */ 
/*     */               
/*  84 */               if (!fileExist) {
/*  85 */                 System.err.println("Cargando bytes de foto . . .");
/*     */                 
/*  87 */                 int codigoAssist = this.resultSet.getInt(1);
/*     */                 
/*  89 */                 byte[] bits = null;
/*     */                 
/*  91 */                 ResultSet resultSet2 = DataBaseClass.getResultSet();
/*  92 */                 Statement st2 = DataBaseClass.getSt();
/*     */                 try {
/*  94 */                   st2 = (Statement)this.connection.createStatement();
/*  95 */                   resultSet2 = st2.executeQuery("SELECT Foto FROM Assist WHERE idAssist =" + codigoAssist);
/*     */                   
/*  97 */                   while (resultSet2.next()) {
/*  98 */                     bits = resultSet2.getBytes(1);
/*     */                   }
/* 100 */                 } catch (SQLException ex) {
/* 101 */                   JOptionPane.showMessageDialog(null, "No se pudo obtener los bits de la foto. \n" + ex, "Bits de Foto : " + codigoAssist, 0);
/*     */                 } 
/* 103 */                 if (bits != null) {
/* 104 */                   imagef = new ImageIcon(bits);
/*     */                   
/* 106 */                   DataBaseClass.executeQuery("UPDATE Assist SET Foto_Path = '" + savePhotoInFile(codigoAssist, bits, "Asistencia") + "' WHERE Assist.idAssist = " + codigoAssist);
/*     */                 } else {
/* 108 */                   imagef = new ImageIcon(getClass().getResource("/zeroattendance/ImagesGUI/user.png"));
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 113 */               ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(200, 200, 4));
/* 114 */               labelnew.setIcon(imaescala);
/*     */               
/* 116 */               this.data[i][j] = labelnew;
/* 117 */             } else if (i == 7) {
/* 118 */               JLabel labelnew = new JLabel();
/* 119 */               ImageIcon imagef = null;
/* 120 */               File fileFoto = null;
/* 121 */               boolean fileExist = true;
/*     */               try {
/* 123 */                 fileFoto = new File(ZeroTicketSignUp.getPathJar() + File.separator + "Users" + File.separator + this.resultSet.getString(9));
/* 124 */                 imagef = new ImageIcon(fileFoto.getAbsolutePath());
/* 125 */                 fileExist = fileFoto.exists();
/* 126 */                 imagef.getImage();
/* 127 */               } catch (Exception e) {
/* 128 */                 fileExist = false;
/*     */               } 
/* 130 */               if (!fileExist) {
/* 131 */                 System.err.println("Cargando bytes de foto . . .");
/*     */                 
/* 133 */                 int codigoEmployee = this.resultSet.getInt(11);
/*     */                 
/* 135 */                 byte[] bits = null;
/*     */                 
/* 137 */                 ResultSet resultSet2 = DataBaseClass.getResultSet();
/* 138 */                 Statement st2 = DataBaseClass.getSt();
/*     */                 try {
/* 140 */                   st2 = (Statement)this.connection.createStatement();
/* 141 */                   resultSet2 = st2.executeQuery("SELECT Foto FROM Employee WHERE idEmployee =" + codigoEmployee);
/* 142 */                   while (resultSet2.next()) {
/* 143 */                     bits = resultSet2.getBytes(1);
/*     */                   }
/* 145 */                 } catch (SQLException ex) {
/* 146 */                   JOptionPane.showMessageDialog(null, "No se pudo obtener los bits de la foto. \n" + ex, "Bits de Foto : " + codigoEmployee, 0);
/*     */                 } 
/* 148 */                 if (bits != null) {
/* 149 */                   imagef = new ImageIcon(bits);
/* 150 */                   DataBaseClass.executeQuery("UPDATE Employee SET Foto_Path = '" + savePhotoInFile(codigoEmployee, bits, "Empleado") + "' WHERE idEmployee = " + codigoEmployee);
/*     */                 } else {
/* 152 */                   imagef = new ImageIcon(getClass().getResource("/zeroattendance/ImagesGUI/user.png"));
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 157 */               ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(200, 200, 4));
/* 158 */               labelnew.setIcon(imaescala);
/* 159 */               this.data[i][j] = labelnew;
/*     */             } else {
/* 161 */               this.data[i][j] = this.resultSet.getObject(i + 1);
/*     */             } 
/* 163 */           } else if (this.type.equalsIgnoreCase("ReporteResumen")) {
/* 164 */             if (i == 1) {
/* 165 */               JLabel labelnew = new JLabel();
/* 166 */               ImageIcon imagef = null;
/* 167 */               File fileFoto = null;
/* 168 */               boolean fileExist = true;
/*     */               try {
/* 170 */                 fileFoto = new File(ZeroTicketSignUp.getPathJar() + File.separator + "Users" + File.separator + this.resultSet.getString(5));
/* 171 */                 imagef = new ImageIcon(fileFoto.getAbsolutePath());
/* 172 */                 fileExist = fileFoto.exists();
/* 173 */                 imagef.getImage();
/* 174 */               } catch (Exception e) {
/* 175 */                 fileExist = false;
/*     */               } 
/* 177 */               if (!fileExist) {
/* 178 */                 System.err.println("Cargando bytes de foto . . .");
/* 179 */                 int codigoEmployee = this.resultSet.getInt(6);
/*     */                 
/* 181 */                 byte[] bits = null;
/*     */                 
/* 183 */                 ResultSet resultSet2 = DataBaseClass.getResultSet();
/* 184 */                 Statement st2 = DataBaseClass.getSt();
/*     */                 try {
/* 186 */                   st2 = (Statement)this.connection.createStatement();
/* 187 */                   resultSet2 = st2.executeQuery("SELECT Foto FROM Employee WHERE idEmployee =" + codigoEmployee);
/* 188 */                   while (resultSet2.next()) {
/* 189 */                     bits = resultSet2.getBytes(1);
/*     */                   }
/* 191 */                 } catch (SQLException ex) {
/* 192 */                   JOptionPane.showMessageDialog(null, "No se pudo obtener los bits de la foto. \n" + ex, "Bits de Foto : " + codigoEmployee, 0);
/*     */                 } 
/* 194 */                 if (bits != null) {
/* 195 */                   imagef = new ImageIcon(bits);
/* 196 */                   DataBaseClass.executeQuery("UPDATE Employee SET Foto_Path = '" + savePhotoInFile(codigoEmployee, bits, "Empleado") + "' WHERE idEmployee = " + codigoEmployee);
/*     */                 } else {
/* 198 */                   imagef = new ImageIcon(getClass().getResource("/zeroattendance/ImagesGUI/user.png"));
/*     */                 } 
/*     */               } 
/* 201 */               ImageIcon imaescala = new ImageIcon(imagef.getImage().getScaledInstance(200, 200, 4));
/* 202 */               labelnew.setIcon(imaescala);
/*     */               
/* 204 */               this.data[i][j] = labelnew;
/* 205 */             } else if (i == 2) {
/*     */               Object last; String name;
/*     */               try {
/* 208 */                 name = this.resultSet.getString(3);
/* 209 */               } catch (Exception ex) {
/* 210 */                 name = "";
/*     */               } 
/*     */               
/*     */               try {
/* 214 */                 last = this.resultSet.getString(4);
/* 215 */               } catch (Exception ex) {
/* 216 */                 last = "";
/*     */               } 
/*     */               
/* 219 */               this.data[i][j] = name + " " + last;
/* 220 */             } else if (i != 3) {
/* 221 */               this.data[i][j] = this.resultSet.getObject(i + 1);
/*     */             } 
/*     */           } else {
/*     */             
/* 225 */             this.data[i][j] = this.resultSet.getObject(i + 1);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 231 */         j++;
/*     */       } 
/* 233 */     } catch (SQLException ex) {
/* 234 */       JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al cargar datos a la tabla : " + kind + " </br> " + ex + " </br>" + this.consulta + "</p></pre></body></html> ", "Error en base de datos", 0);
/* 235 */       System.out.println(this.consulta);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String savePhotoInFile(int codigoEmployee, byte[] bits, String tipo) {
/* 241 */     File fileSave = null;
/* 242 */     String pathSave = "";
/* 243 */     InputStream in = new ByteArrayInputStream(bits);
/* 244 */     BufferedImage buff = null;
/*     */     try {
/* 246 */       buff = ImageIO.read(in);
/* 247 */     } catch (IOException ex) {
/* 248 */       Logger.getLogger(TablesModel.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/*     */     
/* 251 */     if (tipo.equalsIgnoreCase("Empleado")) {
/* 252 */       fileSave = new File(ZeroTicketSignUp.getPathJar() + File.separator + "Users" + File.separator + codigoEmployee + ".jpg");
/* 253 */       pathSave = codigoEmployee + ".jpg";
/* 254 */     } else if (tipo.equalsIgnoreCase("Asistencia")) {
/* 255 */       String uniqueName = ZeroTicketSignUp.getPathJar() + File.separator + "Attendance" + File.separator + getTimeNow() + "_employee" + codigoEmployee + ".jpg";
/* 256 */       fileSave = new File(uniqueName);
/* 257 */       pathSave = getTimeNow() + "_employee" + codigoEmployee + ".jpg";
/*     */     } 
/*     */     try {
/* 260 */       ImageIO.write(buff, "jpg", fileSave);
/* 261 */     } catch (Exception ex) {
/* 262 */       Logger.getLogger(TablesModel.class.getName()).log(Level.SEVERE, null, ex);
/*     */     } 
/*     */     
/* 265 */     return pathSave;
/*     */   }
/*     */   
/*     */   public String getTimeNow()
/*     */   {
/* 270 */     java.util.Date date = Calendar.getInstance().getTime();
/* 271 */
 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
/* 272 */     return sdf.format(date); } public TablesModel(String kind, String[] parameters) { this.data = (Object[][])null; this.consulta = ""; this.type = "";
/*     */     this.st = DataBaseClass.getSt();
/*     */     this.connection = DataBaseClass.getConnection();
/*     */     this.resultSet = DataBaseClass.getResultSet();
/*     */     this.metaData = DataBaseClass.getMetaData();
/* 277 */     this.type = kind;
/* 278 */     setColumnNames(this.type);
/* 279 */     this.consulta += parameters[0];
/* 280 */     fillTableData(this.type); }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnNames(String kind) {
/* 285 */     if (kind.equalsIgnoreCase("Users")) {
/* 286 */       this.columnNames = new String[] { "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Fecha de </td></tr><tr><td align=\"center\"> Nacimiento  </td> </tr></table></center></html>", "DPI", "Sexo", "<html><center><table><tr><td align=\"center\" > Correo </td></tr><tr><td align=\"center\"> Electrónico  </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Número de </td></tr><tr><td align=\"center\"> Celular  </td> </tr></table></center></html>" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       this.consulta = "SELECT  idUser, Nombre, Apellido, Fecha_Nacimiento, DPI, Sexo, Mail, Cel FROM user ";
/* 296 */     } else if (kind.equalsIgnoreCase("Reportes")) {
/* 297 */       this.columnNames = new String[] { "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Asistencia </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Foto </td></tr><tr><td align=\"center\"> Asistencia </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Nombre </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Apellido </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Entrada </td></tr><tr><td align=\"center\"> Salida </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Fecha de </td></tr><tr><td align=\"center\"> Asistencia  </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Fecha y hora </td></tr><tr><td align=\"center\"> Asistencia  </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Foto </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>" };
/*     */       
/* 299 */       this.consulta = "SELECT  idAssist, idAssist AS Foto ,  Nombre, Apellido,Tipo , Date_Assist, Time_Assist, idAssist AS Foto,Employee.Foto_Path, Assist.Foto_Path, Employee_ID FROM Assist INNER JOIN Employee ON idEmployee=Employee_ID ";
/* 300 */     } else if (kind.equalsIgnoreCase("ReporteResumen")) {
/* 301 */       this.columnNames = new String[] { "<html><center><table><tr><td align=\"center\" > Codigo </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Foto </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Nombre Completo </td></tr><tr><td align=\"center\"> Usuario </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > Horas Netas </td></tr><tr><td align=\"center\"> Trabajadas </td> </tr></table></center></html>" };
/*     */       
/* 303 */       this.consulta = "SELECT  DISTINCT idEmployee, idEmployee AS Foto ,  Nombre, Apellido ,Employee.Foto_Path, Employee_ID FROM Assist INNER JOIN Employee ON idEmployee=Employee_ID ";
/*     */     } else {
/* 305 */       this.columnNames = new String[] { "<html><center><table><tr><td align=\"center\" > NO </td></tr><tr><td align=\"center\"> INFO </td> </tr></table></center></html>", "<html><center><table><tr><td align=\"center\" > NO </td></tr><tr><td align=\"center\"> DATA </td> </tr></table></center></html>" };
/*     */       
/* 307 */       this.consulta = "SELECT idEmployee FROM Employee WHERE idEmployee = -1 ";
/*     */     } 
/*     */     
/* 310 */     this.numcolumn = this.columnNames.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public Class getColumnClass(int col) {
/* 315 */     if (this.type.equals("permiso modulos")) {
/* 316 */       if (col == 3) {
/* 317 */         return Boolean.class;
/*     */       }
/* 319 */       if (col == 0) {
/* 320 */         return Integer.class;
/*     */       }
/* 322 */       return String.class;
/*     */     } 
/* 324 */     if (this.type.equalsIgnoreCase("Users")) {
/*     */       
/* 326 */       if (col == 0) {
/* 327 */         return Integer.class;
/*     */       }
/* 329 */       if (col == 3) {
/* 330 */         return Date.class;
/*     */       }
/*     */       
/* 333 */       return String.class;
/*     */     } 
/* 335 */     if (this.type.equalsIgnoreCase("Reportes")) {
/* 336 */       if (col == 1 || col == 7) {
/* 337 */         return JLabel.class;
/*     */       }
/* 339 */       if (col == 0) {
/* 340 */         return Integer.class;
/*     */       }
/* 342 */       if (col == 5) {
/* 343 */         return Date.class;
/*     */       }
/*     */       
/* 346 */       return String.class;
/*     */     } 
/* 348 */     if (this.type.equalsIgnoreCase("ReporteResumen")) {
/* 349 */       if (col == 1) {
/* 350 */         return JLabel.class;
/*     */       }
/* 352 */       if (col == 0) {
/* 353 */         return Integer.class;
/*     */       }
/*     */       
/* 356 */       return String.class;
/*     */     } 
/*     */     
/* 359 */     return String.class;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColumnWidth(int col) {
/* 364 */     if (this.type.equalsIgnoreCase("Ingreso de Otros Gastos")) {
/* 365 */       if (col == 0) {
/* 366 */         return 86;
/*     */       }
/* 368 */       if (col == 1) {
/* 369 */         return 155;
/*     */       }
/* 371 */       if (col == 2) {
/* 372 */         return 152;
/*     */       }
/* 374 */       if (col == 3) {
/* 375 */         return 270;
/*     */       }
/* 377 */       if (col == 4) {
/* 378 */         return 119;
/*     */       }
/* 380 */       if (col == 5) {
/* 381 */         return 155;
/*     */       }
/* 383 */       return 0;
/*     */     } 
/* 385 */     if (this.type.equalsIgnoreCase("ReporteResumen")) {
/* 386 */       if (col == 0) {
/* 387 */         return 70;
/*     */       }
/* 389 */       return 200;
/*     */     } 
/*     */     
/* 392 */     return 200;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 397 */   public String getColumnName(int col) { return this.columnNames[col]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCellEditable(int row, int col) {
/* 402 */     if (this.type.equalsIgnoreCase("permiso modulos")) {
/* 403 */       if (col == 3) {
/* 404 */         return true;
/*     */       }
/* 406 */       return false;
/*     */     } 
/*     */     
/* 409 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 414 */   public int getRowCount() { return this.numfilas; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 419 */   public int getColumnCount() { return this.numcolumn; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 424 */   public Object getValueAt(int row, int col) { return this.data[col][row]; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueAt(Object value, int row, int col) {
/* 429 */     this.data[col][row] = value;
/* 430 */     fireTableCellUpdated(row, col);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 435 */   public void setType(String type) { this.type = type; }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/TablesModel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */