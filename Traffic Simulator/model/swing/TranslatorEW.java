package model.swing;

/*    */ class TranslatorEW
/*    */   extends Translator
/*    */ {
/*  */   TranslatorEW(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) { super(tX, tY, tWidth, tHeight, tScaleFactor); }
/*    */   
/*  */   int getX(double x, double y, double width, double height) { return scale(this._tX + this._tWidth - x - width); }
/*  */   int getY(double x, double y, double width, double height) { return scale(this._tY + this._tHeight - y - height); }
/*  */   int getWidth(double width, double height) { return scale(width); }
/*  */   int getHeight(double width, double height) { return scale(height); }
/*    */ }