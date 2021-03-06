package seedu.address.model.task;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import seedu.address.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Task in the todo.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask extends Comparable<ReadOnlyTask>{

    public static final String BLANK = "";
    enum TaskType {FLOATING, UNTIMED, DEADLINE, TIMERANGE, EVENT }

    Name getName();
    Optional<Time> getTime();
    Description getDescription();
    Location getLocation();
    boolean getCompleted();
    TaskType getTaskType();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the task's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getTime().equals(this.getTime())
                && other.getDescription().equals(this.getDescription())
                && other.getLocation().equals(this.getLocation())
                && other.getCompleted() == (this.getCompleted()));
    }

    /**
     * Formats the task as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        TaskType taskType = getTaskType();
        String time;
        switch(taskType) {
            case FLOATING:
                time = BLANK;
                break;
            case UNTIMED:
            case DEADLINE:
                time = getTime().get().getStartDateString();
                break;
            case EVENT:
                time = getTime().get().getStartDateString() + " - " + getTime().get().getEndDateString() + " " +
                        getTime().get().getEndTimeString();
                break;
            case TIMERANGE:
                time = getTime().get().getStartDateString() + " - " + getTime().get().getEndTimeString();
                break;
            default:
                assert false: "not possible for task to be uncategorised.";
                time = null;
        }

        builder.append(getName())
                .append(" Date: ")
                .append(time)
                .append(" Description: ")
                .append(getDescription())
                .append(" Location: ")
                .append(getLocation())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Formats the task for display in browser, showing all contact details.
     */
    default String getAsHTML() {
        final StringBuilder builder = new StringBuilder();
        builder.append("<html>")
                .append("<body bgcolor=\"#ffffff\">")
                .append("<h2><font face=\"cambria\"><b>" + getName() + "</b></font></h2>");
        
        if (getTaskType() == TaskType.EVENT) {
        	builder.append("<p><font face=\"segoe ui semibold\">Start Date:</font> <font face=\"segoe ui light\">")
        	        .append(getTime().get().getStartDateString())
        	        .append("</font></p>")
        	        .append("<p><font face=\"segoe ui semibold\">End Date:</font> <font face=\"segoe ui light\">")
        	        .append(getTime().get().getEndDateString())
        	        .append(" ")
                    .append(getTime().get().getEndTimeString());
        } else {
	        if (getTaskType() != TaskType.FLOATING) {
	        	builder.append("<p><font face=\"segoe ui semibold\">Date:</font> <font face=\"segoe ui light\">")
                        .append(getTime().get().getStartDate().toLocalDate().format(DateTimeFormatter.ofPattern("d MMM uuuu")))
	        	        .append("</font></p>");
	            if (getTaskType() != TaskType.UNTIMED) {
	            	builder.append("<p><font face=\"segoe ui semibold\">Time:</font> <font face=\"segoe ui light\">")
	            	        .append(getTime().get().getStartDate().toLocalTime());
	            	if (getTaskType() != TaskType.DEADLINE) {
	            	    builder.append(" ~ ")
	                            .append(getTime().get().getEndDate().get().toLocalTime());
	            	}
	            }
	            builder.append("</font></p>");
	        }
        }
        
        builder.append("<p><font face=\"segoe ui semibold\">Description: </font>")
                .append("<font face=\"segoe ui light\">" + getDescription()+ "</font></p>")
                .append("<p><font face=\"segoe ui semibold\">Location: </font>")
                .append("<font face=\"segoe ui light\">" + getLocation()+ "</font></p>")
                .append("<p><font face=\"segoe ui semibold\">Tags:</font> <font face=\"segoe ui light\"> ");
        getTags().forEach(builder::append);
        builder.append("</font></p>")
                .append("</body> </html>");
        return builder.toString();
    }

    /**
     * Returns a string representation of this Task's tags
     */
    default String tagsString() {
        final StringBuffer buffer = new StringBuffer();
        final String separator = ", ";
        getTags().forEach(tag -> buffer.append(tag).append(separator));
        if (buffer.length() == 0) {
            return "";
        } else {
            return buffer.substring(0, buffer.length() - separator.length());
        }
    }

    //@@author A0135812L
    @Override
    public default int compareTo(ReadOnlyTask o) {
        if(!this.getTime().isPresent()){
            return -1;
        }else if(!o.getTime().isPresent()){
            return 1;
        }else{
            return this.getTime().get().compareTo(o.getTime().get());
        }
    }
    //@@author

}
