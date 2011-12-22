package ru.eltech.elerning.geotracker.ui;

import org.json.JSONObject;
import ru.eltech.elerning.geotracker.core.Geo2TagConstants;
import ru.eltech.elerning.geotracker.core.model.Channel;
import ru.eltech.elerning.geotracker.core.model.RssResult;
import ru.eltech.elerning.geotracker.core.model.RssRow;
import ru.eltech.elerning.geotracker.core.services.Geo2TagService;
import ru.eltech.elerning.geotracker.util.StringUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class MainWindow {
    private final String authToken;
    private final String login;

    private List<Channel> availableChannels;
    private List<Channel> subscribedChannels;
    private List<Channel> rssChannels;

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
    private JTextField rssLatTextField;
    private JTextField rssLonTextField;
    private JTextField rssRadiusTextField;
    private JTable rssTable;
    private JButton rssGenerateButton;
    private JLabel rssStatusBar;
    private JTextField applyMarkLChannelNameTextField;
    private JTextField applyMarkTitleTextField;
    private JTextField applyMarkDescrTextField;
    private JTextField applyMarkLinkTextField;
    private JTextField applyMarkLatTextField;
    private JTextField applyMarkLonTextField;
    private JButton applyMarkButton;
    private JLabel applyMarkStatusBar;
    private JTextField rssFeedUserTextField;

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

        rssGenerateButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                generateRss();
            }
        });

        showChannels();
        showSubscribedChannels();
        rssFeedUserTextField.setText(login);
        applyMarkButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                applyMark();
            }
        });
    }

    private void generateRss() {
        try {
            final double lat = Double.parseDouble(rssLatTextField.getText());
            final double lon = Double.parseDouble(rssLonTextField.getText());
            final double radius = Double.parseDouble(rssRadiusTextField.getText());
            final String user = rssFeedUserTextField.getText();
            final RssResult rssResult = Geo2TagService.wrapedRssFeed(authToken, lat, lon, radius);
            final Map<String,List<JSONObject>> channelToTags = rssResult.getChannelToTags();
            final List<RssRow> rssResultAsRows = filterByUser(extractRssRows(channelToTags), user);
            rssTable.setModel(new AbstractTableModel() {
                public int getRowCount() {
                    return rssResultAsRows.size();
                }

                public int getColumnCount() {
                    return RssRow.getColsCount();
                }

                @Override
                public String getColumnName(final int column) {
                    switch (column) {
                        case 0:
                            return "Channel";
                        case 1:
                            return "date";
                        case 2:
                            return "title";
                        case 3:
                            return "description";
                        case 4:
                            return "link";
                        case 5:
                            return "lon";
                        case 6:
                            return "lat";
                        case 7:
                            return "user";
                    }
                    return null;
                }

                public Object getValueAt(final int rowIndex, final int columnIndex) {
                    final RssRow row = rssResultAsRows.get(rowIndex);
                    switch (columnIndex) {
                        case 0:
                            return row.getChannelName();
                        case 1:
                            return row.getPubDate();
                        case 2:
                            return row.getTitle();
                        case 3:
                            return row.getDescr();
                        case 4:
                            return row.getLink();
                        case 5:
                            return row.getLon();
                        case 6:
                            return row.getLat();
                        case 7:
                            return row.getUser();
                    }
                    return null;
                }
            });
            rssStatusBar.setText("");
        } catch (final Exception e) {
            rssStatusBar.setText("Error: " + e.getMessage());
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    private List<RssRow> filterByUser(final List<RssRow> rssRows, final String user) {
        if(StringUtils.isEmpty(user)){
            return rssRows;
        }
        final List<RssRow> out = new ArrayList<RssRow>();
        for(final RssRow row : rssRows){
            if(user.equals(row.getUser())){
                out.add(row);
            }
        }
        return out;
    }

    private List<RssRow> extractRssRows(final Map<String, List<JSONObject>> channelToTags) {
        final List<RssRow> out = new ArrayList<RssRow>();
        for(final Map.Entry<String, List<JSONObject>> entry : channelToTags.entrySet()){
            for(final JSONObject tag : entry.getValue()){
                out.add(new RssRow(
                        entry.getKey(),
                        tag.optString("pubDate"),
                        tag.optString("title"),
                        tag.optString("description"),
                        tag.optString("link"),
                        tag.optString("latitude"),
                        tag.optString("longitude"),
                        tag.optString("user")
                ));
            }

        }
        return out;
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

   private void applyMark() {
        try {
            final String channelName = applyMarkLChannelNameTextField.getText();
            final String description = applyMarkDescrTextField.getText();
            final String link = applyMarkLinkTextField.getText();
            final String title = applyMarkTitleTextField.getText();
            final double lon = Double.parseDouble(applyMarkLonTextField.getText());
            final double lat = Double.parseDouble(applyMarkLatTextField.getText());
            final JSONObject applyMarkResult = Geo2TagService.applyMark(authToken, channelName, description, StringUtils.isEmpty(link) ? "unknown" : link, lat, lon, new Date(), title);
            applyMarkStatusBar.setText(applyMarkResult.optString(Geo2TagConstants.Params.STATUS_DESCRIPTION));

        } catch (final Exception e) {
            applyMarkStatusBar.setText("Error: " + e.getMessage());
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
