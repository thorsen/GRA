
package RA;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.axis.AxisState;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryTick;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.text.G2TextMeasurer;
import org.jfree.text.TextBlock;
import org.jfree.text.TextUtilities;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;


public class Etiqueta extends CategoryAxis{
   private static final int DEFAULT_INTERVAL = 1; 
   private int m_interval; 

  /** Default constructor. */ 
  public Etiqueta() 
  { 
    this(null, DEFAULT_INTERVAL); 
  } 

  /** 
   * Constructs an axis with a label. 
   * @param label Axis label (may be null). 
   */ 
  public Etiqueta(String label) 
  { 
    this(label, DEFAULT_INTERVAL); 
  } 

  /** 
   * Constructs a category axis with a label and an interval. 
   * @param label Axis label (may be null). 
   * @param interval This number controls the labels to be printed. 
   * For instance, if <code>interval = 1</code>, all labels are printed; if 
   * <code>interval = 10</code>, only one of every 10 labels are printed (first label 
   * is always printed). 
   */ 
  public Etiqueta(String label, int interval) 
  { 
    super(label); 
    m_interval = interval; 
  } 

  /** 
   * Draws the category labels and returns the updated axis state. 
   * NOTE: This method redefines the corresponding one in <code>CategoryAxis</code>, 
   * and is a copy of that, with added control to skip some labels to be printed. 
   * 
   * @param g2 the graphics device (<code>null</code> not permitted). 
   * @param dataArea the area inside the axes (<code>null</code> not 
   *          permitted). 
   * @param edge the axis location (<code>null</code> not permitted). 
   * @param state the axis state (<code>null</code> not permitted). 
   * @param plotState collects information about the plot (<code>null</code> 
   *          permitted). 
   * 
   * @return The updated axis state (never <code>null</code>). 
   */ 
  
  protected TextBlock createLabel(Comparable category, float width, 
                                        RectangleEdge edge, Graphics2D g2) {
     String l="";
      if(!(category.toString().startsWith("a"))){
        l=category.toString();
      }
      
      TextBlock label = TextUtilities.createTextBlock(l,
              getTickLabelFont(category), getTickLabelPaint(category), width,
              1, new G2TextMeasurer(g2));
      return label; 
   }

  
  protected AxisState drawCategoryLabels(Graphics2D g2, Rectangle2D plotArea, 
                                         Rectangle2D dataArea,RectangleEdge edge, AxisState state, 
                                         PlotRenderingInfo plotState) 
  { 
    if (state == null) 
    { 
      throw new IllegalArgumentException("Null 'state' argument."); 
    } 

    if (isTickLabelsVisible()) 
    { 
      g2.setFont(getTickLabelFont()); 
      g2.setPaint(getTickLabelPaint()); 
      List ticks = refreshTicks(g2, state, dataArea, edge); 
      state.setTicks(ticks); 

      int categoryIndex = 0; 
      Iterator iterator = ticks.iterator(); 
      while (iterator.hasNext()) 
      { 
        CategoryTick tick = (CategoryTick) iterator.next(); 
        g2.setPaint(getTickLabelPaint()); 

        CategoryLabelPosition position = getCategoryLabelPositions() 
            .getLabelPosition(edge); 
        double x0 = 0.0; 
        double x1 = 0.0; 
        double y0 = 0.0; 
        double y1 = 0.0; 
        if (edge == RectangleEdge.TOP) 
        { 
          x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge); 
          x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge); 
          y1 = state.getCursor() - getCategoryLabelPositionOffset(); 
          y0 = y1 - state.getMax(); 
        } 
        else if (edge == RectangleEdge.BOTTOM) 
        { 
          x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge); 
          x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge); 
          y0 = state.getCursor() + getCategoryLabelPositionOffset(); 
          y1 = y0 + state.getMax(); 
        } 
        else if (edge == RectangleEdge.LEFT) 
        { 
          y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge); 
          y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge); 
          x1 = state.getCursor() - getCategoryLabelPositionOffset(); 
          x0 = x1 - state.getMax(); 
        } 
        else if (edge == RectangleEdge.RIGHT) 
        { 
          y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge); 
          y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge); 
          x0 = state.getCursor() + getCategoryLabelPositionOffset(); 
          x1 = x0 - state.getMax(); 
        } 
        Rectangle2D area = new Rectangle2D.Double(x0, y0, (x1 - x0), (y1 - y0)); 
        Point2D anchorPoint = 
          RectangleAnchor.coordinates(area, position.getCategoryAnchor()); 

        // THIS CODE IS NOW CONTROLLED BY THE "IF" ============= 
        if (categoryIndex % m_interval == 0) 
        { 
            
          TextBlock block = tick.getLabel(); 
          block.draw(g2, (float) anchorPoint.getX(), (float) anchorPoint.getY(), 
                     position.getLabelAnchor(), (float) anchorPoint.getX(), 
                     (float) anchorPoint.getY(), position.getAngle()); 
          Shape bounds = block.calculateBounds(g2, (float) anchorPoint.getX(), 
                                               (float) anchorPoint.getY(), 
                                               position.getLabelAnchor(), 
                                               (float) anchorPoint.getX(), 
                                               (float) anchorPoint.getY(), 
                                               position.getAngle()); 
          if (plotState != null) 
          { //Daba problemas para exportar la imagen
            /*EntityCollection entities = plotState.getOwner().getEntityCollection(); 
            if (entities != null) 
            { 
              //String tooltip = (String) categoryLabelToolTips.get(tick.getCategory()); 
              String tooltip = null; 
              entities.add(new TickLabelEntity(bounds, tooltip, null)); 
            } */
          } 
        } 
        // END IF ======================================== 

        categoryIndex++; 
      } 

      if (edge.equals(RectangleEdge.TOP)) 
      { 
        double h = state.getMax(); 
        state.cursorUp(h); 
      } 
      else if (edge.equals(RectangleEdge.BOTTOM)) 
      { 
        double h = state.getMax(); 
        state.cursorDown(h); 
      } 
      else if (edge == RectangleEdge.LEFT) 
      { 
        double w = state.getMax(); 
        state.cursorLeft(w); 
      } 
      else if (edge == RectangleEdge.RIGHT) 
      { 
        double w = state.getMax(); 
        state.cursorRight(w); 
      } 
    } 
    return state; 
  } 
} 


  
 


    
    
   
    

