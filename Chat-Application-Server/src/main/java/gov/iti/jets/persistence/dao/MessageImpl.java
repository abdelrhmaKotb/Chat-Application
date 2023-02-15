package gov.iti.jets.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gov.iti.jets.dto.MessageDto;
import gov.iti.jets.persistence.dao.interfaces.MessageDao;
import gov.iti.jets.persistence.utils.DBConnecttion;

public class MessageImpl implements MessageDao {

    @Override
    public void createMessage(MessageDto messageDto) {
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement(
                    """
                                            INSERT INTO chat_application.message
                            (sender, reciver, content, fontSize, fontStyle, fontColor, backgroundColor, isBold, isUnderlined, isItalic)
                            VALUES (?, ?, ?, ?, ?,  ?, ?, ?, ?, ?);

                                        """);
            stmt.setString(1, messageDto.getSender());
            stmt.setString(2, messageDto.getReciver());
            stmt.setString(3, messageDto.getMessage());
            // stmt.setDate(4, messageDto.getMessageDate());
            stmt.setInt(4, messageDto.getFontSize());
            stmt.setString(5, messageDto.getFontStyle());
            stmt.setString(6, messageDto.getFontColor());
            stmt.setString(7, messageDto.getBackgroundColor());
            stmt.setBoolean(8, messageDto.isBold());
            stmt.setBoolean(9, messageDto.isUnderlined());
            stmt.setBoolean(10, messageDto.isItalic());

            stmt.executeUpdate();

            System.out.println("Message inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<MessageDto> getChatMessages(String ssender, String reciver) {
        List<MessageDto> messages = new ArrayList<>();
        try (Connection con = DBConnecttion.getConnection();) {

            PreparedStatement stmt = con.prepareStatement("""
                      SELECT * FROM message WHERE (sender = ? and reciver = ?) || (sender = ? and reciver = ? ) order by message_date
                    """);
            stmt.setString(1, ssender);
            stmt.setString(2, reciver);
            stmt.setString(3, reciver);
            stmt.setString(4, ssender);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                var message = new MessageDto(res.getString("sender"),
                res.getString("content"),
                res.getInt("fontSize"),
                res.getString("fontStyle"),
                res.getString("fontColor"),
                res.getString("backgroundColor"),
                res.getBoolean("isBold"),
                res.getBoolean("isUnderlined"),
                res.getBoolean("isUnderlined"),
                res.getString("reciver"));
                message.setMessageDate(res.getTimestamp("message_date"));
                messages.add(message);
            }

            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

}
