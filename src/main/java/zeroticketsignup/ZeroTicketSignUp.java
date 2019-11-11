/*     */ package zeroticketsignup;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.InputStream;
/*     */ import java.net.URISyntaxException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JOptionPane;
/*     */ import org.apache.poi.util.IOUtils;
/*     */ import zeroticketsignup.Controller.ConfigClass;
/*     */ import zeroticketsignup.Controller.DataBaseClass;
/*     */ import zeroticketsignup.Controller.IndexController;
/*     */ import zeroticketsignup.Controller.Logs;
/*     */ import zeroticketsignup.View.IndexView;
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
/*     */ public class ZeroTicketSignUp
/*     */ {
/*  30 */   public static int codeEvent = 0;
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  34 */     createFolder("Logs");
/*     */ 
/*     */ 
/*     */     
/*  38 */     File fileConfig = new File(getPathJar() + File.separator + "db.txt");
/*  39 */     System.out.println(getPathJar() + File.separator + "db.txt");
/*  40 */     String[] dataConfig = ConfigClass.configReader(fileConfig);
/*     */     
/*  42 */     boolean connect = DataBaseClass.connect(dataConfig);
/*     */     
/*  44 */     if (connect) {
/*     */       
/*  46 */       codeEvent = Integer.parseInt(dataConfig[7]);
/*  47 */       String nameEvent = "";
/*     */       try {
/*  49 */         Object[] data = (new DataBaseClass()).giveData("SELECT  Nombre FROM event WHERE idEvent = " + codeEvent + "");
/*  50 */         nameEvent = data[0].toString();
/*  51 */       } catch (Exception e) {
/*  52 */         nameEvent = "";
/*     */       } 
/*     */       
/*  55 */       JOptionPane.showMessageDialog(null, "Evento Seleccionado :  \"" + nameEvent + "\" Código: " + codeEvent);
/*     */       
/*  57 */       IndexView iv = new IndexView();
/*  58 */       iv.setLocationRelativeTo(null);
/*  59 */       IndexController controller = new IndexController(iv);
/*     */       
/*  61 */       iv.setVisible(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void createFolder(String nameFolder) {
/*  68 */     File theDir = new File(getPathJar() + File.separator + nameFolder);
/*     */     
/*  70 */     if (!theDir.exists()) {
/*  71 */       System.out.println("Creating directory: " + nameFolder);
/*  72 */       boolean result = false;
/*     */       try {
/*  74 */         theDir.mkdir();
/*  75 */         result = true;
/*  76 */       } catch (SecurityException se) {
/*  77 */         System.err.println("Error creating folder " + nameFolder);
/*     */       } 
/*  79 */       if (result) {
/*  80 */         System.out.println("DIR " + nameFolder + " created");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getPathJar() {
/*     */     try {
/*  91 */       return (new File(ZeroTicketSignUp.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath())).getParent();
/*  92 */     } catch (URISyntaxException ex) {
/*  93 */       Logs.printLog("Error en URISyntax Expection ", "Error " + ex);
/*     */       
/*  95 */       return null;
/*     */     } 
/*     */   }
/*     */   public static File getFileFromPacking(String path, String nameToCopy) {
/*  99 */     InputStream stream = ZeroTicketSignUp.class.getResourceAsStream(path);
/*     */     
/*     */     try {
/* 102 */       FileOutputStream file = new FileOutputStream(getPathJar() + File.separator + nameToCopy);
/* 103 */       IOUtils.copy(stream, file);
/* 104 */       return new File(getPathJar() + File.separator + nameToCopy);
/* 105 */     } catch (Exception ex) {
/* 106 */       Logger.getLogger(ZeroTicketSignUp.class.getName()).log(Level.SEVERE, null, ex);
/*     */ 
/*     */       
/* 109 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/ZeroTicketSignUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */