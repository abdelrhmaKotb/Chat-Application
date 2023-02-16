package gov.iti.jets.business.models;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvitationsModel {
    
    private ObservableList<String> observableInvitationsList;

    public void setObservableInvitationsList(List<String> oList){
        
        observableInvitationsList = FXCollections.observableArrayList();
        if(oList != null){
            oList.forEach(e -> observableInvitationsList.add(e));
        }
    }

    public ObservableList<String> getObservableInvitationsList(){
        return observableInvitationsList;
    }

}
