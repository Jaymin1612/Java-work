/*     */ package model.swing;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import model.AnimatorBuilder;
/*     */ import model.Car;
/*     */ import model.CarHandler;
/*     */ import model.Light;
/*     */ import model.LightColor;
/*     */ import model.MP;
/*     */ import model.Road;
/*     */ import util.Animator;
/*     */ import util.SwingAnimatorPainter;
/*     */ 
/*     */ 
/*     */ public class SwingAnimatorBuilder
/*     */   implements AnimatorBuilder
/*     */ {
/*     */   MyPainter _painter;
/*     */   
/*  23 */   public SwingAnimatorBuilder() { this._painter = new MyPainter(); }
/*     */   
/*     */   public Animator getAnimator() {
/*  26 */     if (this._painter == null) throw new IllegalStateException();
/*  27 */     Animator returnValue = new util.SwingAnimator(this._painter, "Traffic Simulator", VP.displayWidth, VP.displayHeight, VP.displayDelay);
/*     */     
/*  29 */     this._painter = null;
/*  30 */     return returnValue; }
/*     */   
/*  32 */   private static double skipInit = VP.gap;
/*  33 */   private static double skipRoad = VP.gap + MP.roadDrawLength;
/*  34 */   private static double skipCar = VP.gap + VP.elementWidth;
/*  35 */   private static double skipRoadCar = skipRoad + skipCar;
/*     */   
/*  37 */   public void addLight(Light d, int i, int j) { double x = skipInit + skipRoad + j * skipRoadCar;
/*  38 */     double y = skipInit + skipRoad + i * skipRoadCar;
/*  39 */     Translator t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
/*  40 */     this._painter.addLight(d, t);
/*     */   }
/*     */   
/*  43 */   public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) { double x = skipInit + j * skipRoadCar;
/*  44 */     double y = skipInit + skipRoad + i * skipRoadCar;
/*  45 */     Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorWE(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor);
/*     */     
/*  47 */     this._painter.addRoad(l, t);
/*     */   }
/*     */   
/*  50 */   public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) { double x = skipInit + skipRoad + j * skipRoadCar;
/*  51 */     double y = skipInit + i * skipRoadCar;
/*  52 */     Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorNS(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor);
/*     */     
/*  54 */     this._painter.addRoad(l, t);
/*     */   }
/*     */   
/*     */   private static class MyPainter implements SwingAnimatorPainter {
/*     */     private List<Element<Road>> _roadElements;
/*     */     private List<Element<Light>> _lightElements;
/*     */     
/*     */     private static class Element<T> {
/*     */       T x;
/*     */       Translator t;
/*     */       
/*     */       Element(T x, Translator t) {
/*  66 */         this.x = x;
/*  67 */         this.t = t;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */     MyPainter()
/*     */     {
/*  74 */       this._roadElements = new ArrayList();
/*  75 */       this._lightElements = new ArrayList();
/*     */     }
/*     */     
/*  78 */     void addLight(Light x, Translator t) { this._lightElements.add(new Element(x, t)); }
/*     */     
/*     */     void addRoad(Road x, Translator t) {
/*  81 */       this._roadElements.add(new Element(x, t));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     public void paint(Graphics g)
/*     */     {
/*  89 */       for (Element<Light> e : this._lightElements)
/*     */       {
/*  91 */         if (((Light)e.x).getColor() == LightColor.GREEN)
/*     */         {
/*  93 */           g.setColor(Color.GREEN);
/*     */ 
/*     */ 
/*     */         }
/*  97 */         else if (((Light)e.x).getColor() == LightColor.YELLOW)
/*     */         {
/*  99 */           g.setColor(Color.YELLOW);
/*     */         }
/*     */         else
/*     */         {
/* 103 */           g.setColor(Color.RED);
/*     */         }
/*     */         
/*     */ 
/* 107 */         XGraphics.fillOval(g, e.t, 0.0D, 0.0D, MP.carLength, VP.elementWidth);
/*     */       }
/* 109 */       g.setColor(Color.BLACK);
/* 110 */       for (Element<Road> e : this._roadElements) {
/* 111 */         XGraphics.fillRect(g, e.t, 0.0D, 0.0D, MP.roadDrawLength, VP.elementWidth);
/*     */       }
/*     */       
/*     */ 
/* 115 */       for (Element<Road> e : this._roadElements)
/*     */       {
/* 117 */         for (Car d : (Car[])((Road)e.x).getCars().toArray(new Car[0])) {
/* 118 */           g.setColor(d.getColor());
/* 119 */           XGraphics.fillOval(g, e.t, d.getPosition() / (d.getOberver().getLength() / MP.roadDrawLength), 0.0D, d.getCarLength() / (d.getOberver().getLength() / MP.roadDrawLength), VP.elementWidth);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }