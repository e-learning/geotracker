package ru.eltech.elerning.geotracker.ui;

import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.Geo2TagConstants;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.core.services.Geo2TagService;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class MainWindow {
    private final String authToken;
    private final String login;

    private List<Channel> availableChannels;
    private List<Channel> subscribedChannels;

    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    private JTable channelsTable;
    private JButton addChannelButton;
    private JTextField addChannelNameTextField;
    private JTextField addChannelDescTextField;
    private JTextField channelsLatTextField;
    private JTextField channelsLonTextField;
    private JTextField channelsRadiusTextField;
    private JButton refreshChannelsButton;
    private JTextField addChannelRadiusTextField;
    private JTextField addChannelUrlTextField;
    private JLabel channelsStatusBarLabel;
    private JTable subscribedTable;
    private JButton subscribeButton;
    private JButton refreshSubscribedButton;
    private JTextField channelNameForSubscribeTextField;
    private JLabel subscribedStatusBarLabel;

    public MainWindow(final String authToken, final String login) {
        this.authToken = authToken;
        this.login = login;
        refreshChannelsButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                showChannels();
            }
        });

        addChannelButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                addChannel();
            }
        });

        refreshSubscribedButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                showSubscribedChannels();
            }
        });

        subscribeButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                subscribeChannel();
            }
        });

        showChannels();
        showSubscribedChannels();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void addChannel() {
        try {
            final String name = addChannelNameTextField.getText();
            final String description = addChannelDescTextField.getText();
            final String url = addChannelUrlTextField.getText();
            final double activeRadius = Double.parseDouble(addChannelRadiusTextField.getText());
            final JSONObject addChannelResult = Geo2TagService.addChannel(authToken, name, description, url, activeRadius);
            if(addChannelResult.optString(Geo2TagConstants.Params.STATUS).equals(Geo2TagConstants.LOGIN_STATUS_OK)){
                showChannels();
                channelsStatusBarLabel.setText("");
            } else {
                channelsStatusBarLabel.setText(addChannelResult.optString(Geo2TagConstants.Params.STATUS_DESCRIPTION));
            }
        } catch (final Exception e) {
            channelsStatusBarLabel.setText("Error: " + e.getMessage());
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    private void subscribeChannel() {
        try {
            final String name = channelNameForSubscribeTextField.getText();
            final JSONObject subscribeResult = Geo2TagService.subscribeChannel(authToken, name);
            if(subscribeResult.optString(Geo2TagConstants.Params.STATUS).equals(Geo2TagConstants.LOGIN_STATUS_OK)){
                showSubscribedChannels();
                subscribedStatusBarLabel.setText("");
            } else {
                subscribedStatusBarLabel.setText(subscribeResult.optString(Geo2TagConstants.Params.STATUS_DESCRIPTION));
            }
        } catch (final Exception e) {
            subscribedStatusBarLabel.setText("Error: " + e.getMessage());
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    private void showChannels() {
        try {
            final double lat = Double.parseDouble(channelsLatTextField.getText());
            final double lon = Double.parseDouble(channelsLonTextField.getText());
            final double radius = Double.parseDouble(channelsRadiusTextField.getText());
            availableChannels = Geo2TagService.loadAvailableChannelsAsList(authToken, lat, lon, radius);
            channelsTable.setModel(new AbstractTableModel() {
                public int getRowCount() {
                    return availableChannels.size();
                }

                public int getColumnCount() {
                    return 2;
                }

                @Override
                public String getColumnName(final int column) {
                    switch (column) {
                        case 0:
                            return "Name";
                        case 1:
                            return "Description";
                    }
                    return null;
                }

                public Object getValueAt(final int rowIndex, final int columnIndex) {
                    final Channel channel = availableChannels.get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            return channel.getName();
                        case 1:
                            return channel.getDescription();
                    }
                    return null;
                }
            });
            channelsStatusBarLabel.setText("");
        } catch (final Exception e) {
            channelsStatusBarLabel.setText("Error: " + e.getMessage());
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }


    private void showSubscribedChannels() {
        try {
            subscribedChannels = Geo2TagService.loadSubscribedChannelsAsList(authToken);
            subscribedTable.setModel(new AbstractTableModel() {
                public int getRowCount() {
                    return subscribedChannels.size();
                }

                public int getColumnCount() {
                    return 2;
                }

                @Override
                public String getColumnName(final int column) {
                    switch (column) {
                        case 0:
                            return "Name";
                        case 1:
                            return "Description";
                    }
                    return null;
                }

                public Object getValueAt(final int rowIndex, final int columnIndex) {
                    final Channel channel = subscribedChannels.get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            return channel.getName();
                        case 1:
                            return channel.getDescription();
                    }
                    return null;
                }
            });
            subscribedStatusBarLabel.setText("");
        } catch (final Exception e) {
            subscribedStatusBarLabel.setText("Error: " + e.getMessage());
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
