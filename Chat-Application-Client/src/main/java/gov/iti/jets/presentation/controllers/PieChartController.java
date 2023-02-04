package gov.iti.jets.presentation.controllers;

import java.beans.EventHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import gov.iti.jets.business.services.ChartsService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PieChartController implements Initializable {

    @FXML
    private PieChart genderPieChart;
    @FXML
    private TextField male, female;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePieChart();
    }

    public void updatePieChart() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

        data.addAll(new PieChart.Data("JEE", 25),

                new PieChart.Data("Js", 50));

        genderPieChart.setData(data);
        genderPieChart.setTitle("Gender");
        genderPieChart.setStyle("-fx-font:20 arial;");

        genderPieChart.setLabelLineLength(10);
        ChartsService cs=new ChartsService();
        new Thread(() -> {

            while (true) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        data.clear();
                        data.addAll(new PieChart.Data("Male", cs.getCharts("Male")),
                                new PieChart.Data("Female", cs.getCharts("Female")));
                        male.setText(String.valueOf(String.format("%.2f",(double) cs.getCharts("Male")
                                / ((cs.getCharts("Male") + cs.getCharts("Female"))) * 100))+" %");
                                female.setText(String.valueOf(String.format("%.2f",(double) cs.getCharts("Female")
                                / ((cs.getCharts("Male") + cs.getCharts("Female"))) * 100))+" %");

                    }

                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

   

}
