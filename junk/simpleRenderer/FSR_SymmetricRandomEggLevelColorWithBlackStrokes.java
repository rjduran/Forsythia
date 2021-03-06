package org.fleen.forsythia.junk.simpleRenderer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

import org.fleen.forsythia.core.composition.FPolygon;
import org.fleen.forsythia.core.composition.ForsythiaComposition;

public class FSR_SymmetricRandomEggLevelColorWithBlackStrokes extends ForsythiaSimpleRenderer_Abstract{
 
  private static final long serialVersionUID=-7451576959261610292L;
  
  /*
   * ################################
   * CONSTRUCTOR
   * no params. it's supersimple
   * ################################
   */
  
  public FSR_SymmetricRandomEggLevelColorWithBlackStrokes(){
    super();}
  
  /*
   * ################################
   * COLOR
   * a nice random symmetric palette that goes well wit hthe black strokes
   * ################################
   */
  
  private static final Color STROKECOLOR=new Color(0,0,0);
  
//  Map<FPolygonSignature,Color> colorbysig=new Hashtable<FPolygonSignature,Color>();
//  
  private Random rnd=new Random();
//  
//  public Color getColor(FPolygon polygon){
//    Color c=colorbysig.get(polygon.getSignature());
//    if(c==null){
//      c=getRandomColor();
//      colorbysig.put(polygon.getSignature(),c);}
//    return c;}
  
  Map<Integer,Color> colorbylevel=new Hashtable<Integer,Color>();
  
  public Color getColor(FPolygon polygon){
    Color c=colorbylevel.get(getTagDepth(polygon,"egg"));
    if(c==null){
      c=getRandomColor();
      colorbylevel.put(getTagDepth(polygon,"egg"),c);}
    return c;}
  
  
  
  private Color getRandomColor(){
    Color c=new Color(64+rnd.nextInt(12)*16,64+rnd.nextInt(12)*16,64+rnd.nextInt(12)*16);
    return c;}
  
  /*
   * ################################
   * STROKE
   * ################################
   */
  
  private static final float STROKEWIDTH_DEFAULT=0.01f;
  private float strokewidth=STROKEWIDTH_DEFAULT;
  
  private Stroke createStroke(){
    Stroke stroke=new BasicStroke(strokewidth,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND,0,null,0);
    return stroke;}
  
  /*
   * ################################
   * RENDER 
   * ################################
   */
  
  protected void render(ForsythiaComposition forsythia,Graphics2D graphics,AffineTransform transform){
    Path2D path;
    //FILL LEAF POLYGONS
    Color color;
    for(FPolygon polygon:forsythia.getLeafPolygons()){
      color=getColor(polygon);
      graphics.setPaint(color);
      path=getPath2D(polygon);
      graphics.fill(path);}
    //STROKE LEAF POLYGONS
    graphics.setPaint(STROKECOLOR);
    graphics.setStroke(createStroke());
    for(FPolygon polygon:forsythia.getLeafPolygons()){
      path=getPath2D(polygon);
      graphics.draw(path);}
    //
//    colorbysig.clear();
    colorbylevel.clear();
    }
  
}
