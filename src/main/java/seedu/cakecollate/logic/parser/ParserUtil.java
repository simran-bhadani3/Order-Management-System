package seedu.cakecollate.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.cakecollate.commons.core.index.Index;
import seedu.cakecollate.commons.core.index.IndexList;
import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.parser.exceptions.ParseException;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.orderitem.OrderItem;
import seedu.cakecollate.model.orderitem.Type;
import seedu.cakecollate.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final int PHONE_LENGTH = 20;
    public static final int TAG_LENGTH = 30;

    public static final int INTEGER_LENGTH = 10;
    public static final int NAME_LENGTH = 80;

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }


    /**
     * Parses {@code oneBasedIndexList} from String into an {@code IndexList} and returns it.
     * @param oneBasedIndexList one based indices input by the user.
     * @return list of indices sorted in descending order.
     * @throws ParseException if one of the specified indices is invalid.
     */
    public static IndexList parseIndexList(String oneBasedIndexList) throws ParseException {
        String[] indexListSplit = oneBasedIndexList.trim().split(" ");
        IndexList indexList = new IndexList(new ArrayList<>());
        for (String index: indexListSplit) {
            Index parsedIndex = parseIndex(index);
            indexList.add(parsedIndex);
        }
        indexList.sortList();
        return indexList;
    }


    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (trimmedName.isEmpty()) {
            throw new ParseException(Name.MESSAGE_EMPTY);
        }
        if (trimmedName.length() > NAME_LENGTH) {
            throw new ParseException(Name.MESSAGE_OVERFLOW);
        }
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (trimmedPhone.isEmpty()) {
            throw new ParseException(Phone.MESSAGE_EMPTY);
        }
        if (trimmedPhone.length() > PHONE_LENGTH) {
            throw new ParseException(Phone.MESSAGE_OVERFLOW);
        }
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (trimmedAddress.isEmpty()) {
            throw new ParseException(Address.MESSAGE_EMPTY);
        }
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (trimmedEmail.isEmpty()) {
            throw new ParseException(Email.MESSAGE_EMPTY);
        }
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String orderDescription} into a {@code OrderDescription}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code orderDescription} is invalid.
     */
    public static OrderDescription parseOrderDescription(String orderDescription) throws ParseException {
        requireNonNull(orderDescription);
        String trimmedOrderDescription = orderDescription.trim();
        if (!OrderDescription.isValidOrderDescription(trimmedOrderDescription)) {
            throw new ParseException(OrderDescription.MESSAGE_CONSTRAINTS);
        }

        return new OrderDescription(trimmedOrderDescription);
    }

    /**
     * Parses {@code Collection<String> orderDescriptions} into a {@code Set<OrderDescription>}.
     */
    public static Set<OrderDescription> parseOrderDescriptions(Collection<String> orderDescriptions)
            throws ParseException {
        requireNonNull(orderDescriptions);
        final Set<OrderDescription> orderDescriptionSet = new HashSet<>();
        for (String o : orderDescriptions) {
            orderDescriptionSet.add(parseOrderDescription(o));
        }
        return orderDescriptionSet;
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (trimmedTag.length() > TAG_LENGTH) {
            throw new ParseException(Tag.MESSAGE_OVERFLOW);
        }
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String deliveryDate} into an {@code DeliveryDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code deliveryDate} is invalid.
     */
    public static DeliveryDate parseDeliveryDate(String deliveryDate) throws ParseException {
        requireNonNull(deliveryDate);
        String trimmedDeliveryDate = deliveryDate.trim();
        if (trimmedDeliveryDate.isEmpty()) {
            throw new ParseException(DeliveryDate.MESSAGE_EMPTY);
        }
        if (!DeliveryDate.isValidFormat(trimmedDeliveryDate)) {
            System.out.println("not valid format");
            throw new ParseException(DeliveryDate.MESSAGE_CONSTRAINTS_FORMAT);
        }
        if (!DeliveryDate.isXDaysLater(trimmedDeliveryDate, 0L)) {
            System.out.println("not future date");
            throw new ParseException(String.format(DeliveryDate.MESSAGE_CONSTRAINTS_VALUE, trimmedDeliveryDate));
        }
        return new DeliveryDate(trimmedDeliveryDate);
    }

    /**
     * Parses a {@code String orderItemType} into an {@code OrderItem}.
     * The {@code Cost} is set to a default value of 10 for now.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param orderItemType Type of the order item as entered by the user.
     *
     * @throws ParseException if the given {@code orderItemType} is invalid.
     */
    public static OrderItem parseOrderItem(String orderItemType) throws ParseException {
        requireNonNull(orderItemType);
        String trimmedOrderItemDescription = orderItemType.trim();
        if (!Type.isValidType(trimmedOrderItemDescription)) {
            throw new ParseException(Type.MESSAGE_CONSTRAINTS);
        }
        return new OrderItem(new Type(trimmedOrderItemDescription));
    }
}
