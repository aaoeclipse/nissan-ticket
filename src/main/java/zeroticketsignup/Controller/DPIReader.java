/*     */ package zeroticketsignup.Controller;
/*     */ 
/*     */ import com.google.common.primitives.Bytes;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.smartcardio.Card;
/*     */ import javax.smartcardio.CardChannel;
/*     */ import javax.smartcardio.CardException;
/*     */ import javax.smartcardio.CardTerminal;
/*     */ import javax.smartcardio.CommandAPDU;
/*     */ import javax.smartcardio.ResponseAPDU;
/*     */ import javax.swing.JOptionPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DPIReader
/*     */ {
/*     */   private DPIinfo dpiReaded;
/*     */   private CardTerminal cardTerminal;
/*     */   private byte[] photoBytes;
/*     */   private byte[] dataResult;
/*     */   
/*     */   public DPIReader() {
/*  30 */     this.dpiReaded = null;
/*  31 */     this.cardTerminal = null;
/*  32 */     this.photoBytes = null;
/*  33 */     this.dataResult = null;
/*     */ 
/*     */ 
/*     */     
/*  37 */     this.dpiReaded = null;
/*  38 */     this.cardTerminal = SmartCardTerminal.getTerminal();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void doCardReaderCommunication(CardTerminal terminal) throws DPIParseException, UnsupportedEncodingException {
/*  44 */     if (terminal != null) {
/*  45 */       this.cardTerminal = terminal;
/*     */       
/*     */       while (true) {
/*     */         try {
/*  49 */           this.cardTerminal.waitForCardPresent(1000L);
/*  50 */           System.out.println("Inserted card");
/*  51 */           handleCard(this.cardTerminal);
/*  52 */           this.cardTerminal.waitForCardAbsent(1000L);
/*  53 */           System.out.println("Removed card");
/*     */           
/*     */           break;
/*  56 */         } catch (CardException ex) {
/*  57 */           Logger.getLogger(DPIReader.class.getName()).log(Level.SEVERE, null, ex);
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/*  62 */       JOptionPane.showMessageDialog(null, "Error, terminal es nulo ", "Error en lectura de DPI", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleCard(CardTerminal cardTerminal) throws DPIParseException, UnsupportedEncodingException {
/*  72 */     String[] dataDpi = null;
/*  73 */     boolean readIt = false;
/*  74 */     boolean isNew = false;
/*     */     
/*  76 */     ArrayList<Byte> dataResultItems = new ArrayList<>();
/*     */     
/*     */     try {
/*  79 */       Card card = cardTerminal.connect("*");
/*  80 */       card.beginExclusive();
/*     */ 
/*     */       
/*     */       try {
/*  84 */         CardChannel channel = card.getBasicChannel();
/*     */         
/*  86 */         ResponseAPDU response = channel.transmit(new CommandAPDU(newBytes));
/*     */         
/*  88 */         if (response.getSW() == 27266) {
/*  89 */           System.out.println("DPI VIEJO");
/*     */           
/*  91 */           response = channel.transmit(new CommandAPDU(0, 164, 4, 12, firstLine));
/*  92 */           addByteToList(dataResultItems, response.getData());
/*     */           
/*  94 */           if (response.getSW() == 36864) {
/*  95 */             response = channel.transmit(new CommandAPDU(secondLine));
/*  96 */             addByteToList(dataResultItems, response.getData());
/*     */             
/*  98 */             if (response.getSW() == 36864) {
/*  99 */               response = channel.transmit(new CommandAPDU(thirdLine));
/* 100 */               addByteToList(dataResultItems, response.getData());
/*     */               
/* 102 */               if (response.getSW() == 36864) {
/* 103 */                 response = channel.transmit(new CommandAPDU(forthLine));
/* 104 */                 addByteToList(dataResultItems, response.getData());
/*     */                 
/* 106 */                 if (response.getSW() == 36864) {
/* 107 */                   response = channel.transmit(new CommandAPDU(fifthLine));
/* 108 */                   addByteToList(dataResultItems, response.getData());
/*     */                   
/* 110 */                   if (response.getSW() == 36864) {
/* 111 */                     response = channel.transmit(new CommandAPDU(sixthLine));
/* 112 */                     addByteToList(dataResultItems, response.getData());
/*     */                     
/* 114 */                     if (response.getSW() == 36864) {
/* 115 */                       response = channel.transmit(new CommandAPDU(seventhLine));
/* 116 */                       addByteToList(dataResultItems, response.getData());
/*     */                       
/* 118 */                       if (response.getSW() == 36864) {
/* 119 */                         response = channel.transmit(new CommandAPDU(eightLine));
/* 120 */                         addByteToList(dataResultItems, response.getData());
/*     */                         
/* 122 */                         if (response.getSW() == 36864) {
/* 123 */                           response = channel.transmit(new CommandAPDU(nineLine));
/* 124 */                           addByteToList(dataResultItems, response.getData());
/*     */                           
/* 126 */                           if (response.getSW() == 36864) {
/* 127 */                             response = channel.transmit(new CommandAPDU(tenLine));
/* 128 */                             addByteToList(dataResultItems, response.getData());
/*     */                             
/* 130 */                             if (response.getSW() == 36864) {
/* 131 */                               response = channel.transmit(new CommandAPDU(tenLine));
/* 132 */                               addByteToList(dataResultItems, response.getData());
/*     */                               
/* 134 */                               if (response.getSW() == 36864) {
/* 135 */                                 response = channel.transmit(new CommandAPDU(elevenLine));
/* 136 */                                 addByteToList(dataResultItems, response.getData());
/*     */                                 
/* 138 */                                 if (response.getSW() == 36864) {
/* 139 */                                   response = channel.transmit(new CommandAPDU(twelveLine));
/* 140 */                                   addByteToList(dataResultItems, response.getData());
/*     */ 
/*     */ 
/*     */ 
/*     */                                   
/* 145 */                                   byte[] photoAPDUArray = null;
/* 146 */                                   byte[] lastData = { 0, 0, 0, 0 };
/* 147 */                                   int b = 1;
/* 148 */                                   while (!isPhotoFind(lastData)) {
/*     */ 
/*     */                                     
/* 151 */                                     photoAPDUArray = new byte[] { 0, -80, (byte)b, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                                     
/* 159 */                                     response = channel.transmit(new CommandAPDU(photoAPDUArray));
/*     */                                     
/* 161 */                                     if (response.getSW() == 36864) {
/*     */                                       
/* 163 */                                       addByteToList(dataResultItems, response.getData());
/* 164 */                                       lastData = response.getData();
/*     */                                     } 
/*     */ 
/*     */                                     
/* 168 */                                     if (b == 100) {
/*     */                                       break;
/*     */                                     }
/* 171 */                                     b++;
/*     */                                   } 
/*     */                                   
/* 174 */                                   if (response.getSW() == 36864) {
/* 175 */                                     response = channel.transmit(new CommandAPDU(sixtyNine));
/* 176 */                                     addByteToList(dataResultItems, response.getData());
/*     */                                     
/* 178 */                                     if (response.getSW() == 36864) {
/* 179 */                                       response = channel.transmit(new CommandAPDU(seventy));
/* 180 */                                       addByteToList(dataResultItems, response.getData());
/*     */                                       
/* 182 */                                       if (response.getSW() == 36864) {
/* 183 */                                         response = channel.transmit(new CommandAPDU(seventyOne));
/* 184 */                                         addByteToList(dataResultItems, response.getData());
/*     */                                         
/* 186 */                                         if (response.getSW() == 36864) {
/* 187 */                                           response = channel.transmit(new CommandAPDU(seventyTwo));
/* 188 */                                           addByteToList(dataResultItems, response.getData());
/*     */ 
/*     */                                           
/* 191 */                                           readIt = true;
/*     */                                         }
/*     */                                       
/*     */                                       }
/*     */                                     
/*     */                                     }
/*     */                                   
/*     */                                   }
/*     */                                 
/*     */                                 } 
/*     */                               } 
/*     */                             } 
/*     */                           } 
/*     */                         } 
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/* 212 */         } else if (response.getSW() == 36864) {
/*     */           
/* 214 */           if (response.getSW() == 36864) {
/* 215 */             response = channel.transmit(new CommandAPDU(line1));
/* 216 */             addByteToList(dataResultItems, response.getData());
/*     */             
/* 218 */             if (response.getSW() == 36864) {
/* 219 */               response = channel.transmit(new CommandAPDU(line2));
/* 220 */               addByteToList(dataResultItems, response.getData());
/*     */               
/* 222 */               if (response.getSW() == 36864) {
/* 223 */                 response = channel.transmit(new CommandAPDU(line3));
/* 224 */                 addByteToList(dataResultItems, response.getData());
/*     */                 
/* 226 */                 if (response.getSW() == 36864) {
/* 227 */                   response = channel.transmit(new CommandAPDU(line4));
/* 228 */                   addByteToList(dataResultItems, response.getData());
/*     */                   
/* 230 */                   if (response.getSW() == 36864) {
/* 231 */                     response = channel.transmit(new CommandAPDU(line5));
/* 232 */                     addByteToList(dataResultItems, response.getData());
/* 233 */                     readIt = true;
/*     */                     
/* 235 */                     isNew = true;
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } else {
/*     */           
/* 243 */           System.out.println("Algo paso mal " + response.getSW());
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 248 */         this.dpiReaded = null;
/* 249 */         card.endExclusive();
/*     */         
/*     */         try {
/* 252 */           card.disconnect(false);
/* 253 */         } catch (CardException ex) {
/*     */           try {
/* 255 */             this.dpiReaded = null;
/* 256 */             throw new DPIParseException("Error al desconectar DPI : " + ex.getMessage());
/* 257 */           } catch (DPIParseException dPIParseException) {}
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 262 */         System.out.println("End read. ! ");
/*     */         
/* 264 */         if (readIt)
/*     */         {
/*     */           
/* 267 */           this.dataResult = Bytes.toArray(dataResultItems);
/*     */ 
/*     */           
/* 270 */           this.dpiReaded = new DPIinfo(this.dataResult, isNew);
/*     */         }
/*     */       
/*     */       }
/* 274 */       catch (CardException e) {
/* 275 */         this.dpiReaded = null;
/*     */       }
/*     */     
/*     */     }
/* 279 */     catch (CardException e) {
/* 280 */       this.dpiReaded = null;
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
/*     */   private boolean isPhotoFind(byte[] lastResponse) {
/* 297 */     String sb = "";
/* 298 */     for (byte b : lastResponse) {
/* 299 */       sb = sb + String.format("%02X", new Object[] { Byte.valueOf(b) });
/*     */     } 
/*     */     
/* 302 */     sb = sb.substring(sb.length() - 4, sb.length());
/*     */     
/* 304 */     if (sb.equalsIgnoreCase("FFD9")) {
/* 305 */       return true;
/*     */     }
/* 307 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 317 */   public DPIinfo getDPI() { return this.dpiReaded; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addByteToList(ArrayList<Byte> list, byte[] data) {
/* 328 */     for (int i = 0; i < data.length; i++) {
/* 329 */       list.add(Byte.valueOf(data[i]));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 335 */   private static final byte[] newBytes = new byte[] { 0, -92, 4, 12, 12, -96, 0, 0, 0, 99, 80, 75, 67, 83, 45, 49, 53 };
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
/* 358 */   private static final byte[] firstLine = new byte[] { -96, 0, 0, 0, 119, 1, -125, -125, 8, 16, 0, -15, 0, 0, 0, 1 };
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
/* 378 */   private static final byte[] secondLine = new byte[] { 0, -92, 0, 0, 2, 16, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 388 */   private static final byte[] thirdLine = new byte[] { 0, -92, 2, 0, 2, 1, 1 };
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
/* 399 */   private static final byte[] forthLine = new byte[] { 0, -80, 0, 0, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 408 */   private static final byte[] fifthLine = new byte[] { 0, -80, 0, -1, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 416 */   private static final byte[] sixthLine = new byte[] { 0, -92, 2, 0, 2, 1, 2 };
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
/* 427 */   private static final byte[] seventhLine = new byte[] { 0, -80, 0, 0, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 435 */   private static final byte[] eightLine = new byte[] { 0, -80, 0, -1, -1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 443 */   private static final byte[] nineLine = new byte[] { 0, -92, 4, 12, 7, -96, 0, 0, 2, 71, 16, 1 };
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
/* 459 */   private static final byte[] tenLine = new byte[] { 0, -80, -127, 0, 93 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 467 */   private static final byte[] elevenLine = new byte[] { 0, -92, 4, 12, 7, -96, 0, 0, 2, 71, 16, 1 };
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
/* 482 */   private static final byte[] twelveLine = new byte[] { 0, -80, -126, 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 490 */   private static final byte[] sixtyNine = new byte[] { 0, -92, 4, 0, 7, -96, 0, 0, 1, 81, 0, 0 };
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
/* 505 */   private static final byte[] seventy = new byte[] { Byte.MIN_VALUE, -54, -97, Byte.MAX_VALUE, 45 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 513 */   private static final byte[] seventyOne = new byte[] { 0, -92, 4, 0, 16, -96, 0, 0, 0, 119, 1, 8, 0, 7, 16, 0, -15, 0, 0, 3, 0 };
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
/* 537 */   private static final byte[] seventyTwo = new byte[] { 0, -53, 63, -1, 3, 92, 1, -62 };
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
/* 551 */   private static final byte[] line1 = new byte[] { 0, -92, 4, 12, 12, -96, 0, 0, 0, 99, 80, 75, 67, 83, 45, 49, 53 };
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
/* 572 */   private static final byte[] line2 = new byte[] { 0, -80, -127, 0, 0, 2, 41 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 582 */   private static final byte[] line3 = new byte[] { 0, -80, -126, 0, 0, 17, 70 };
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
/* 593 */   private static final byte[] line4 = new byte[] { 0, -92, 4, 12, 7, -96, 0, 0, 2, 71, 16, 1 };
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
/* 609 */   private static final byte[] line5 = new byte[] { 0, -124, 0, 0, 8 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 617 */   private static final byte[] line6 = new byte[] { 0, -126, 0, 0, 40, -42, 47, 18, -46, 13, 27, 101, -63, -51, 100, -37, -11, -26, -6, -63, 66, -31, -64, -113, -83, 33, 22, 95, 104, 75, 15, 88, -55, -16, 61, 126, -21, -5, -41, -44, 14, -39, 110, -57, -19, 0 };
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
/* 667 */   public CardTerminal getCardTerminal() { return this.cardTerminal; }
/*     */ 
/*     */ 
/*     */   
/* 671 */   public void setCardTerminal(CardTerminal cardTerminal) { this.cardTerminal = cardTerminal; }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/DPIReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */