package com.kebaptycoon.view.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.kebaptycoon.controller.menuControllers.MenuController;
import com.kebaptycoon.controller.menuControllers.StockMenuController;
import com.kebaptycoon.model.entities.Ingredient;
import com.kebaptycoon.utils.Globals;
import com.kebaptycoon.utils.Pair;
import com.kebaptycoon.view.screens.GameScreen;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;

public class StockMenu extends Menu {

    private ArrayList<Pair<Ingredient, Integer>> ingredients;
    public StockMenu(GameScreen gameScreen) {

        super(gameScreen);
        MenuController mc = new StockMenuController(gameScreen, this);
        GestureDetector gd = new GestureDetector(mc);
        InputProcessor ip = mc;
        InputMultiplexer mul = new InputMultiplexer(gd, ip);

        super.menuController = mul;
        Gdx.input.setInputProcessor(menuController);

        currentPage = 0;

        ingredients = gameScreen.getGameLogic().getMarketManager().getIngredients();

    }

    @Override
    public void render(SpriteBatch batch, Viewport viewPort) {

        batch.begin();
        batch.draw(resourceManager.textures.get("menu_backgrounds_stockBackground"), 300, 300);

        int y = 920;

        int  min = Math.min((currentPage + 1) * 8, ingredients.size());

        for (int i = currentPage * 8; i < min; i++) {

            Pair<Ingredient, Integer> ingredient = ingredients.get(i);
            if(i%4 == 0)
                y -= 240;
            batch.draw(resourceManager.textures.get("ingredients_" + ingredient.getLeft()),
                    530 + (i%4) * 240, y, 100, 100);
            heading3Font.draw(batch, ingredient.getRight() + " TL ", 530 + (i%4) * 240 + 30, y - 30);
            heading3Font.draw(batch, gameScreen.getCurrentVenue().getStock(ingredient.getLeft()) + "", 560 + (i%4) * 240, y - 60);
            batch.draw(resourceManager.textures.get("menu_plus2"),  620 +  (i%4) * 240, y - 80, 25 , 25);
        }

        batch.end();

    }

    public void changeCurrentPage(int delta) {
        int pages = ((ingredients.size() - 1) / 8) + 1;
        this.currentPage = (currentPage + pages + delta) % pages;
    }

}
