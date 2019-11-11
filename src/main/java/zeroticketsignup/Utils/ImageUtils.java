/*    */ package zeroticketsignup.Utils;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.awt.image.BufferedImageOp;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import javax.imageio.ImageIO;
/*    */ import javax.swing.JOptionPane;
/*    */ import org.imgscalr.Scalr;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ImageUtils
/*    */ {
/*    */   public static File bufferedToFile(BufferedImage bi, String filePath) {
/* 25 */     File outputfile = null;
/*    */     
/*    */     try {
/* 28 */       outputfile = new File(filePath);
/*    */       
/* 30 */       ImageIO.write(bi, "jpg", outputfile);
/* 31 */     } catch (IOException e) {
/* 32 */       outputfile = null;
/* 33 */       System.err.println("Error en convertir imagen to File \n" + e);
/*    */     } 
/*    */     
/* 36 */     return outputfile;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static BufferedImage resizeImage(BufferedImage bi, int width, int height) {
/* 42 */     BufferedImage rescaled = null, finale = null;
/* 43 */     if (bi.getHeight() >= height) {
/* 44 */       rescaled = Scalr.resize(bi, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, width, height, new BufferedImageOp[] { Scalr.OP_ANTIALIAS });
/* 45 */       int w = rescaled.getWidth();
/*    */       
/* 47 */       if (w >= width) {
/* 48 */         int xCrop = (w - width) / 2;
/*    */         
/* 50 */         finale = Scalr.crop(rescaled, xCrop, 0, width, height, new BufferedImageOp[] { Scalr.OP_ANTIALIAS });
/* 51 */         return finale;
/*    */       } 
/* 53 */       return rescaled;
/*    */     } 
/*    */ 
/*    */     
/* 57 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BufferedImage fileTobuff(String filename) {
/* 68 */     BufferedImage img = null;
/*    */     
/*    */     try {
/* 71 */       img = ImageIO.read(new File(filename));
/* 72 */     } catch (IOException e) {
/* 73 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 76 */     return img;
/*    */   }
/*    */ 
/*    */   
/*    */   public static InputStream buffToInputStream(BufferedImage buffi) {
/* 81 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/*    */     try {
/* 83 */       ImageIO.write(buffi, "jpg", os);
/* 84 */     } catch (IOException ex) {
/* 85 */       JOptionPane.showMessageDialog(null, "Error en convertir foto", "Error en ingresar datos", 0);
/*    */     } 
/* 87 */     InputStream fis = new ByteArrayInputStream(os.toByteArray());
/* 88 */     return fis;
/*    */   }
/*    */   
/*    */   public static InputStream byteToInputStream(byte[] fotoInBytes) {
/* 92 */     InputStream in = new ByteArrayInputStream(fotoInBytes);
/* 93 */     return in;
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Utils/ImageUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */