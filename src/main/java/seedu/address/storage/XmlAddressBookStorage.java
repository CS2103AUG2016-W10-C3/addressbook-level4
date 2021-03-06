package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.ReadOnlyToDo;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * A class to access ToDo data stored as an xml file on the hard disk.
 */
public class XmlAddressBookStorage implements ToDoStorage {

    private static final Logger logger = LogsCenter.getLogger(XmlAddressBookStorage.class);
    private static final String DEFAULT_FILEPATH = "data/ToDoList.xml";
    
    private String filePath;

    public XmlAddressBookStorage(String filePath){
        this.filePath = filePath;
    }

    public String getToDoFilePath(){
        return filePath;
    }

    /**
     * Similar to {@link #readToDo()}
     * @param filePath location of the data. Cannot be null
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyToDo> readToDo(String filePath) throws DataConversionException, IOException {
        assert filePath != null;

        File addressBookFile = new File(filePath);

        if (!addressBookFile.exists()) {
            logger.info("ToDo file "  + addressBookFile + " not found");
            return Optional.empty();
        }

        ReadOnlyToDo addressBookOptional = XmlFileStorage.loadDataFromSaveFile(new File(filePath));

        return Optional.of(addressBookOptional);
    }

    /**
     * Similar to {@link #saveToDo(ReadOnlyToDo)}
     * @param filePath location of the data. Cannot be null
     */
    public void saveToDo(ReadOnlyToDo addressBook, String filePath) throws IOException {
        assert addressBook != null;
        assert filePath != null;

        File file = new File(filePath);
        FileUtil.createIfMissing(file);
        XmlFileStorage.saveDataToFile(file, new XmlSerializableAddressBook(addressBook));
    }

    //@@author A0126649W
    @Override
    public Optional<ReadOnlyToDo> readToDo() throws DataConversionException, IOException {
        assert filePath != null;

        File addressBookFile = new File(filePath);

        if (!addressBookFile.exists()) {
            logger.info("ToDo file "  + addressBookFile + " not found");
            this.filePath = DEFAULT_FILEPATH;
            return Optional.empty();
        }

        ReadOnlyToDo addressBookOptional = XmlFileStorage.loadDataFromSaveFile(new File(filePath));

        return Optional.of(addressBookOptional);
    }
    //@@author

    @Override
    public void saveToDo(ReadOnlyToDo addressBook) throws IOException {
        saveToDo(addressBook, filePath);
    }
}
