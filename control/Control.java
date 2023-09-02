package control;

import model.Model;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Control {
    private View view;
    private Model model;

    public Control(View view, Model model) {
        this.view = view;
        this.model = model;

        this.view.addTambahListener(new TambahListener());
        this.view.addHapusListener(new HapusListener());
        this.view.addResetListener(new ResetListener());
        this.view.addSearchListener(new SearchListener());
        this.view.addEditListener(new EditListener());

        this.view.setTableModel(model.getTableModel());
    }

    class TambahListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String namaBarang = view.getInputNama();
            int jumlahBarang = view.getInputJumlah();

            if (namaBarang.isEmpty() || jumlahBarang == 0) {
                JOptionPane.showMessageDialog(null, "Nama barang atau jumlah barang tidak boleh kosong!");
            } else {
                view.resetInput();
                JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
                model.addTableData(namaBarang, jumlahBarang);            }
        }
    }

    class HapusListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Hapus berdasarkan nama barang
            String namaBarang = view.getInputNamaHapus();

            // Buat kondisi barang ketika kosong, barang ketika tidak ada di dalam table, dan barang ketika ada di dalam table
            if (namaBarang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama barang tidak boleh kosong!");
            } else if (model.isTableEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa dihapus!");
            } else if (!model.isTableEmpty() && !model.isDataExist(namaBarang)) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            } else {
                view.resetInput();
                model.removeTableData(namaBarang);
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            }
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Menghapus semua data di dalam table
            if (model.isTableEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa direset!");
            } else {
                view.resetInput();
                model.resetTableData();
                JOptionPane.showMessageDialog(null, "Data berhasil direset!");
            }
        }
    }

    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Tampilkan barang yang dicari
            String namaBarang = view.getInputBarangDicari();

            // Buat kondisi barang ketika kosong, barang ketika tidak ada di dalam table, dan barang ketika ada di dalam table
            if (namaBarang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nama barang tidak boleh kosong!");
            } else if (model.isTableEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa dicari!");
            } else if (!model.isTableEmpty() && !model.isDataExist(namaBarang)) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            } else {
                view.resetInput();
                model.searchTableData(namaBarang);
            }
        }
    }

    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Edit jumlah barang berdasarkan nama barang
            String namaBarang = view.getInputNamaEdit();
            int jumlahBarang = view.getEditJumlah();

            // Buat kondisi barang ketika kosong, barang ketika tidak ada di dalam table, dan barang ketika ada di dalam table
            if (namaBarang.isEmpty() || jumlahBarang == 0) {
                JOptionPane.showMessageDialog(null, "Nama barang atau jumlah barang tidak boleh kosong!");
            } else if (model.isTableEmpty()) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa diedit!");
            } else if (!model.isTableEmpty() && !model.isDataExist(namaBarang)) {
                JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
            } else {
                view.resetInput();
                model.editTableData(namaBarang, jumlahBarang);
                JOptionPane.showMessageDialog(null, "Data berhasil diedit!");
            }
        }
    }
}
