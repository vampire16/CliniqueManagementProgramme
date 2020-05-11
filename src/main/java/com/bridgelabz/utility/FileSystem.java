package com.bridgelabz.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileSystem {
    ObjectMapper mapper = new ObjectMapper();

    public FileSystem() {
    }

    public <E> void writeData(String filePath, ArrayList<E> list) throws IOException {
        mapper.writeValue(new File(filePath), list);
    }

    public <E> ArrayList<E> readData(String filePath, Class<E> contentClass) throws IOException {
        return mapper.readValue(new File(filePath),
                mapper.getTypeFactory().constructParametricType(ArrayList.class, contentClass));
    }
}
