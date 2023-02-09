package gov.iti.jets.persistence.entities;

import java.util.Date;

public class GroupMembers {
    private int group_id;
    private String memberPhoneNumber;
    private Date join_date;

    public GroupMembers(int group_id, String memberPhoneNumber, Date join_date) {
        this.group_id = group_id;
        this.memberPhoneNumber = memberPhoneNumber;
        this.join_date = join_date;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
    }

}