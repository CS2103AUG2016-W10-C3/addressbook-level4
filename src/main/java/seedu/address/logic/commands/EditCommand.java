package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.UnmodifiableObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.*;
import seedu.address.model.task.UniqueTaskList.PersonNotFoundException;

import java.util.HashSet;
import java.util.Set;


/**
 * Adds a task to the SmartyDo.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a task to the SmartyDo. "
            + "Parameters: INDEX [NAME] [t/TIME] [d/DESCRIPTION] [a/LOCATION]  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " John Doe t/9876 d/johnd's description a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the SmartyDo";

    
    private boolean isExecutedBefore;
    private Task toAdd;
    public final int targetIndex;
    private ReadOnlyTask taskToDelete;
    private String name;
    private String time;
    private String description;
    private String location; 
    private Set<String> tags;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(int targetIndex, String name, String time, String description, String location, Set<String> tags)
            throws IllegalValueException {
    	this.targetIndex = targetIndex;
    	this.name = name;
    	this.time = time;
    	this.description = description;
    	this.location = location;
    	this.tags = tags;
        
        isExecutedBefore = false;
    }

    @Override
    public CommandResult execute() {
    	
        assert model != null;
        assert undoRedoManager != null;
        
        UnmodifiableObservableList<ReadOnlyTask> lastShownList = model.getFilteredPersonList();

        if (lastShownList.size() < targetIndex) {
            indicateAttemptToExecuteIncorrectCommand();
            return new CommandResult(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        taskToDelete = lastShownList.get(targetIndex - 1);
       
        try {
            Set<Tag> tagSet = new HashSet<>();
            if(!tags.isEmpty()){
                for (String tagName : tags) {
                    tagSet.add(new Tag(tagName));
                }
            }else{
                UniqueTagList tagsToDelete = taskToDelete.getTags();
                tagSet = tagsToDelete.toSet();
            }
            if(name== " "){
                Name nameToDelete = taskToDelete.getName();
                name = nameToDelete.toString();
            }
            if (time == " "){
                Time timeToDelete = taskToDelete.getTime();
                time = timeToDelete.toString();
            }
            if (description == " "){
                Description descriptionToDelete = taskToDelete.getDescription();
                description = descriptionToDelete.toString();
            }
            if (location == " "){
                Address locationToDelete = taskToDelete.getAddress();
                location = locationToDelete.toString();
            }
            toAdd = new Task(
                    new Name(name),
                    new Time(time),
                    new Description(description),
                    new Address(location),
                    new UniqueTagList(tagSet)
            );
            model.addTask(toAdd);
            model.deletePerson(taskToDelete);
            
        } catch (UniqueTaskList.DuplicatePersonException e) {
            return new CommandResult(MESSAGE_DUPLICATE_TASK);
        } catch (IllegalValueException e) {
            assert false : "The target task already possesses null values";
        } catch (PersonNotFoundException e) {
            assert false : "The target task cannot be missing";
        } 
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

}
