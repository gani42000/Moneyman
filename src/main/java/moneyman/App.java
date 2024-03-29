package moneyman;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Currency;
import java.util.Locale;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Locale locale = Locale.forLanguageTag("ta-IN");
    	Currency curr = Currency.getInstance(locale);
    	System.out.println(curr.getCurrencyCode());
    	System.out.println(Paths.get("").toAbsolutePath());
    	Path dirPath = Paths.get("/~/");

           try {
               // List the contents of the directory
               Files.list(dirPath)
                    .forEach(path -> System.out.println(path.getFileName()));
           } catch (IOException e) {
               e.printStackTrace();
           }
    }
}
