package data;

import com.github.utils.CsvReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class TestDataProvider {

    @DataProvider(name = "invalid-emails")
    private static Object[][] readInvalidEmails() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/invalid-emails.csv");
    }

    @DataProvider(name = "invalid-passwords")
    private static Object[][] readInvalidPasswords() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/invalid-passwords.csv");
    }

    @DataProvider(name = "invalid-usernames")
    private static Object[][] readInvalidUsernames() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/invalid-usernames.csv");
    }
}
