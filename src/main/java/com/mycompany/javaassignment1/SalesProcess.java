package javaapplication1;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class JavaApplication1 {

    private static DefaultTableModel tableModel;
    private static TableRowSorter<DefaultTableModel> sorter;

    public static void main(String[] args) {
        // Create and configure the frame
        JFrame frame = new JFrame("Furniture Sale Ordering Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and configure the panel
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);

        // Create search panel
        JPanel searchPanel = new JPanel(new GridLayout(1, 2));

        // Add a JTextField for search input
        JTextField searchField = new JTextField();
        searchPanel.add(searchField);

        // Create search button
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);

        // Create table model with columns
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"CustomerID", "Furniture", "Type", "Price", "Approval"}
        );

        // Create JTable with the table model
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(true);
        table.setRowSelectionAllowed(false);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create approve panel
        JPanel approvePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // Create approve button
        JButton approveButton = new JButton("Approve Sale");
        approvePanel.add(approveButton);

        // Create close button
        JButton closeButton = new JButton("Close Sale");
        approvePanel.add(closeButton);

        // Create save panel
        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Create save button
        JButton saveButton = new JButton("Save");
        savePanel.add(saveButton);

        // Create a panel to hold the approvePanel and savePanel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(approvePanel, BorderLayout.WEST);
        bottomPanel.add(savePanel, BorderLayout.CENTER);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Initialize sorter
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Event handler for search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)" + searchTerm);
                    sorter.setRowFilter(rf);
                } else {
                    // If the search term is empty, remove the filter
                    sorter.setRowFilter(null);
                }
            }
        });

        // Event handler for save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataToFile();
            }
        });

        // Event handler for close button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Closed", selectedRow, tableModel.getColumnCount() - 1);
                }
            }
        });

        // Event handler for approve button
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Approved", selectedRow, tableModel.getColumnCount() - 1);
                }
            }
        });

        // Mouse listener to handle adding new row when clicking on empty row
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    int col = table.columnAtPoint(e.getPoint());
                    if (row == -1) {
                        tableModel.addRow(new Object[tableModel.getColumnCount()]);
                    }
                }
            }
        });

        // Load initial data
        loadData();

        // Set custom cell editor for the "Price" column
        table.getColumnModel().getColumn(3).setCellEditor(new NumberCellEditor());

        // Set frame visibility
        frame.setVisible(true);
    }

    private static void saveDataToFile() {
        String filePath = "C:/Users/ME/Desktop/sale_orders.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    String value = (String) tableModel.getValueAt(row, col);
                    if (value != null) {
                        writer.write(value);
                        if (col < tableModel.getColumnCount() - 1) {
                            writer.write(",");
                        }
                    }
                }
                writer.newLine();
            }
            writer.flush();
            writer.close();
            System.out.println("Data saved to " + filePath + " successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        String filePath = "C:/Users/ME/Desktop/sale_orders.txt";
        try {
            java.util.Scanner fileScanner = new java.util.Scanner(new java.io.File(filePath));
            while (fileScanner.hasNext()) {
                String[] rowData = fileScanner.nextLine().split(",");
                tableModel.addRow(rowData);
            }
            fileScanner.close();
            System.out.println("Data loaded from " + filePath + " successfully.");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    static class NumberCellEditor extends DefaultCellEditor {
        public NumberCellEditor() {
            super(new JTextField());
        }

        @Override
        public boolean stopCellEditing() {
            try {
                Integer.parseInt((String) getCellEditorValue());
                return super.stopCellEditing();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a numeric value for Price.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
}
