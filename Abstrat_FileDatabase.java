package full_project;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class Abstrat_FileDatabase<T> {

    protected ArrayList<T> records = new ArrayList<>();
    protected String filename;

    public Abstrat_FileDatabase(String filename) {
        this.filename = filename;
    }// bezn allah done

    public abstract T createRecordFrom(String line);// bezn allah done

    public abstract String getkey(T ob);// bezn allah done

    public abstract String getline(T ob);// bezn allah done

    public void readFromFile() {
        File path_file = new File(filename);
        try (Scanner read_path = new Scanner(path_file)) {
            if (!read_path.hasNextLine()) {
                System.out.println("File is empty");
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
        if (records.isEmpty()) {
            System.out.print("there is no record yet!");
        }
        return records;
    }// bezn allah done

    public boolean contains(String key) {
        if (records.isEmpty()) {
            return false;
        } else {
            for (T c : records) {
                if (key.equals(getkey(c))) {
                    return true;
                }
            }
        }
        return false;
    }// bezn allah done

    public T getRecord(String key) {
        if (records.isEmpty()) {
            System.out.print("there is no record to get!");
            return null;

        } else {
            for (T c : records) {
                if (key.equals(getkey(c))) {
                    return c;
                }
            }
        }
        System.out.print("can not find record to get!");
        return null;
    }// bezn allah done

    public void deleteRecord(String key) {
        if (!contains(key)) {
            System.out.println("there is no record with this key");
            return;
        }
        for (T c : records) {
            if (key.equals(getkey(c))) {
                records.remove(c);
                System.out.println("record deleted");
                saveToFile();
                return;
            }
        }
    }// bezn allah done

    public void insertRecord(T record) {
        if (contains(getkey(record))) {
            System.out.println("Record with this key already exists!");
        } else {
            records.add(record);
            saveToFile();
        }
    }// bezn allah done

    public void saveToFile() {
        if (records.isEmpty()) {
            System.out.print(" there is nothing to write");
        } else {
            try (FileWriter wr = new FileWriter(filename)) {
                for (T cc : records) {
                    wr.write(getline(cc) + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("ERROR : FILE NOT FOUND");
            } catch (IOException e) {
                System.out.println("ERROR : IOException");
            }
        }
    }// bezn allah done
}

