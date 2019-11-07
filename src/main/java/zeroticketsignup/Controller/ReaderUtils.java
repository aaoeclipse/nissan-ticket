/*     */ package zeroticketsignup.Controller;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.List;
/*     */ import javax.smartcardio.Card;
/*     */ import javax.smartcardio.CardChannel;
/*     */ import javax.smartcardio.CardException;
/*     */ import javax.smartcardio.CardTerminal;
/*     */ import javax.smartcardio.TerminalFactory;
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
/*     */ public class ReaderUtils
/*     */ {
/*     */   public static CardTerminal getTerminalByName(String terminalName) throws NFCException {
/*     */     try {
/*  24 */       TerminalFactory terminalFactory = TerminalFactory.getDefault();
/*  25 */       List<CardTerminal> terminals = terminalFactory.terminals().list();
/*  26 */       for (CardTerminal terminal : terminals) {
/*  27 */         System.out.println(terminal.getName());
/*  28 */         if (terminal.getName().contains(terminalName)) {
/*  29 */           return terminal;
/*     */         }
/*     */       } 
/*  32 */     } catch (CardException e) {
/*  33 */       throw new NFCException("Error : " + e);
/*     */     } 
/*  35 */     throw new NFCException("No card terminal found, expected: [" + terminalName + "], available: [" + getAvailableTerminals() + "]");
/*     */   }
/*     */   
/*     */   private static String getAvailableTerminals() {
/*  39 */     StringBuilder sb = new StringBuilder();
/*  40 */     TerminalFactory terminalFactory = TerminalFactory.getDefault();
/*     */     try {
/*  42 */       List<CardTerminal> terminals = terminalFactory.terminals().list();
/*  43 */       for (CardTerminal terminal : terminals) {
/*  44 */         if (sb.length() != 0) {
/*  45 */           sb.append(", ");
/*     */         }
/*  47 */         sb.append(terminal.getName());
/*     */       } 
/*  49 */     } catch (CardException cardException) {}
/*     */     
/*  51 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] readUID(CardTerminal terminal) {
/*  59 */     String data = "";
/*  60 */     String UID = "";
/*  61 */     String status = "";
/*     */ 
/*     */     
/*     */     try {
/*  65 */       Card card = terminal.connect("*");
/*     */       
/*  67 */       System.out.println("Card: " + card);
/*  68 */       CardChannel channel = card.getBasicChannel();
/*     */       
/*  70 */       card.getATR().getBytes();
/*     */       
/*  72 */       byte[] baReadUID = { -1, -54, 0, 0, 0 };
/*     */ 
/*     */       
/*  75 */       if (send(baReadUID, channel)[1].equalsIgnoreCase("9000")) {
/*     */         
/*  77 */         UID = send(baReadUID, channel)[0].toUpperCase();
/*  78 */         System.out.println("UID: " + UID);
/*  79 */         status = "9000";
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  84 */       card.disconnect(false);
/*     */     }
/*  86 */     catch (CardException cardException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     return new String[] { UID, data, status };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String[] send(byte[] cmd, CardChannel channel) {
/*  98 */     String res = "";
/*     */     
/* 100 */     byte[] baResp = new byte[258];
/* 101 */     ByteBuffer bufCmd = ByteBuffer.wrap(cmd);
/* 102 */     ByteBuffer bufResp = ByteBuffer.wrap(baResp);
/*     */ 
/*     */     
/* 105 */     int output = 0;
/*     */ 
/*     */     
/*     */     try {
/* 109 */       output = channel.transmit(bufCmd, bufResp);
/*     */     }
/* 111 */     catch (CardException ex) {
/* 112 */       ex.printStackTrace();
/*     */     } 
/*     */     
/* 115 */     for (int i = 0; i < output; i++) {
/*     */       
/* 117 */       res = res + String.format("%02x", new Object[] { Byte.valueOf(baResp[i]) });
/*     */     } 
/*     */ 
/*     */     
/* 121 */     if (res.length() != 4)
/*     */     {
/* 123 */       return new String[] { res.substring(0, res.length() - 4), res.substring(res.length() - 4, res.length()) };
/*     */     }
/* 125 */     return new String[] { "", res };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toStringFromHex(String hex) {
/* 131 */     StringBuilder output = new StringBuilder();
/* 132 */     for (int i = 0; i < hex.length(); i += 2) {
/* 133 */       String str = hex.substring(i, i + 2);
/* 134 */       output.append((char)Integer.parseInt(str, 16));
/*     */     } 
/* 136 */     return output.toString();
/*     */   }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/ReaderUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */