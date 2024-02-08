/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaassignment1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author godch
 */
import java.util.Date;
import java.util.List;

public class Report {
    private int reportID;
    private Date generationDate;
    private String reportType;
    private String content;

    // Getter methods
    public int getReportID() {
        return reportID;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public String getReportType() {
        return reportType;
    }

    public String getContent() {
        return content;
    }

    // Method to generate report content
    public Object[][] generateReportContent(String[] searchCriteria, String reportType) {
        List<Object[]> reportData = new ArrayList<>();

        switch (reportType) {
            case "Work Done Report":
                // Read data from work done file
                try (BufferedReader reader = new BufferedReader(new FileReader("work_done.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Split the line by comma and check if it matches all search criteria
                        String[] fields = line.split(",");
                        boolean allCriteriaMatch = true;
                        for (String criteria : searchCriteria) {
                            boolean criteriaMatch = false;
                            for (String field : fields) {
                                if (field.equalsIgnoreCase(criteria.trim())) {
                                    criteriaMatch = true;
                                    break;
                                }
                            }
                            if (!criteriaMatch) {
                                allCriteriaMatch = false;
                                break;
                            }
                        }
                        if (allCriteriaMatch) {
                            reportData.add(fields);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case "Sales Report":
                // Read data from sales file
                try (BufferedReader reader = new BufferedReader(new FileReader("sales.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Split the line by comma and check if it matches all search criteria
                        String[] fields = line.split(",");
                        boolean allCriteriaMatch = true;
                        for (String criteria : searchCriteria) {
                            boolean criteriaMatch = false;
                            for (String field : fields) {
                                if (field.equalsIgnoreCase(criteria.trim())) {
                                    criteriaMatch = true;
                                    break;
                                }
                            }
                            if (!criteriaMatch) {
                                allCriteriaMatch = false;
                                break;
                            }
                        }
                        if (allCriteriaMatch) {
                            reportData.add(fields);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }

        // Convert the list of arrays to a two-dimensional array
        Object[][] reportArray = new Object[reportData.size()][];
        for (int i = 0; i < reportData.size(); i++) {
            reportArray[i] = reportData.get(i);
        }

        return reportArray;
    }
}
