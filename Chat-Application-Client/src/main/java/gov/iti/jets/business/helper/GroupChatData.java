package gov.iti.jets.business.helper;

import gov.iti.jets.dto.GroupDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GroupChatData implements Chat{
    private FXMLLoader loader;

    private Parent view;

    private  GroupDto group;

    public GroupChatData(FXMLLoader loader, Parent view, GroupDto group) {
        this.loader = loader;
        this.view = view;
        this.group=group;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Parent getView() {
        return view;
    }
    public String getIdntifier() {
        return String.valueOf(group.getId());
    }

}
