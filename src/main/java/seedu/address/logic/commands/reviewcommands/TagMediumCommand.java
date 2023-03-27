package seedu.address.logic.commands.reviewcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.tag.Tag.TagName.MEDIUM;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * The TagMediumCommand class is responsible for tagging the
 * current card being reviewed with the MEDIUM tag.
 */
public class TagMediumCommand extends Command {
    public static final String COMMAND_WORD = ";";

    public static final String MESSAGE_SUCCESS = "Card is tagged with MEDIUM!";

    /**
     * Executes the tagging operation by calling the tagCurrentCardInReview() method of the Model object.
     *
     * @param model the Model object containing the cards to be tagged
     * @return a CommandResult object with a success message
     * @throws CommandException if an error occurs during the execution of the command
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.tagCurrentCardInReview(new Tag(MEDIUM));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
