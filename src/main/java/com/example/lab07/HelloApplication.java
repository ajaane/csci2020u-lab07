package com.example.lab07;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import java.io.File;



public class HelloApplication extends Application {
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };

    @Override
    public void start(Stage stage) throws IOException {

        String fileName = "weatherwarnings-2015.csv";
        File file = new File(fileName);
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        ArrayList<String> list = new ArrayList<String>();

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            list.add(cols[5]);
        }

        Set<String> st = new HashSet<String>(list);
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String s : st) {
            pieChartData.add(new PieChart.Data(s, Collections.frequency(list, s)));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setLayoutX(40);
        pieChart.setLayoutY(10);
        pieChart.setClockwise(true);
        pieChart.setLabelsVisible(false);
        pieChart.setLabelLineLength(10);
        pieChart.setLegendSide(Side.LEFT);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(pieChart);


        anchorPane.getStylesheets().add(HelloApplication.class.getResource("colors.css").toExternalForm());
        Scene scene = new Scene(anchorPane, 600, 396);
        stage.setTitle("Lab 07");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
