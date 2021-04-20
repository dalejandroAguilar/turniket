package com.rodion.turniket.screens.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.rodion.turniket.basics.BasicScreen;
import com.rodion.turniket.screens.level.stages.PageStage;
import com.rodion.turniket.utilities.Difficulty;
import com.rodion.turniket.utilities.LevelManagerMaster;

public class BookLevel {
    private boolean onMoving;
    private PageStage previousPage;
    private PageStage page;
    private PageStage nextPage;
    private ScreenViewport screenViewport;
    private BasicScreen basicScreen;

    public BookLevel(ScreenViewport screenViewport, BasicScreen basicScreen) {
        onMoving = false;
        this.screenViewport = screenViewport;
        this.basicScreen = basicScreen;



        if(LevelManagerMaster.getPreviousPage() > -1)
            previousPage = new PageStage(LevelManagerMaster.getPreviousPage(), screenViewport,
                    basicScreen){
            @Override
            public void onPickLevel() {
                super.onPickLevel();
                BookLevel.this.onPickLevel();
            }
        };
        else
            previousPage = null;


        page = new PageStage(LevelManagerMaster.getPage(), screenViewport, basicScreen){
            @Override
            public void onPickLevel() {
                super.onPickLevel();
                BookLevel.this.onPickLevel();
            }
        };
        nextPage = new PageStage(LevelManagerMaster.getNextPage(), screenViewport, basicScreen){
            @Override
            public void onPickLevel() {
                super.onPickLevel();
                BookLevel.this.onPickLevel();
            }
        };

//        page.addAction(Actions.scaleBy(2,2,1));

    }

    public boolean onPrevious() {
        if (LevelManagerMaster.goBackwardPage()) {
            onMoving = true;
            previousPage.addAction(Actions.moveTo(0, 0, 0.5f));
            page.addAction(Actions.sequence(
                    Actions.moveBy(Gdx.graphics.getWidth(), 0, 0.5f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            nextPage = null;
                            nextPage = page;
                            page = null;
                            page = previousPage;
                            previousPage = null;
                            if (LevelManagerMaster.getPreviousPage() != -2) {
                                previousPage = new PageStage(LevelManagerMaster.getPreviousPage(),
                                        screenViewport, basicScreen){
                                    @Override
                                    public void onPickLevel() {
                                        super.onPickLevel();
                                        BookLevel.this.onPickLevel();
                                    }
                                };
                                previousPage.resize(Gdx.graphics.getWidth(),
                                        Gdx.graphics.getHeight());
                                previousPage.addAction(Actions.moveBy(-Gdx.graphics.getWidth(),
                                        0));
                            }
                            onMoving = false;
                        }
                    })));
            return true;
        }
        return false;
    }

    public boolean onNext() {
        if (LevelManagerMaster.goForwardPage()) {
            onMoving = true;
            nextPage.addAction(Actions.moveTo(0, 0, 0.5f));
            page.addAction(Actions.sequence(
                    Actions.moveBy(-Gdx.graphics.getWidth(), 0, 0.5f),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            previousPage = null;
                            previousPage = page;
                            page = null;
                            page = nextPage;
                            nextPage = null;
                            if (LevelManagerMaster.getNextPage() != -2) {
                                nextPage = new PageStage(LevelManagerMaster.getNextPage(),
                                        screenViewport, basicScreen){
                                    @Override
                                    public void onPickLevel() {
                                        super.onPickLevel();
                                        BookLevel.this.onPickLevel();
                                    }
                                };
                                nextPage.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                                nextPage.addAction(Actions.moveBy(Gdx.graphics.getWidth(),
                                        0));
                            }
                            onMoving = false;
                        }
                    })));
            return true;
        }
        return false;
    }

    public void render(float delta) {
        if (previousPage != null) {
            previousPage.act();
            previousPage.draw();
        }
        page.act();
        page.draw();
        if (nextPage != null) {
            nextPage.act();
            nextPage.draw();
        }
    }

    public void resize(int width, int height) {
        if (previousPage != null)
            previousPage.resize(width, height);
        page.resize(width, height);
        if (nextPage != null)
            nextPage.resize(width, height);
        if (!onMoving) {
            if (previousPage != null)
                previousPage.addAction(Actions.moveBy(-Gdx.graphics.getWidth(), 0));
            if (nextPage != null)
                nextPage.addAction(Actions.moveBy(Gdx.graphics.getWidth(), 0));
        }
    }

    public PageStage getPreviousPage() {
        return previousPage;
    }

    public PageStage getPage() {
        return page;
    }

    public PageStage getNextPage() {
        return nextPage;
    }

    public boolean isOnMoving() {
        return onMoving;
    }

    public void onPickLevel(){
        System.out.println("external onpick");
    }
}
