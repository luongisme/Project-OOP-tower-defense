package HelperMethod;

public class Utilz {
    public static int GetHypoDistance(float x1, float y1, float x2, float y2) {
        float xDiff = Math.abs(x1 - x2);
        float yDiff = Math.abs(y1 - y2);
       
        return (int) Math.hypot(xDiff, yDiff);
    }

    
}

