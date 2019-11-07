/*    */ package zeroticketsignup.View;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.EventQueue;
/*    */ import java.awt.Frame;
/*    */ import java.awt.event.WindowAdapter;
/*    */ import java.awt.event.WindowEvent;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.UnsupportedLookAndFeelException;
/*    */ 
/*    */ public class LoadView extends JDialog {
/*    */   public LoadView(Frame parent, boolean modal) {
/* 20 */     super(parent, modal);
/* 21 */     setUndecorated(true);
/* 22 */     initComponents();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private JLabel jLabel1;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void initComponents() {
/* 35 */     this.jLabel1 = new JLabel();
/*    */     
/* 37 */     setDefaultCloseOperation(2);
/* 38 */     setBackground(new Color(255, 255, 255, 0));
/*    */     
/* 40 */     this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/zeroticketsignup/Images/loading.gif")));
/*    */     
/* 42 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 43 */     getContentPane().setLayout(layout);
/* 44 */     layout.setHorizontalGroup(layout
/* 45 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 46 */         .addComponent(this.jLabel1));
/*    */     
/* 48 */     layout.setVerticalGroup(layout
/* 49 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 50 */         .addComponent(this.jLabel1));
/*    */ 
/*    */     
/* 53 */     pack();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/*    */     try {
/* 66 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 67 */         if ("Nimbus".equals(info.getName())) {
/* 68 */           UIManager.setLookAndFeel(info.getClassName());
/*    */           break;
/*    */         } 
/*    */       } 
/* 72 */     } catch (ClassNotFoundException ex) {
/* 73 */       Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);
/* 74 */     } catch (InstantiationException ex) {
/* 75 */       Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);
/* 76 */     } catch (IllegalAccessException ex) {
/* 77 */       Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);
/* 78 */     } catch (UnsupportedLookAndFeelException ex) {
/* 79 */       Logger.getLogger(LoadView.class.getName()).log(Level.SEVERE, null, ex);
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 87 */     EventQueue.invokeLater(new Runnable() {
/*    */           public void run() {
/* 89 */             LoadView dialog = new LoadView(new JFrame(), true);
/* 90 */             dialog.addWindowListener(new WindowAdapter()
/*    */                 {
/*    */                   public void windowClosing(WindowEvent e) {
/* 93 */                     System.exit(0);
/*    */                   }
/*    */                 });
/* 96 */             dialog.setVisible(true);
/*    */           }
/*    */         });
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/View/LoadView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */