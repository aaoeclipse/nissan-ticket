/*    */ package zeroticketsignup.Controller;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.border.Border;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ 
/*    */ public class PhotoCell
/*    */   extends DefaultTableCellRenderer {
/* 11 */   Border unselectedBorder = null;
/* 12 */   Border selectedBorder = null;
/*    */   
/*    */   boolean isBordered = true;
/*    */   
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 17 */     if (value instanceof JLabel) {
/*    */       
/* 19 */       JLabel label = (JLabel)value;
/*    */       
/* 21 */       return label;
/*    */     } 
/*    */     
/* 24 */     return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
/*    */   }
/*    */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/PhotoCell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */