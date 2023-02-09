package gov.iti.jets.business.helper;

import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.CurrentUserModel;

public class ModelsFactory {

    private static ModelsFactory instance;

    private ContactsModel contactsModel;
    private CurrentUserModel currentUserModel;

    private ModelsFactory(){}


    public static  ModelsFactory getInstance()
    {
        if(instance == null)
        {
            return new ModelsFactory();
        }
        return instance;
    }


    public ContactsModel getContactsModel(){

        if(contactsModel == null)
        {
            return new ContactsModel();
        }

        return contactsModel;
    }


    public CurrentUserModel getCurrentUserModel(){
        if(currentUserModel == null)
        {
            return new CurrentUserModel();
        }

        return currentUserModel;
    }
}
