package seedu.address.logic.parser.deckparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

<<<<<<< HEAD:src/main/java/seedu/address/logic/parser/deckparsers/RemoveDeckCommandParser.java
import seedu.address.logic.commands.dump.AddCommand;
import seedu.address.logic.parser.*;
=======
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteDeckCommand;
import seedu.address.logic.commands.dump.CreateDeckCommand;
>>>>>>> b759adc08096c8661fd321a19929122145affe9f:src/main/java/seedu/address/logic/parser/DeleteDeckCommandParser.java
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.dump.Address;
import seedu.address.model.deck.dump.Email;
import seedu.address.model.deck.dump.Name;
import seedu.address.model.deck.dump.Phone;
import seedu.address.model.deck.dump.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
<<<<<<< HEAD:src/main/java/seedu/address/logic/parser/deckparsers/RemoveDeckCommandParser.java
public class RemoveDeckCommandParser implements Parser<AddCommand> {
=======
public class DeleteDeckCommandParser implements Parser<DeleteDeckCommand> {
>>>>>>> b759adc08096c8661fd321a19929122145affe9f:src/main/java/seedu/address/logic/parser/DeleteDeckCommandParser.java

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteDeckCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index targetIdx = Index.fromOneBased(Integer.parseInt(args));

        return new DeleteDeckCommand(targetIdx);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
