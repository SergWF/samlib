package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.storage.json.exception.JsonStorageException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: SBilenogov
 */
public class EntityStorage {
    private SamlibData data;
    private ObjectMapper mapper;
    private String fileName;
    private Date updateDate;


    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public SamlibData getData() {
        if (null == data) {
            try {
                readData(getReader());
            } catch (IOException e) {
                new JsonStorageException("Read exception", e);
            }
        }
        return data;
    }

    private Reader getReader() {
        return null;
    }

    protected SamlibData readData(Reader reader) throws IOException {
        data = getMapper().readValue(reader, SamlibData.class);
        updateDate = new Date();
        return data;
    }

    public void saveData() throws JsonStorageException {
        try {
            writeData(getWriter());
            updateDate = new Date();
        } catch (IOException e) {
            throw new JsonStorageException("Write exception", e);
        }
    }

    protected Writer getWriter() throws IOException {
        return new FileWriter(getFileName());
    }

    protected void writeData(Writer writer) throws IOException {
        getMapper().writeValue(writer, getData());
        writer.close();

    }

    public <T extends BaseEntity> Long generateId(Class<T> storedClass) {
        return getData().generateNewId(storedClass);
    }
}
