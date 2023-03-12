package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DECK;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddDeckCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Deck;

/**
 * Parses input arguments and creates a new AddDeckCommand object
 */
public class AddDeckCommandParser implements Parser<AddDeckCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDeckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DECK);

        if (!arePrefixesPresent(argMultimap, PREFIX_DECK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDeckCommand.MESSAGE_USAGE));
        }

        Deck deck = ParserUtil.parseDeck(argMultimap.getValue(PREFIX_DECK).get());

        return new AddDeckCommand(deck);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}