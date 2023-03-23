package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel containing the statistics of the current review.
 */
public class ReviewStatsPanel extends UiPart<Region> {
    private static final String FXML = "ReviewStatsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ReviewStatsPanel.class);

    @FXML
    private ListView<Pair<String, String>> reviewStatsView;

    /**
     * Creates a {@code ReviewStatsPanel} with the given {@code ObservableList}.
     */
    public ReviewStatsPanel(ObservableList<Pair<String, String>> reviewStatsList) {
        super(FXML);
        reviewStatsView.setItems(reviewStatsList);
        reviewStatsView.setCellFactory(listView -> new ReviewStatsPanel.ReviewStatsViewCell());
        reviewStatsView.setStyle("-fx-background-color: #FFFFFF;"
                + "-fx-background-radius: 30; -fx-border-radius: 30; -fx-border-width:5;");
    }

    class ReviewStatsViewCell extends ListCell<Pair<String, String>> {
        @Override
        protected void updateItem(Pair<String, String> pair, boolean empty) {
            super.updateItem(pair, empty);
            if (empty || pair == null) {
                setGraphic(null);
                setText(null);

            } else {
                setGraphic(new ReviewStat(pair).getRoot());
            }
        }
    }

}