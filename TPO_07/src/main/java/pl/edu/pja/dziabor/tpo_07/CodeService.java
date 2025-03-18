package pl.edu.pja.dziabor.tpo_07;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CodeService {

    private static Map<String, Code> codes = new HashMap<>();

    public boolean saveCode(Code code) throws Exception {
        if (code.getId().isEmpty()){
            throw new Exception("Fill all required fields");
        }

        if (!code.isValid()){
            throw new Exception("Code can be saved for minimum 10 seconds and maximum 90 days");
        }

        if (codes.containsKey(code.getId()))
            return false;

        codes.put(code.getId(), code);
        saveToExtent(codes);
        return true;
    }

    public Optional<Code> findById(String id){
        if (codes.containsKey(id)){
            if (codes.get(id).isExpired()){
                codes.remove(id);
            }
            return Optional.ofNullable(codes.get(id));
        }
        return Optional.ofNullable(codes.get(id));
    }

    public void saveToExtent(Map<String, Code> codes) throws IOException {
        FileOutputStream fileOutputStream
                = new FileOutputStream("extent.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(codes);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static Map<String, Code> getCodes() {
        return codes;
    }

    public void populateCodes() {
        try (FileInputStream fileIn = new FileInputStream("extent.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            codes = (Map<String, Code>) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatCode(String codeText) throws FormatterException {
            return new Formatter().formatSource(codeText);
    }
}
