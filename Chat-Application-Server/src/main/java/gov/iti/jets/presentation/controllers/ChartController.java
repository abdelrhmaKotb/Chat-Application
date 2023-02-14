package gov.iti.jets.presentation.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gov.iti.jets.business.rmi.ServerImpl;
import gov.iti.jets.business.services.ChartsService;
import gov.iti.jets.dto.CountryDto;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.TextField;

public class ChartController implements Initializable {

    @FXML
    private PieChart genderPieChart;
    @FXML
    private TextField male, female;

    @FXML
    private BarChart<String, Double> bar;

    @FXML
    private BarChart<String, Double> onlineAndOflineBar;

    static public ChartController chartController;

    public ChartController() {
        chartController = this;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        updatePieChart();
        updateBarChart();
        updateOnlineAndOfline();

    }

    public void updatePieChart() {
       
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                ObservableList<PieChart.Data> data = FXCollections.observableArrayList();

                genderPieChart.setData(data);
                genderPieChart.setTitle("Gender");
                genderPieChart.setStyle("-fx-font:20 system;-fx-text-fill:black;");
        
                genderPieChart.setLabelLineLength(10);
                ChartsService cs = new ChartsService();
        
                data.clear();
                data.addAll(new PieChart.Data("Male", cs.getGenderCharts(0)),
                        new PieChart.Data("Female", cs.getGenderCharts(1)));

                male.setText(String.valueOf(String.format("%.2f", (double) cs.getGenderCharts(0)
                        / ((cs.getGenderCharts(0) + cs.getGenderCharts(1))) * 100)) + " %");
                female.setText(String.valueOf(String.format("%.2f", (double) cs.getGenderCharts(1)
                        / ((cs.getGenderCharts(0) + cs.getGenderCharts(1))) * 100)) + " %");

            }

        });

        // new Thread(() -> {

        // while (true) {
        // Platform.runLater(new Runnable() {

        // @Override
        // public void run() {
        // data.clear();
        // data.addAll(new PieChart.Data("Male", cs.getGenderCharts(0)),
        // new PieChart.Data("Female", cs.getGenderCharts(1)));

        // male.setText(String.valueOf(String.format("%.2f", (double)
        // cs.getGenderCharts(0)
        // / ((cs.getGenderCharts(0) + cs.getGenderCharts(1))) * 100)) + " %");
        // female.setText(String.valueOf(String.format("%.2f", (double)
        // cs.getGenderCharts(1)
        // / ((cs.getGenderCharts(0) + cs.getGenderCharts(1))) * 100)) + " %");

        // }

        // });

        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }

        // }
        // }).start();
    }

    public void updateBarChart() {

        bar.setTitle("Countries And Users");
        bar.setStyle("-fx-font:20 system ;-fx-text-fill:black;");

        new Thread(() -> {

            while (true) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        ChartsService chartServie = new ChartsService();
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

    private ObservableList<XYChart.Series<String, Double>> getChartData(ArrayList<CountryDto> countryData) {

        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        answer.clear();
        for (int i = 0; i < countryData.size(); i++) {

            Series<String, Double> aSeries = new Series<String, Double>();
            aSeries.setName(countryData.get(i).getName());
            aSeries.getData().add(new XYChart.Data(Integer.toString(i + 1), countryData.get(i).getId()));
            answer.add(aSeries);

        }

        return answer;
    }

    private ObservableList<XYChart.Series<String, Double>> getOnlineAndOfline() {

        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        answer.clear();

        Series<String, Double> aSeries = new Series<String, Double>();
        aSeries.setName("Online");
        aSeries.getData().add(new XYChart.Data(Integer.toString(1), ServerImpl.countOnLine));
        Series<String, Double> bSeries = new Series<String, Double>();
        bSeries.setName("Ofline");
        bSeries.getData().add(new XYChart.Data(Integer.toString(2), ServerImpl.countOfLine));
        answer.addAll(aSeries, bSeries);

        return answer;
    }

    public void updateOnlineAndOfline() {

        onlineAndOflineBar.setTitle("Online And Ofline");
        onlineAndOflineBar.setStyle("-fx-font:20 system ;-fx-text-fill:black;");

        new Thread(() -> {

            while (true) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {

                        onlineAndOflineBar.setData(getOnlineAndOfline());
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
