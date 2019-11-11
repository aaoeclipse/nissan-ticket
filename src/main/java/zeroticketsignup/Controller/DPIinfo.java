/*     */ package zeroticketsignup.Controller;
/*     */ 
/*     */ import com.google.common.primitives.Bytes;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.StringTokenizer;
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
/*     */ public class DPIinfo
/*     */ {
/*     */   private String dpi;
/*     */   private String primerNombre;
/*     */   private String segundoNombre;
/*     */   private String tercerNombre;
/*     */   private String primerApellido;
/*     */   private String segundoApellido;
/*     */   private String sexo;
/*     */   private String vecindadMunicipio;
/*     */   private String vecindadDepartamento;
/*     */   private String nacionalidad;
/*     */   private String nacimiento;
/*     */   private String emisionFecha;
/*  32 */   private byte[] photoOnBytes = null;
/*     */   private String respuestaLector;
/*  34 */   private byte[] respuestaBytes = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DPIinfo(byte[] dataResult, boolean isNew) throws UnsupportedEncodingException {
/*  42 */     String dataInString = new String(dataResult, "ISO-8859-1");
/*  43 */     this.respuestaLector = dataInString;
/*     */     
/*  45 */     if (isNew) {
/*     */       
/*  47 */       decodeNew(this.respuestaLector);
/*     */     }
/*     */     else {
/*     */       
/*  51 */       decodeOld(this.respuestaLector, dataResult);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  56 */     this.primerNombre = this.primerNombre.replace("  ", "").replace("\000", "");
/*  57 */     this.segundoNombre = this.segundoNombre.replace("  ", "").replace("\000", "");
/*  58 */     this.primerApellido = this.primerApellido.replace("  ", "").replace("\000", "");
/*  59 */     this.segundoApellido = this.segundoApellido.replace("  ", "").replace("\000", "");
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
/*     */   public void decodeNew(String dataText) {
/*  75 */     this.dpi = dataText.substring(30, 43);
/*  76 */     this.primerNombre = dataText.substring(43, 68);
/*  77 */     this.segundoNombre = dataText.substring(68, 85);
/*  78 */     this.primerApellido = dataText.substring(143, 168);
/*  79 */     this.segundoApellido = dataText.substring(168, 194);
/*  80 */     this.sexo = dataText.substring(218, 227);
/*  81 */     this.nacimiento = oldFormatDate(dataText.substring(290, 300));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     this.photoOnBytes = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String oldFormatDate(String newFormat) {
/*  97 */     String ans = "";
/*  98 */     newFormat = newFormat + "/";
/*  99 */     StringTokenizer st = new StringTokenizer(newFormat, "/");
/*     */     
/* 101 */     while (st.hasMoreTokens()) {
/* 102 */       ans = ans + st.nextToken();
/* 103 */       ans = ans + nameMonth(st.nextToken());
/* 104 */       ans = ans + st.nextToken();
/*     */     } 
/*     */ 
/*     */     
/* 108 */     return ans;
/*     */   }
/*     */ 
/*     */   
/*     */   public String nameMonth(String month) {
/* 113 */     String name = "";
/* 114 */     int m = Integer.parseInt(month);
/*     */     
/* 116 */     switch (m)
/*     */     { case 1:
/* 118 */         name = "ENE";
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
/* 159 */         return name;case 2: name = "ENE"; return name;case 3: name = "MAR"; return name;case 4: name = "ABR"; return name;case 5: name = "MAY"; return name;case 6: name = "JUN"; return name;case 7: name = "JUL"; return name;case 8: name = "AGO"; return name;case 9: name = "SEP"; return name;case 10: name = "OCT"; return name;case 11: name = "NOV"; return name;case 12: name = "DIC"; return name; }  name = ""; return name;
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
/*     */   public void decodeOld(String dataText, byte[] dataBytes) {
/* 171 */     this.dpi = dataText.substring(36, 49);
/* 172 */     this.primerNombre = dataText.substring(49, 74);
/* 173 */     this.segundoNombre = dataText.substring(74, 91);
/* 174 */     this.primerApellido = dataText.substring(99, 124);
/* 175 */     this.segundoApellido = dataText.substring(124, 149);
/* 176 */     this.sexo = dataText.substring(174, 183);
/* 177 */     this.nacimiento = dataText.substring(273, 282);
/*     */ 
/*     */     
/* 180 */     this.photoOnBytes = findPhotoOldDPI(dataBytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] findPhotoOldDPI(byte[] data) {
/* 191 */     byte[] foto = null;
/* 192 */     int startRead = -1;
/* 193 */     int endRead = -1;
/*     */     
/* 195 */     String sb = "";
/* 196 */     for (byte b : data) {
/* 197 */       sb = sb + String.format("%02X", new Object[] { Byte.valueOf(b) });
/*     */     } 
/* 199 */     if (sb.contains("FFD8") && sb.contains("FFD96F6D")) {
/* 200 */       startRead = sb.indexOf("FFD8") / 2;
/* 201 */       endRead = sb.indexOf("FFD96F6D") / 2;
/*     */       
/* 203 */       ArrayList<Byte> fotoBytes = new ArrayList<>();
/* 204 */       for (int i = startRead; i < endRead + 1; i++) {
/* 205 */         fotoBytes.add(Byte.valueOf(data[i]));
/*     */       }
/*     */ 
/*     */       
/* 209 */       foto = Bytes.toArray(fotoBytes);
/*     */     } else {
/*     */       
/* 212 */       foto = null;
/*     */     } 
/*     */     
/* 215 */     return foto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printBytes(byte[] dataResult) {
/* 226 */     StringBuilder sb = new StringBuilder();
/* 227 */     for (byte b : dataResult) {
/* 228 */       sb.append(String.format("%02X:", new Object[] { Byte.valueOf(b) }));
/*     */     } 
/*     */     
/* 231 */     System.out.println(sb.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public DPIinfo(String[] dataDPI, byte[] bytesPhoto) {
/* 236 */     setDataInfo(dataDPI);
/*     */     
/* 238 */     this.photoOnBytes = bytesPhoto;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void setDataInfo(String[] dataDPI) {
/* 244 */     this.dpi = dataDPI[0];
/* 245 */     this.primerNombre = dataDPI[1];
/* 246 */     this.segundoNombre = dataDPI[2];
/* 247 */     this.tercerNombre = dataDPI[3];
/* 248 */     this.primerApellido = dataDPI[4];
/* 249 */     this.segundoApellido = dataDPI[5];
/* 250 */     this.sexo = dataDPI[6];
/* 251 */     this.vecindadMunicipio = dataDPI[7];
/* 252 */     this.vecindadDepartamento = dataDPI[8];
/* 253 */     this.nacionalidad = dataDPI[9];
/* 254 */     this.nacimiento = dataDPI[10];
/* 255 */     this.emisionFecha = dataDPI[11];
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
/*     */ 
/*     */ 
/*     */   
/* 273 */   public String getDpi() { return this.dpi; }
/*     */ 
/*     */ 
/*     */   
/* 277 */   public String getPrimerNombre() { return this.primerNombre; }
/*     */ 
/*     */ 
/*     */   
/* 281 */   public String getSegundoNombre() { return this.segundoNombre; }
/*     */ 
/*     */ 
/*     */   
/* 285 */   public String getTercerNombre() { return this.tercerNombre; }
/*     */ 
/*     */ 
/*     */   
/* 289 */   public String getPrimerApellido() { return this.primerApellido; }
/*     */ 
/*     */ 
/*     */   
/* 293 */   public String getSegundoApellido() { return this.segundoApellido; }
/*     */ 
/*     */ 
/*     */   
/* 297 */   public String getSexo() { return this.sexo; }
/*     */ 
/*     */ 
/*     */   
/* 301 */   public String getVecindadMunicipio() { return this.vecindadMunicipio; }
/*     */ 
/*     */ 
/*     */   
/* 305 */   public String getVecindadDepartamento() { return this.vecindadDepartamento; }
/*     */ 
/*     */ 
/*     */   
/* 309 */   public String getNacionalidad() { return this.nacionalidad; }
/*     */ 
/*     */ 
/*     */   
/* 313 */   public String getNacimiento() { return this.nacimiento; }
/*     */ 
/*     */ 
/*     */   
/* 317 */   public String getEmisionFecha() { return this.emisionFecha; }
/*     */ 
/*     */ 
/*     */   
/* 321 */   public byte[] getPhotoOnBytes() { return this.photoOnBytes; }
/*     */ 
/*     */ 
/*     */   
/* 325 */   public String getRespuestaLector() { return this.respuestaLector; }
/*     */ 
/*     */ 
/*     */   
/* 329 */   public byte[] getRespuestaBytes() { return this.respuestaBytes; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public String toString() { return "DPIinfo{dpi=" + this.dpi + ", primerNombre=" + this.primerNombre + ", segundoNombre=" + this.segundoNombre + ", tercerNombre=" + this.tercerNombre + ", primerApellido=" + this.primerApellido + ", segundoApellido=" + this.segundoApellido + ", sexo=" + this.sexo + ", vecindadMunicipio=" + this.vecindadMunicipio + ", vecindadDepartamento=" + this.vecindadDepartamento + ", nacionalidad=" + this.nacionalidad + ", nacimiento=" + this.nacimiento + ", emisionFecha=" + this.emisionFecha + '}'; }
/*     */ }


/* Location:              /home/eclipse/Documents/Work/Nissan/ZERO INNOVATION/REGISTRO/ZeroTicketJustSignUp.jar!/zeroticketsignup/Controller/DPIinfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.1
 */