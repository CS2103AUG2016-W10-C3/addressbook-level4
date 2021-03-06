package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.task.*;


/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Location address;
    private Description description;
    private Optional<Time> time;
    private UniqueTagList tags;
    private boolean isCompleted;

    public TestTask() {
        tags = new UniqueTagList();
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setTime(Optional<Time> time) {
        this.time = time;
    }

    public void setComplete(boolean isComplete) {
        this.isCompleted = isComplete;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Optional<Time> getTime() {
        return time;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    @Override
    public Location getLocation() {
        return address;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

	@Override
	public boolean getCompleted() {
		return isCompleted;
	}

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().taskName + " ");
        sb.append("t;" + this.getTime().get().value + " ");
        sb.append("d;" + this.getDescription().value + " ");
        sb.append("a;" + this.getLocation().value + " ");

        this.getTags().getInternalList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public TaskType getTaskType() {
        if (time.isPresent()) {
            if (time.get().getUntimedStatus()){
                return TaskType.UNTIMED;
            } else if (!time.get().getEndDate().isPresent()) {
                return TaskType.DEADLINE;
            } else {
                return TaskType.TIMERANGE;
            }
        } else {
            return TaskType.FLOATING;
        }
    }
}
