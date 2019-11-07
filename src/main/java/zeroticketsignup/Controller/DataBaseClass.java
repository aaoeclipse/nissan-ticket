/*     */ package zeroticketsignup.Controller;
/*     */ 
/*     */ import com.mysql.jdbc.Connection;
/*     */ import com.mysql.jdbc.ResultSetMetaData;
/*     */ import com.mysql.jdbc.Statement;
/*     */ import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ public class DataBaseClass
/*     */ {
/*  13 */   protected static MysqlDataSource dataSource = new MysqlDataSource();
/*  14 */   protected static Connection connection = null;
/*     */   protected static Statement st;
/*     */   protected static ResultSet resultSet;
/*     */   protected static ResultSetMetaData metaData;
/*     */   
/*     */   public static boolean connect(String[] datos) {
/*     */     try {
/*  21 */       dataSource.setServerName(datos[0]);
/*  22 */       dataSource.setUser(datos[1]);
/*  23 */       dataSource.setPassword(datos[2]);
/*  24 */       dataSource.setDatabaseName(datos[3]);
/*  25 */       dataSource.setAutoReconnect(true);
/*  26 */       connection = (Connection)dataSource.getConnection();
/*  27 */       return true;
/*  28 */     } catch (SQLException ex) {
/*  29 */       Logs.printLog("Error en al connectarse en Base de Datos ", ex + "\nHOST : " + datos[0] + "\nUSER : " + datos[1] + "\nPSW  : " + datos[2] + "\nDATABASE : " + datos[3]);
/*     */ 
/*     */       
/*  32 */       return false;
/*     */     } 
/*     */   }
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
/*     */   public static void executeQuery(String consulta) {
/*     */     try {
/*  58 */       st = (Statement)connection.createStatement();
/*     */       
/*  60 */       st.executeUpdate(consulta);
/*  61 */     } catch (SQLException ex) {
/*  62 */       JOptionPane.showMessageDialog(null, "<html><body><pre><p style='width: 600px;'>Error al ingresar datos : <br/>" + ex + " </p></pre></body></html>", "Error al ingresar datos a la base de datos", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object[] giveData(String consulta) {
/*  68 */     Object[] dataOut = null;
/*     */     try {
/*  70 */       st = (Statement)connection.createStatement();
/*  71 */       resultSet = st.executeQuery(consulta);
/*  72 */       metaData = (ResultSetMetaData)resultSet.getMetaData();
/*     */       
/*  74 */       int numcolumn = metaData.getColumnCount();
/*  75 */       dataOut = new Object[numcolumn];
/*  76 */       int numFilas = 0;
/*  77 */       while (resultSet.next()) {
/*  78 */         for (int i = 0; i < numcolumn; i++) {
/*  79 */           if (resultSet.getObject(i + 1) != null) {
/*  80 */             dataOut[i] = resultSet.getObject(i + 1);
/*     */           } else {
/*  82 */             dataOut[i] = "";
/*     */           } 
/*     */         } 
/*  85 */         numFilas++;
/*     */       } 
/*     */       
/*  88 */       if (numFilas < 0) {
/*  89 */         return null;
/*     */       }
/*  91 */       return dataOut;
/*  92 */     } catch (SQLException ex) {
/*  93 */       Logs.printLog("Error al obtener datos en metodo DataBaseClass/giveData", " " + ex);
/*     */ 
/*     */       
/*  96 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object[][] giveMultipleData(String consulta) {
/* 101 */     Object[][] data = (Object[][])null;
/*     */     
/* 103 */     int numfilas = -1;
/* 104 */     int numcol = -1;
/*     */     try {
/* 106 */       st = (Statement)connection.createStatement();
/* 107 */       resultSet = st.executeQuery(consulta);
/* 108 */       metaData = (ResultSetMetaData)resultSet.getMetaData();
/*     */       
/* 110 */       resultSet.last();
/* 111 */       numfilas = resultSet.getRow();
/* 112 */       numcol = metaData.getColumnCount();
/* 113 */       data = new Object[numfilas][numcol];
/* 114 */       resultSet.beforeFirst();
/* 115 */       int j = 0;
/*     */       
/* 117 */       while (resultSet.next()) {
/* 118 */         for (int i = 0; i < numcol; i++) {
/* 119 */           data[j][i] = resultSet.getObject(i + 1);
/*     */         }
/*     */         
/* 122 */         j++;
/*     */       } 
/* 124 */     } catch (SQLException ex) {
/* 125 */       JOptionPane.showMessageDialog(null, "Error al cargar datos : \n" + ex, "Error en base de datos", 0);
/*     */     } 
/*     */     
/* 128 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public static MysqlDataSource getDataSource() { return dataSource; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public static Connection getConnection() { return connection; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static Statement getSt() { return st; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public static ResultSet getResultSet() { return resultSet; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public static ResultSetMetaData getMetaData() { return metaData; }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/DataBaseClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */