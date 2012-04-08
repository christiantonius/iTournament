/*
 * @(#)LayoutStyle.java 
 *
 * Copyright 2006 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.eclipse.wb.swt.layout.grouplayout;

import org.eclipse.swt.SWT;

/**
 * LayoutStyle is used to determine how much space to place between components during layout.
 * LayoutStyle can be obtained for two components, or for a component relative to an edge of a
 * parent container. The amount of space can vary depending upon whether or not the components are
 * logically grouped together (<code>RELATED</code>).
 * <p>
 * This class is primarily useful for JREs prior to 1.6. In 1.6 API for this was added to Swing.
 * When run on a JRE of 1.6 or greater this will call into the appropriate methods in Swing.
 * 
 * @version $Revision: 1.4 $
 */
public class LayoutStyle {
  /**
   * Possible argument to getPreferredGap. Used to indicate the two componets are grouped together.
   */
  public static final int RELATED = 0;
  /**
   * Possible argument to getPreferredGap. Used to indicate the two componets are not grouped
   * together.
   */
  public static final int UNRELATED = 1;
  /**
   * Possible argument to getPreferredGap. Used to indicate the distance to indent a component is
   * being requested. To visually indicate a set of related components they will often times be
   * horizontally indented, the <code>INDENT</code> constant for this. For example, to indent a
   * check box relative to a label use this constant to <code>getPreferredGap</code>.
   */
  public static final int INDENT = 3;
  private static LayoutStyle layoutStyle;

  /**
   * Factory methods for obtaining the current <code>LayoutStyle</code> object appropriate for the
   * current look and feel.
   * 
   * @return the current LayoutStyle instance
   */
  public static LayoutStyle getSharedInstance() {
    if (layoutStyle == null) {
      String platform = SWT.getPlatform();
      if ("win32".equalsIgnoreCase(platform)) {
        layoutStyle = new WindowsLayoutStyle();
      } else {
        layoutStyle = new LayoutStyle();
      }
    }
    return layoutStyle;
  }

  /**
   * Returns the amount of space to use between two components. The return value indicates the
   * distance to place <code>component2</code> relative to <code>component1</code>. For example, the
   * following returns the amount of space to place between <code>component2</code> and
   * <code>component1</code> when <code>component2</code> is placed vertically above
   * <code>component1</code>:
   * 
   * <pre>
     *   int gap = getPreferredGap(component1, component2,
     *                             LayoutStyle.RELATED,
     *                             SwingConstants.NORTH, parent);
     * </pre>
   * 
   * The <code>type</code> parameter indicates the type of gap being requested. It can be one of the
   * following values:
   * <table>
   * <tr>
   * <td><code>RELATED</code>
   * <td>If the two components will be contained in the same parent and are showing similar
   * logically related items, use <code>RELATED</code>.
   * <tr>
   * <td><code>UNRELATED</code>
   * <td>If the two components will be contained in the same parent but show logically unrelated
   * items use <code>UNRELATED</code>.
   * <tr>
   * <td><code>INDENT</code>
   * <td>Used to obtain the preferred distance to indent a component relative to another. For
   * example, if you want to horizontally indent a JCheckBox relative to a JLabel use
   * <code>INDENT</code>. This is only useful for the horizontal axis.
   * </table>
   * <p>
   * It's important to note that some look and feels may not distinguish between
   * <code>RELATED</code> and <code>UNRELATED</code>.
   * <p>
   * The return value is not intended to take into account the current size and position of
   * <code>component2</code> or <code>component1</code>. The return value may take into
   * consideration various properties of the components. For example, the space may vary based on
   * font size, or the preferred size of the component.
   * 
   * @param source
   *          the <code>Control</code> <code>component2</code> is being placed relative to
   * @param target
   *          the <code>Control</code> being placed
   * @param type
   *          how the two components are being placed
   * @param position
   *          the position <code>component2</code> is being placed relative to
   *          <code>component1</code>; one of <code>SwingConstants.NORTH</code>,
   *          <code>SwingConstants.SOUTH</code>, <code>SwingConstants.EAST</code> or
   *          <code>SwingConstants.WEST</code>
   * @param parent
   *          the parent of <code>component2</code>; this may differ from the actual parent and may
   *          be null
   * @return the amount of space to place between the two components
   * @throws IllegalArgumentException
   *           if <code>position</code> is not one of <code>SwingConstants.NORTH</code>,
   *           <code>SwingConstants.SOUTH</code>, <code>SwingConstants.EAST</code> or
   *           <code>SwingConstants.WEST</code>; <code>type</code> not one of <code>INDENT</code>,
   *           <code>RELATED</code> or <code>UNRELATED</code>; or <code>component1</code> or
   *           <code>component2</code> is null
   */
  public int getPreferredGap(Class<?> source,
      int sourceStyle,
      Class<?> target,
      int targetStyle,
      int type,
      int position) {
    if (position != SWT.UP && position != SWT.DOWN && position != SWT.LEFT && position != SWT.RIGHT) {
      throw new IllegalArgumentException("Invalid position");
    }
    if (source == null || target == null) {
      throw new IllegalArgumentException("Components must be non-null");
    }
    if (type == RELATED) {
      return 6;
    } else if (type == UNRELATED) {
      return 12;
    } else if (type == INDENT) {
      return 6;
    }
    throw new IllegalArgumentException("Invalid type");
  }

  /**
   * Returns the amount of space to position a component inside its parent.
   * 
   * @param controlClass
   *          the <code>Component</code> being positioned
   * @param position
   *          the position <code>component</code> is being placed relative to its parent; one of
   *          <code>SwingConstants.NORTH</code>, <code>SwingConstants.SOUTH</code>,
   *          <code>SwingConstants.EAST</code> or <code>SwingConstants.WEST</code>
   * @param parent
   *          the parent of <code>component</code>; this may differ from the actual parent and may
   *          be null
   * @return the amount of space to place between the component and specified edge
   * @throws IllegalArgumentException
   *           if <code>position</code> is not one of <code>SwingConstants.NORTH</code>,
   *           <code>SwingConstants.SOUTH</code>, <code>SwingConstants.EAST</code> or
   *           <code>SwingConstants.WEST</code>; or <code>component</code> is null
   */
  public int getContainerGap(Class<?> controlClass, int controlStyle, int position) {
    if (position != SWT.UP && position != SWT.DOWN && position != SWT.LEFT && position != SWT.RIGHT) {
      throw new IllegalArgumentException("Invalid position");
    }
    if (controlClass == null) {
      throw new IllegalArgumentException("Component must be non-null");
    }
    return 12;
  }
}
