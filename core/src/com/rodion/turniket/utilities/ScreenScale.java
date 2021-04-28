package com.rodion.turniket.utilities;

import com.rodion.turniket.basics.FactorScale;
import com.rodion.turniket.basics.ScreenSize;

public class ScreenScale {
    private static FactorScale factorScale;


    public static void resize(int width, int height){
        float widthRadio = ((float)(width)) / ((float)(ScreenSize.WIDTH));
        float heightRadio = ((float)(height)) / ((float)(ScreenSize.HEIGHT));
        float ratio = Math.min(widthRadio, heightRadio);
        if (ratio >= FactorScale.UltraHuge.scale) {
            factorScale = FactorScale.UltraHuge;
            return;
        }
        if (ratio <= FactorScale.UltraSmall.scale){
            factorScale = FactorScale.UltraSmall;
            return;
        }

        for (FactorScale enumFactorScale : FactorScale.values()){
            if (enumFactorScale.getScale() > ratio){
                factorScale = FactorScale.get(enumFactorScale.index - 1);
                return;
            }
        }
    }

    public static FactorScale getFactorScale() {
        return factorScale;
    }
}
