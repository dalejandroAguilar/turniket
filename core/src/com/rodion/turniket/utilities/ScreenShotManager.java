package com.rodion.turniket.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.ScreenUtils;

import java.nio.ByteBuffer;

public class ScreenShotManager {
    private static Pixmap bg;

    public static void ScreenShotManager() {
//        bg = new Pixmap();
//        try{
//            FileHandle fh;
//            do{
//                fh = new FileHandle(Gdx.files.getLocalStoragePath() + "screenshot" + counter++ + ".png");
//            }while (fh.exists());
//            Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
//            PixmapIO.writePNG(fh, pixmap);
//            pixmap.dispose();
//        }catch (Exception e){
//        }

    }

    public static Pixmap getSS() {
        return bg;
    }

    public static void takeSS() {
        bg = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
    }

    private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown) {
        final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);
//        if (yDown) {
//            // Flip the pixmap upside down
//            ByteBuffer pixels = pixmap.getPixels();
//            int numBytes = w * h * 4;
//            byte[] lines = new byte[numBytes];
//            int numBytesPerLine = w * 4;
//            for (int i = 0; i < h; i++) {
//                pixels.position((h - i - 1) * numBytesPerLine);
//                pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
//            }
//            pixels.clear();
//            pixels.put(lines);
//        }
//        final int width = src.getWidth();
//        final int height = src.getHeight();


        if (yDown) {
            Pixmap flipped = new Pixmap(w, h, pixmap.getFormat());
            for (int xx = 0; xx < w; xx++) {
                for (int yy = 0; yy < h; yy++) {
                    flipped.drawPixel(xx, yy, pixmap.getPixel(xx, h - yy - 1));
                }
            }
            return flipped;
        } else
            return pixmap;
    }
}
