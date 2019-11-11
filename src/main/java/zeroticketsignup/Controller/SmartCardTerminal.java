/*    */ package zeroticketsignup.Controller;
/*    */ 
/*    */ import javax.smartcardio.CardTerminal;
/*    */ import javax.swing.JOptionPane;
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
/*    */ public class SmartCardTerminal
/*    */ {
/* 20 */   private static CardTerminal terminal = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void connectDPIReader() {
/* 28 */     terminal = null;
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 33 */       terminal = ReaderUtils.getTerminalByName("OMNIKEY");
/*    */     
/*    */     }
/* 36 */     catch (Exception ex) {
/*    */       
/* 38 */       JOptionPane.showMessageDialog(null, "Error en conectar lector DPI : " + ex, "Error en lector DPI", 0);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public static CardTerminal getTerminal() { return terminal; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public static void setTerminal(CardTerminal terminal) { SmartCardTerminal.terminal = terminal; }
/*    */ 
/*    */   
/*    */   public static void connectNFCReader() {
/* 57 */     terminal = null;
/*    */     try {
/* 59 */       terminal = ReaderUtils.getTerminalByName("ACS");
/* 60 */     } catch (NFCException ex) {
/* 61 */       JOptionPane.showMessageDialog(null, "Error en conectar lector NFC : " + ex, "Error en lector NFC", 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/SmartCardTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */