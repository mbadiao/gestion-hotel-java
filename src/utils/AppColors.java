package utils;

import java.awt.Color;

/**
 * Professional color constants for the hotel management application
 * @author admin
 */
public class AppColors {
    
    // Primary Colors
    public static final Color PRIMARY_DARK = new Color(44, 62, 80);      // #2C3E50 - Navigation, headers
    public static final Color PRIMARY_LIGHT = new Color(52, 152, 219);   // #3498DB - Interactive elements
    
    // Semantic Colors
    public static final Color SUCCESS = new Color(39, 174, 96);          // #27AE60 - Success actions
    public static final Color WARNING = new Color(243, 156, 18);         // #F39C12 - Warning actions
    public static final Color DANGER = new Color(231, 76, 60);           // #E74C3C - Delete/danger actions
    public static final Color INFO = new Color(52, 152, 219);            // #3498DB - Info actions
    
    // Background Colors
    public static final Color BACKGROUND_LIGHT = new Color(236, 240, 241); // #ECF0F1 - Main background
    public static final Color BACKGROUND_WHITE = new Color(255, 255, 255);  // #FFFFFF - Cards, panels
    public static final Color BACKGROUND_HEADER = new Color(44, 62, 80);    // #2C3E50 - Header background
    public static final Color BACKGROUND_HOVER = new Color(233, 236, 239);  // #E9ECEF - Hover background
    
    // Text Colors
    public static final Color TEXT_DARK = new Color(44, 62, 80);         // #2C3E50 - Primary text
    public static final Color TEXT_LIGHT = new Color(255, 255, 255);     // #FFFFFF - Text on dark backgrounds
    public static final Color TEXT_SECONDARY = new Color(127, 140, 141); // #7F8C8D - Secondary text
    
    // Border Colors
    public static final Color BORDER_LIGHT = new Color(189, 195, 199);   // #BDC3C7 - Light borders
    public static final Color BORDER_MEDIUM = new Color(149, 165, 166);  // #95A5A6 - Medium borders
    
    // Hover States
    public static final Color HOVER_PRIMARY = new Color(41, 128, 185);   // #2980B9 - Primary hover
    public static final Color HOVER_SUCCESS = new Color(46, 204, 113);   // #2ECC71 - Success hover
    public static final Color HOVER_DANGER = new Color(192, 57, 43);     // #C0392B - Danger hover
    public static final Color HOVER_WARNING = new Color(211, 84, 0);     // #D35400 - Warning hover
    
    // Additional Professional Colors
    public static final Color ACCENT_BLUE = new Color(52, 152, 219);     // #3498DB - Accent blue
    public static final Color ACCENT_GREEN = new Color(39, 174, 96);     // #27AE60 - Accent green
    public static final Color ACCENT_ORANGE = new Color(243, 156, 18);   // #F39C12 - Accent orange
    public static final Color ACCENT_RED = new Color(231, 76, 60);       // #E74C3C - Accent red
    
    // Neutral Colors
    public static final Color NEUTRAL_DARK = new Color(52, 73, 94);      // #34495E - Dark neutral
    public static final Color NEUTRAL_MEDIUM = new Color(149, 165, 166); // #95A5A6 - Medium neutral
    public static final Color NEUTRAL_LIGHT = new Color(236, 240, 241);  // #ECF0F1 - Light neutral
    
    /**
     * Get a color with specified alpha (transparency)
     * @param color The base color
     * @param alpha Alpha value (0-255, where 0 is transparent and 255 is opaque)
     * @return Color with specified alpha
     */
    public static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }
    
    /**
     * Get a lighter version of a color
     * @param color The base color
     * @param factor The lightening factor (0.0 to 1.0)
     * @return Lighter version of the color
     */
    public static Color lighter(Color color, float factor) {
        int red = Math.min(255, (int)(color.getRed() + (255 - color.getRed()) * factor));
        int green = Math.min(255, (int)(color.getGreen() + (255 - color.getGreen()) * factor));
        int blue = Math.min(255, (int)(color.getBlue() + (255 - color.getBlue()) * factor));
        return new Color(red, green, blue, color.getAlpha());
    }
    
    /**
     * Get a darker version of a color
     * @param color The base color
     * @param factor The darkening factor (0.0 to 1.0)
     * @return Darker version of the color
     */
    public static Color darker(Color color, float factor) {
        int red = Math.max(0, (int)(color.getRed() * (1 - factor)));
        int green = Math.max(0, (int)(color.getGreen() * (1 - factor)));
        int blue = Math.max(0, (int)(color.getBlue() * (1 - factor)));
        return new Color(red, green, blue, color.getAlpha());
    }
}
