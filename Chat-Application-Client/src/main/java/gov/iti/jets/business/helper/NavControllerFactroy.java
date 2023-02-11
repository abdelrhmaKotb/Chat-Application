package gov.iti.jets.business.helper;

import gov.iti.jets.presentation.controllers.ContactsController;

public class NavControllerFactroy {
    private NavControllerFactroy() {
    }

    public static Object getController(String name) throws Exception {
        switch (name) {
            case "contacts":
                return new ContactsController();
            default:
                throw new Exception("no controller found");
        }
    }
}
