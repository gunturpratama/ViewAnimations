package net.kibotu.android.view.animations.ui;

import android.graphics.PointF;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.Bind;
import net.kibotu.android.view.animations.R;
import net.kibotu.android.view.animations.exo.AnimationGenerator;
import net.kibotu.android.view.animations.exo.EXOAnimationScreenConfig;
import net.kibotu.android.view.animations.exo.EXOAnimationsCollection;
import net.kibotu.android.view.animations.exo.EXOImageView;
import net.kibotu.android.view.animations.exo.elements.*;
import net.kibotu.android.view.animations.exo.interpolation.EXOAnimationCurveCosineInOut;

import java.util.ArrayList;

/**
 * Created by Nyaruhodo on 30.03.2016.
 */
public class ComplexAnimationFragment extends BaseFragment {

    @Bind(R.id.layout)
    ViewGroup layout;

    @Override
    protected void onViewCreated() {
        createJellySplashScreenScene();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_exoanim;
    }

    public void createJellySplashScreenScene() {

        EXOAnimationsCollection collection = new EXOAnimationsCollection(getContext(), layout);

        // HEY JAN: ich hab die Bilder um die H�lfte runterskaliert.. 50%
        collection.layout = layout;
        // hier muss ein bisschen mit den parametern rumgespielt werden
        // in dem intellij project functionieren die parameter mit 1.0 aber irgendwie is die view die �bergeben wird (das relativelayout anders)
        // wenn es probleme mit den animationen geben sollte
        // die repeat animation is nicht 100% perfekt programmiert
        // versuchen animationen nicht zu reusen (manchmal ist es besser einfach eine neue instanz zu generieren)
        // in der lines activity in showJellySplashScreen the calling command for this function is commented out / reenable it
        // 256 color pngs process way (ultra)faster and smaller imagesizes dont hurt too..
        EXOAnimationScreenConfig screen = EXOAnimationScreenConfig.getResolution();
        AnimationGenerator.screen = screen;

        double logoFadeInTime = 0.5;

        addImage(355, 576, R.drawable.bg_intro, screen);               // bg 355*576
        EXOImageView ray = addImage(314, 242, R.drawable.ray, screen);              // ray 314*242
        int particleCount = 24;
        for (int i = 0; i < particleCount; ++i) {
            EXOImageView particle = addImage(314, 242, R.drawable.particle, screen);
            AnimationGenerator generator = AnimationGenerator.create();
            double angle = Math.random() * 2.0 * Math.PI;

            generator.addAnimation(AnimationElementWaitInvisible.create(0.0, logoFadeInTime + Math.random() * 2.0).appendAnimation(AnimationElementRepeat.create(20.0, AnimationElementMove.create(0, 2, 0, 0, Math.sin(angle) * 600.0, Math.cos(angle) * 600.0).addAnimation(AnimationElementFadeOut.create(0, 2)))));
            collection.runAnimation(particle, 5.0, generator);
        }

        double sy = -800;

        EXOImageView green = addImage(355 + 90, 596 - 30 * 2, R.drawable.jelly_green, screen);      // jelly_green 355*576
        EXOImageView red = addImage(355 + 225, 596 - 25 * 3, R.drawable.jelly_red, screen);        // jelly_red 355*576
        EXOImageView pink = addImage(355 + 150, 596 - 20 * 2, R.drawable.jelly_pink, screen);       // jelly_pink 355*576
        EXOImageView blue = addImage(355 + 70, 596, R.drawable.jelly_blue, screen);       // jelly_blue 355*576
        EXOImageView yellow = addImage(355 + 300, 596, R.drawable.jelly_yellow, screen);     // jelly_yellow 355*576
        EXOImageView logo = addImage(294, 268, R.drawable.logo_intro, screen);             // logo 294*268
        EXOImageView kakaoText = addImage(239 + 20, 530, R.drawable.speech_ballon, screen);    // speech_ballon 239*530
        EXOImageView tree1 = addImage(51, 564, R.drawable.tree_ol_1, screen);         // tree_ol_1 051*564
        EXOImageView tree2 = addImage(909, 111, R.drawable.tree_ol_2, screen);        // tree_ol_2 909*111
        EXOImageView tree3 = addImage(728, 0, R.drawable.tree_ol_3, screen);          // tree_ol_3 728*000
        EXOImageView critter1 = addImage(67, 1080, R.drawable.critter_1, screen);        // critter_1 067*1080
        EXOImageView critter4 = addImage(209, 894, R.drawable.critter_4, screen);        // critter_4 209*894
        EXOImageView critter2 = addImage(435, 1067, R.drawable.critter_2, screen);       // critter_2 435*1067
        EXOImageView critter3 = addImage(845, 1135, R.drawable.critter_3, screen);       // critter_3 845*1135

        ArrayList<PointF> points = new ArrayList<>(100);
        points.add(new PointF(0, 0));
        for (int i = 0; i < 100; ++i) {
            PointF point = new PointF();
            point.x = (float) (Math.random() * 240.f);
            point.y = (float) (Math.random() * 400.f);
            points.add(point);
        }

        double depth = 0.325;
        double wtime = 32;
        AnimationElement wiggleAlone = AnimationElementRepeat.create(wtime / 0.75, AnimationElementWiggle.create(0, 0.75, 15.0 * depth)).addAnimation(AnimationElementRepeat.create(wtime / 1.0, AnimationElementWobble.create(0, 1, 0.3 * depth).applyCurve(EXOAnimationCurveCosineInOut.create(0.5, 0.5))));

        AnimationGenerator wiggle = (AnimationGenerator) AnimationGenerator.create()
                .addAnimation(wiggleAlone)
                .addAnimation(AnimationElementRepeat.create(wtime / (0.5 + 0.7), AnimationElementJump.create(0, 0.5, 30.0).appendAnimation(AnimationElementJump.create(0, 0.7, 50.0)))
                );

        double fps = 4.0;
        double wave = 0.3782;

        double staticDelay = 0.2 + logoFadeInTime;
        double varDelay = 0.2;

        double d1 = staticDelay + varDelay * 0.0;
        double d2 = staticDelay + varDelay * 1.0;
        double d3 = staticDelay + varDelay * 2.0;
        double d4 = staticDelay + varDelay * 3.0;
        double d5 = staticDelay + varDelay * 4.0;

        collection.runAnimation(blue, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 0.0, true).appendAnimation(AnimationElementMove.create(0.0, d1, 0, sy, 0, 0).addAnimation(AnimationElementFadeIn.create(0.0, d1))).appendAnimation(wiggle));
        collection.runAnimation(green, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 1.0, true).appendAnimation(AnimationElementMove.create(0.0, d2, 0, sy, 0, 0).addAnimation(AnimationElementFadeIn.create(0.0, d2))).appendAnimation(wiggle));
        collection.runAnimation(pink, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 2.0, true).appendAnimation(AnimationElementMove.create(0.0, d3, 0, sy, 0, 0).addAnimation(AnimationElementFadeIn.create(0.0, d3))).appendAnimation(wiggle));
        collection.runAnimation(red, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 3.0, true).appendAnimation(AnimationElementMove.create(0.0, d4, 0, sy, 0, 0).addAnimation(AnimationElementFadeIn.create(0.0, d4))).appendAnimation(wiggle));
        collection.runAnimation(yellow, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 4.0, true).appendAnimation(AnimationElementMove.create(0.0, d5, 0, sy, 0, 0).addAnimation(AnimationElementFadeIn.create(0.0, d5))).appendAnimation(wiggle));

        wave = 0.1;
        double sy2 = 200;
        collection.runAnimation(critter1, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 0.0, true).appendAnimation(AnimationElementMove.create(0.0, d1, 0, sy2, 0, 0)).appendAnimation(wiggle));
        collection.runAnimation(critter2, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 1.0, true).appendAnimation(AnimationElementMove.create(0.0, d2, 0, sy2, 0, 0)).appendAnimation(wiggle));
        collection.runAnimation(critter3, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 2.0, true).appendAnimation(AnimationElementMove.create(0.0, d3, 0, sy2, 0, 0)).appendAnimation(wiggle));
        collection.runAnimation(critter4, fps, (AnimationGenerator) AnimationGenerator.create().preDelay(wave * 3.0, true).appendAnimation(AnimationElementMove.create(0.0, d4, 0, sy2, 0, 0)).appendAnimation(wiggleAlone));

        collection.runAnimation(logo, 10.0, (AnimationGenerator) AnimationGenerator.create()
                .addAnimation(AnimationElementMove.create(0, logoFadeInTime, 0, -500, 0, 0).applyCurve(EXOAnimationCurveCosineInOut.create(0, 0.125))).addAnimation(AnimationElementScale.create(0, logoFadeInTime, 2, 2, 1, 1)).addAnimation(AnimationElementRotate.create(0, logoFadeInTime, -35, 0))
                .addAnimation(AnimationElementFadeIn.create(0, logoFadeInTime).applyCurve(EXOAnimationCurveCosineInOut.create(0, 0.125)))
                .appendAnimation(AnimationElementRepeat.create(10.0, AnimationElementWiggle.create(0, 3, 2.5))));
        collection.runAnimation(ray, fps, (AnimationGenerator) AnimationGenerator.create().addAnimation(AnimationElementRotate.create(0, 80, 0, 12 * 360)).addAnimation(AnimationElementFadeIn.create(0, 2.0)));
        collection.runAnimation(kakaoText, fps * 1.5, (AnimationGenerator) AnimationGenerator.create().appendAnimation(AnimationElementWaitInvisible.create(0.0, staticDelay + varDelay * 1.0)).
                appendAnimation(AnimationElementMove.create(0.0, varDelay * 1.0, 0, sy, 0, 0)).
                addAnimation(AnimationElementRepeat.create(wtime / 1, AnimationElementBlink.create(0, 1, 0.2)).addAnimation(AnimationElementRepeat.create(wtime / 1, AnimationElementWobble.create(0, 0.5, 0.1).addAnimation(AnimationElementJump.create(0, 1.0, 30))))));

        tree1.anchorY = 1.0;
        tree2.anchorY = 1.0;
        tree3.anchorY = 1.0;
        collection.runAnimation(tree1, fps, (AnimationGenerator) AnimationGenerator.create().addAnimation(AnimationElementRepeat.create(10.0, AnimationElementWiggle.create(0, 1, 5.0))));
        collection.runAnimation(tree2, fps, (AnimationGenerator) AnimationGenerator.create().addAnimation(AnimationElementRepeat.create(10.0, AnimationElementWiggle.create(0, 1, 5.0))));
        collection.runAnimation(tree3, fps, (AnimationGenerator) AnimationGenerator.create().addAnimation(AnimationElementRepeat.create(10.0, AnimationElementWiggle.create(0, 1, 5.0))));
    }

    public EXOImageView addImage(final double x, final double y, final int resourceId, final EXOAnimationScreenConfig screen) {
        System.out.print("EXOAnimations: Adding an image for the Resource:" + resourceId + "\n");
        EXOImageView img = new EXOImageView(getContext());
        img.setName("resId:" + resourceId);
        //img.setDrawingCacheEnabled(true);
        //img.setDrawingCacheQuality(EXOImageView.DRAWING_CACHE_QUALITY_LOW);
        img.setScaleType(EXOImageView.ScaleType.FIT_XY); //(correct aspect ratio later)
        img.setImageResource(resourceId);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.width = (int) screen.imageViewScaleX(img.getDrawable().getIntrinsicWidth());
        lp.height = (int) screen.imageViewScaleY(img.getDrawable().getIntrinsicHeight());
        double posX = screen.imageViewPositionX(x);
        double posY = screen.imageViewPositionY(y);
        lp.setMargins((int) posX - lp.width / 2, (int) posY - lp.height / 2, -lp.width / 2, -lp.height / 2);
        img.setAdjustViewBounds(true);
        img.setLayoutParams(lp);
        layout.addView(img);
        return img;
    }
}
