/*    */ package model.swing;
/*    */ 
/*    */ abstract class Translator
/*    */ {
/*    */   double _tX;
/*    */   double _tY;
/*    */   double _tWidth;
/*    */   double _tHeight;
/*    */   double _tScaleFactor;
/*    */   
/*    */   Translator(double tX, double tY, double tWidth, double tHeight, double tScaleFactor)
/*    */   {
/* 13 */     this._tX = tX;
/* 14 */     this._tY = tY;
/* 15 */     this._tWidth = tWidth;
/* 16 */     this._tHeight = tHeight;
/* 17 */     this._tScaleFactor = tScaleFactor;
/*    */   }
/*    */   
/* 20 */   int scale(double arg) { return (int)Math.ceil(arg * this._tScaleFactor); }
/*    */   
/*    */   abstract int getX(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*    */   
/*    */   abstract int getY(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*    */   
/*    */   abstract int getWidth(double paramDouble1, double paramDouble2);
/*    */   
/*    */   abstract int getHeight(double paramDouble1, double paramDouble2);
/*    */ }