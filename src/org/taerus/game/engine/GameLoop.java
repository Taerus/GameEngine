package org.taerus.game.engine;


import javafx.animation.AnimationTimer;

public abstract class GameLoop implements I {

    private AnimationTimer timer;

    private Graphics graphics;

    private double updateRate;
    private double frameRate;

    private long lastTime;

    private double timeSinceLastUpdate;
    private double timeSinceLastFrame;

    public GameLoop(double updateRate, double frameRate) {

        this.updateRate = updateRate;
        this.frameRate = frameRate;
        this.graphics = Graphics.getInstance();

        this.timer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                double elapsedTime = (double) (currentTime - lastTime) / 1000000.0;
                lastTime = currentTime;

                if(getUpdateRate() > 0) {
                    double delta = getUpdateDuration();
                    timeSinceLastUpdate += elapsedTime;
                    while (timeSinceLastUpdate > delta) {
                        update(delta);
                        timeSinceLastUpdate -= delta;
                    }
                } else {
                    update(elapsedTime);
                }

                if(getFrameRate() > 0) {
                    timeSinceLastFrame += elapsedTime;
                    if(timeSinceLastFrame >= getFrameDuration()) {
                        render();
                        timeSinceLastFrame = 0.0;
                    }
                } else {
                    render();
                }
            }
        };

    }

    public GameLoop(double updateRate) {
        this(updateRate, 0.0);
    }

    public GameLoop() {
        this(0.0, 0.0);
    }

    public void start() {
        lastTime = System.nanoTime();
        timeSinceLastUpdate = 0.0;
        timeSinceLastFrame = 0.0;

        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public double getUpdateRate() {
        return updateRate;
    }

    public void setUpdateRate(double updateRate) {
        this.updateRate = updateRate;
    }

    public double getUpdateDuration() {
        return 1000.0 / updateRate;
    }

    public void setUpdateDuration(double updateDuration) {
        this.updateRate = 1000.0 / updateDuration;
    }

    public double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(double frameRate) {
        this.frameRate = frameRate;
    }

    public double getFrameDuration() {
        return 1000.0 / frameRate;
    }

    public void setFrameDuration(double frameDuration) {
        this.frameRate = 1000.0 / frameDuration;
    }
}
