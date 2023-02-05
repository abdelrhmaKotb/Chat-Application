package gov.iti.jets.presentation.controllers;

import java.beans.EventHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gov.iti.jets.business.dto.CountryDto;
import gov.iti.jets.business.services.ChartsService;
import gov.iti.jets.persistence.dao.countryDaoImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class ChartController implements Initializable {

    @FXML
    private PieChart genderPieChart;
    @FXML
    private TextField male, female;
    @FXML
    private BarChart<String, Double> bar;
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updatePieChart();
        updateBarChart();
      
    }

    public void updatePieChart() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

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
                        data.addAll(new PieChart.Data("Male", cs.getGenderCharts("Male")),
                                new PieChart.Data("Female", cs.getGenderCharts("Female")));
                        male.setText(String.valueOf(String.format("%.2f",(double) cs.getGenderCharts("Male")
                                / ((cs.getGenderCharts("Male") + cs.getGenderCharts("Female"))) * 100))+" %");
                                female.setText(String.valueOf(String.format("%.2f",(double) cs.getGenderCharts("Female")
                                / ((cs.getGenderCharts("Male") + cs.getGenderCharts("Female"))) * 100))+" %");

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
    public void updateBarChart() {
     
        new Thread(() -> {

            while (true) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ChartsService chartServie=new ChartsService();
                        bar.setData(getChartData(chartServie.getCountryChart()));
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
    private ObservableList<XYChart.Series<String, Double>> getChartData(ArrayList<CountryDto>countryData) {
    
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        answer.clear();
        for(int i=0;i<countryData.size();i++){

            Series<String, Double> aSeries = new Series<String, Double>();
            aSeries.setName(countryData.get(i).getName());
            aSeries.getData().add(new XYChart.Data(Integer.toString(i+1),countryData.get(i).getCount()));
            answer.add(aSeries);

        }
     
        return answer;
    }



}
