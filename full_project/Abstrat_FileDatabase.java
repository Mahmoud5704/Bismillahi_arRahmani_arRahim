package full_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Abstrat_FileDatabase<T extends interface_Record> {

    protected ArrayList<T> records = new ArrayList<>();
    protected String filename;

    public Abstrat_FileDatabase(String filename) {
        this.filename = filename;
    }// bezn allah done

    public abstract T createRecordFrom(String line);// bezn allah done

    public void readFromFile() {
        File path_file = new File(filename);
        try (Scanner read_path = new Scanner(path_file)) {
            if (!read_path.hasNextLine()) {
                System.out.println("FILE IS EMPTY");
                return;
            }
            while (read_path.hasNextLine()) {
                String line = read_path.nextLine();
                records.add(createRecordFrom(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR : FILE NOT FOUND");
        }
    }// bezn allah done

    public ArrayList<T> returnAllRecords() {
        readFromFile();
        return records;
    }// bezn allah done

    public boolean contains(String key) {
        readFromFile();
        return getRecord(key) != null;
    }// bezn allah done

    public T getRecord(String key) {
        readFromFile();
        if (records.isEmpty()) {
            return null;

        } else {
            for (T c : records) {
                if (key.equals(c.getSearchKey())) {
                    return c;
                }
            }
        }
        return null;
    }// bezn allah done

    public void deleteRecord(String key) {
        readFromFile();
        if (!contains(key)) {
           return;
        }
        for (T c : records) {
            if (key.equals(c.getSearchKey())) {
                records.remove(c);
                return;
            }
        }
    }// bezn allah done

    public void insertRecord(T record) {
        readFromFile();
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }
    }// bezn allah done

    public void saveToFile() {
        if (!records.isEmpty()) {
            try (FileWriter wr = new FileWriter(filename)) {
                for (T cc : records) {
                    wr.write(cc.lineRepresentation() + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("ERROR : FILE NOT FOUND");
            } catch (IOException e) {
                System.out.println("ERROR : IOException");
            }
        }
    }// bezn allah done
}
