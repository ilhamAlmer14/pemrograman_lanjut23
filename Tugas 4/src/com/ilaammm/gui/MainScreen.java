package com.ilaammm.gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JButton ButtonFilter;
    private JTextField textFieldNIM;
    private JTextField textFieldNama;
    private JTextField textFieldIPK;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JTextField textFieldFilter;
    private List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
    private DefaultListModel defaultListModel = new DefaultListModel();

    class Mahasiswa {
        private String nim;
        private String nama;
        private float ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }
    }

    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNIM.getText();
                String nama = textFieldNama.getText();
                float ipk = Float.parseFloat(textFieldIPK.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(ipk);
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);

                arrayListMahasiswa.add(mahasiswa);
                clearform();

                fromListMahasiswaTolistModel();
            }
        });

        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama)) {
                        Mahasiswa mahasiswa = arrayListMahasiswa.get(i);
                        textFieldIPK.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNIM.setText(mahasiswa.getNim());
                        break;
                    }
                }

            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearform();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama)) {
                        arrayListMahasiswa.remove(i);
                        break;
                    }
                }
                clearform();
                fromListMahasiswaTolistModel();
            }
        });

        ButtonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().contains(keyword)) {
                        filtered.add(arrayListMahasiswa.get(i).getNama());
                    }
                }

                refresListmodel(filtered);
            }
        });
    }

    private void fromListMahasiswaTolistModel() {
        List<String> listnamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            listnamaMahasiswa.add(arrayListMahasiswa.get(i).getNama());
        }

        refresListmodel(listnamaMahasiswa);
    }

    private void clearform() {
        textFieldIPK.setText("");
        textFieldNIM.setText("");
        textFieldNama.setText("");
    }

    private void refresListmodel(List<String> list) {
        defaultListModel.clear();
        defaultListModel.addAll(list);
        jListMahasiswa.setModel(defaultListModel);
    }

    public static void main(String[] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }
}
