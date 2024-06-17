package at.auction.Model;

import at.auction.Controller.CardDisplayController;
import at.auction.View.ViewFactory;

public class CardDisplayModel {

    private final CardDisplayController factory;

    static CardDisplayModel model;

    public CardDisplayModel(){
        factory = new CardDisplayController();
    }

    public CardDisplayController getCardDisplayController() {
        return factory;
    }

    public static CardDisplayModel getInstance(){
        if(model == null)
            model = new CardDisplayModel();
        return model;
    }


}
