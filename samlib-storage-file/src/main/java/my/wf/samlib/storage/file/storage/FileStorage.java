package my.wf.samlib.storage.file.storage;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Serg on 27.04.2014.
 */
public class FileStorage {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void save(EntityData entityData, String fileName){
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(fileName);
        try {
            mapper.writeValue(file, entityData.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(){

    }
}
