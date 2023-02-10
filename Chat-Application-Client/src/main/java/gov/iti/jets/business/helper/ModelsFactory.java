package gov.iti.jets.business.helper;

import gov.iti.jets.business.models.ContactsModel;
import gov.iti.jets.business.models.CurrentUserModel;

public class ModelsFactory {

    private static ModelsFactory instance;

    private ContactsModel contactsModel;
    private static CurrentUserModel currentUserModel;

    private ModelsFactory(){}


    public static  ModelsFactory getInstance()
    {
        if(instance == null)
        {
            instance =  new ModelsFactory();

            return instance;
        }
        return instance;
    }


    public ContactsModel getContactsModel(){

        if(contactsModel == null)
        {
            contactsModel =  new ContactsModel();
            return contactsModel;
        }

        return contactsModel;
    }


    public CurrentUserModel getCurrentUserModel(){
        if(currentUserModel == null)
        {
            currentUserModel =  new CurrentUserModel();
            return currentUserModel;
        }

        return currentUserModel;
    }
}
