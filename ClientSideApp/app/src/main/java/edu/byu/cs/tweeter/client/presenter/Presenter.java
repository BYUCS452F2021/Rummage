package edu.byu.cs.tweeter.client.presenter;

/*import edu.byu.cs.tweeter.client.model.service.CheckAuthorizedProxyService;
import edu.byu.cs.tweeter.shared.model.service.CheckAuthorizedService;*/

/*
 * Contains the business logic for a presenter class
 */
public abstract class Presenter {

    protected final View view;

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    Presenter(View view) {
        this.view = view;
    }

    /**
     * The interface by which this presenter communicates with its view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

   /* public CheckAuthorizedService getCheckAuthorizedService() { return new CheckAuthorizedProxyService(); }
*/
}
