/*    */ package zeroticketsignup.Utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DPIUtils
/*    */ {
/*    */   public static String transformDate(String dateDPI) {
/* 21 */     int dia = -1, mes = -1, anio = -1;
/* 22 */     dia = Integer.parseInt(dateDPI.substring(0, 2));
/* 23 */     anio = Integer.parseInt(dateDPI.substring(5, 9));
/*    */     
/* 25 */     if (dateDPI.substring(2, 5).equalsIgnoreCase("ENE")) {
/* 26 */       mes = 1;
/* 27 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("FEB")) {
/* 28 */       mes = 2;
/* 29 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("MAR")) {
/* 30 */       mes = 3;
/* 31 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("ABR")) {
/* 32 */       mes = 4;
/* 33 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("MAY")) {
/* 34 */       mes = 5;
/* 35 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("JUN")) {
/* 36 */       mes = 6;
/* 37 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("JUL")) {
/* 38 */       mes = 7;
/* 39 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("AGO")) {
/* 40 */       mes = 8;
/* 41 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("SEP")) {
/* 42 */       mes = 9;
/* 43 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("OCT")) {
/* 44 */       mes = 10;
/* 45 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("NOV")) {
/* 46 */       mes = 11;
/* 47 */     } else if (dateDPI.substring(2, 5).equalsIgnoreCase("DIC")) {
/* 48 */       mes = 12;
/*    */     } 
/*    */     
/* 51 */     return anio + "-" + mes + "-" + dia;
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Utils/DPIUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */