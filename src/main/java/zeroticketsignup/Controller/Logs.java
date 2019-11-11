/*    */ package zeroticketsignup.Controller;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileWriter;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Calendar;
/*    */ import java.util.Date;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import zeroticketsignup.ZeroTicketSignUp;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Logs
/*    */ {
/*    */   public static void printLog(String title, String msj) {
/* 21 */     FileWriter fichero = null;
/*    */     
/*    */     try {
/* 24 */       fichero = new FileWriter(ZeroTicketSignUp.getPathJar() + File.separator + "Logs" + File.separator + getTimeNow() + ".txt", true);
/*    */       
/* 26 */       PrintWriter pw = new PrintWriter(fichero);
/* 27 */       pw.println();
/* 28 */       pw.println();
/* 29 */       pw.println("****************************************************");
/* 30 */       pw.println("Título : " + title);
/* 31 */       pw.println();
/* 32 */       pw.println("Mensaje : " + msj);
/* 33 */       pw.println();
/* 34 */       pw.println("Hora : " + getTimeStamp());
/* 35 */       pw.println("****************************************************");
/*    */     }
/* 37 */     catch (IOException ex) {
/* 38 */       Logger.getLogger(Logs.class.getName()).log(Level.SEVERE, null, ex);
/*    */     } 
/*    */     
/*    */     try {
/* 42 */       if (null != fichero) {
/* 43 */         fichero.close();
/*    */       }
/* 45 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getTimeNow() {
/* 51 */     Date date = Calendar.getInstance().getTime();
/* 52 */     SimpleDateFormat sdf = new SimpleDateFormat("EEEEEEE_dd_MMM_yyyy");
/* 53 */     return sdf.format(date);
/*    */   }
/*    */   
/*    */   public static String getTimeStamp() {
/* 57 */     Date date = Calendar.getInstance().getTime();
/* 58 */     SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");
/* 59 */     return sdf.format(date);
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/Logs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */