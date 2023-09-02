package model;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Model {
    private DefaultTableModel tableModel;
    
    public Model() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Nama Barang");
        tableModel.addColumn("Jumlah Barang");
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void addTableData(String namaBarang, int jumlahBarang) {
        tableModel.insertRow(0, new Object[]{namaBarang, jumlahBarang});
    }

    public void removeTableData(String namaBarang) {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            if (tableModel.getValueAt(i, 0).equals(namaBarang)) {
                tableModel.removeRow(i);
            }
        }
    }

    public void editTableData(String namaBarang, int jumlahBarang) {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            if (tableModel.getValueAt(i, 0).equals(namaBarang)) {
                tableModel.setValueAt(jumlahBarang, i, 1);
            }
        }
    }

    public void resetTableData() {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            tableModel.removeRow(0);
        }
    }

    public void searchTableData(String namaBarang) {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            if (tableModel.getValueAt(i, 0).equals(namaBarang)) {
                JOptionPane.showMessageDialog(null, "Nama Barang : " + tableModel.getValueAt(i, 0) + "\nJumlah Barang : " + tableModel.getValueAt(i, 1));
            }
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
        }
    }

    public boolean isTableEmpty() {
        return tableModel.getRowCount() == 0;
    }

    public boolean isDataExist(String namaBarang) {
        int row = tableModel.getRowCount();
        for (int i = 0; i < row; i++) {
            if (tableModel.getValueAt(i, 0).equals(namaBarang)) {
                return true;
            }
        }
        return false;
    }
}
