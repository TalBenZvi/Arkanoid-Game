public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    public AnimationRunner getAnimationRunner() {
        return this.runner;
    }

    public Animation getHighScoresAnimation() {
        return this.highScoresAnimation;
    }

    public Void run() {
        this.getAnimationRunner().run(this.getHighScoresAnimation());
        return null;
    }
}