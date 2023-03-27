package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_DECKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalDecks.VALID_DECK_SCIENCE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deckcommands.FindDeckCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.DeckContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindDeckCommandTest {
    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void equals() {
        FindDeckCommand findFirstCommand = new FindDeckCommand(Collections.singletonList("first"));
        FindDeckCommand findSecondCommand = new FindDeckCommand(Collections.singletonList("second"));

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDeckCommand findFirstCommandCopy = new FindDeckCommand(Collections.singletonList("first"));
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different find deck command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noDeckFound() {
        String userInput = " ";
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 0);
        DeckContainsKeywordsPredicate predicate = prepareDeckPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindDeckCommand command = new FindDeckCommand(keywords);
        expectedModel.updateFilteredDeckList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeckList());
    }

    @Test
    public void execute_singleKeywords_oneDecksFound() {
        String userInput = "science";
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 1);
        DeckContainsKeywordsPredicate predicate = prepareDeckPredicate(userInput);
        List keywords = prepareKeywords(userInput);
        FindDeckCommand command = new FindDeckCommand(keywords);
        expectedModel.updateFilteredDeckList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(VALID_DECK_SCIENCE), model.getFilteredDeckList());
    }

    /**
     * Parses {@code userInput} into a {@code DeckContainsKeywordsPredicate}.
     */
    private DeckContainsKeywordsPredicate prepareDeckPredicate(String userInput) {
        return new DeckContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code List}.
     */
    private List prepareKeywords(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
