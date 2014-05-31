package my.wf.samlib.storage.json.storage;

import my.wf.samlib.core.model.entity.BaseEntity;
import my.wf.samlib.storage.json.exception.JsonStorageException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
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

    protected void setData(SamlibData data){
        this.data = data;
    }

    protected Reader getReader() throws FileNotFoundException {
        return new FileReader(fileName);
    }

    protected SamlibData readData(Reader reader) throws IOException {
        data = getMapper().readValue(reader, SamlibData.class);
        updateDate = new Date();
        reader.close();
        data.postJsonReadProcessing();
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
        SamlibData d = getData();
        getMapper().writeValue(writer, d);
        writer.close();
    }

    public <T extends BaseEntity> Long generateId() {
        return getData().generateNewId();
    }
}
