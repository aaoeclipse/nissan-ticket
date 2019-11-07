/*    */ package zeroticketsignup.Controller;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileReader;
/*    */ 
/*    */ 
/*    */ public class ConfigClass
/*    */   extends DataBaseClass
/*    */ {
/* 11 */   private static File archivo = null;
/* 12 */   private static FileReader fr = null;
/* 13 */   private static BufferedReader br = null;
/*    */   
/*    */   public static boolean fileExists(File fileToRead) {
/*    */     try {
/* 17 */       archivo = fileToRead;
/* 18 */       fr = new FileReader(archivo);
/* 19 */       br = new BufferedReader(fr);
/* 20 */       return true;
/* 21 */     } catch (Exception exception) {
/*    */       
/* 23 */       return false;
/*    */     } 
/*    */   }
/*    */   public static String[] configReader(File fileToRead) {
/* 27 */     String[] datos = null;
/* 28 */     if (fileExists(fileToRead)) {
/*    */       try {
/* 30 */         String cadena = "";
/*    */         String linea;
/* 32 */         while ((linea = br.readLine()) != null) {
/* 33 */           cadena = cadena + linea.toString() + "~";
/*    */         }
/*    */         
/* 36 */         datos = null;
/*    */         
/* 38 */         datos = cadena.split("~");
/* 39 */       } catch (Exception e) {
/* 40 */         e.printStackTrace();
/*    */       } finally {
/*    */         try {
/* 43 */           if (null != fr) {
/* 44 */             fr.close();
/*    */           }
/* 46 */         } catch (Exception e2) {
/* 47 */           e2.printStackTrace();
/*    */         } 
/*    */       } 
/*    */     }
/* 51 */     if (datos.length == 12) {
/* 52 */       String[] datosReturn = { datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8], datos[9], datos[10], datos[11] };
/* 53 */       return datosReturn;
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/ConfigClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */