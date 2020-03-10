package seedu.address.model.deck;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.deck.exceptions.DuplicatePersonException;
import seedu.address.model.deck.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Deck#isSameDeck(Deck)
 */
public class UniqueDeckList implements Iterable<Deck> {

    private final ObservableList<Deck> internalList = FXCollections.observableArrayList();
    private final ObservableList<Deck> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Deck toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeck);
    }

    /**
     * Returns deck with the given index.
     */
    public Deck get(int index) {
        if (index < 0 || index >= internalList.size()) {
            return null;
        }
        return internalList.get(index);
    }

    /**
     * Adds a deck to the library.
     * The deck must not already exist in the library.
     */
    public void add(Deck toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setPerson(Deck target, Deck editedDeck) {
        requireAllNonNull(target, editedDeck);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameDeck(editedDeck) && contains(editedDeck)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDeck);
    }


    /**
     * Removes the equivalent deck from the library.
     * The deck must exist in the library.
     */
    public void remove(Deck toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniqueDeckList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }


    public void setDecks(UniqueDeckList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Deck> decks) {
        requireAllNonNull(decks);
        if (!personsAreUnique(decks)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(decks);
    }

    /**
     * Replaces the contents of the library with {@code decks}.
     * {@code decks} must not contain duplicate deck.
     */
    public void setDecks(List<Deck> decks) {
        requireAllNonNull(decks);
        if (!personsAreUnique(decks)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(decks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Deck> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Deck> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDeckList // instanceof handles nulls
                        && internalList.equals(((UniqueDeckList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean personsAreUnique(List<Deck> decks) {
        for (int i = 0; i < decks.size() - 1; i++) {
            for (int j = i + 1; j < decks.size(); j++) {
                if (decks.get(i).isSameDeck(decks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}