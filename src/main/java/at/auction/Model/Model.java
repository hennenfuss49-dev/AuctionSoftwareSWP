package at.auction.Model;

import at.auction.View.ViewFactory;

public class Model {

    private final ViewFactory factory;

    static Model model;

    public Model(){
        factory = new ViewFactory();
    }

    public ViewFactory getViewFactory() {
        return factory;
    }

    public static Model getInstance(){
        if(model == null)
            model = new Model();
        return model;
    }

}
