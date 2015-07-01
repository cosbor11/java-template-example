
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

    public class JavaTemplateTest
    {

        @Rule
        public TemporaryFolder outputFolder = new TemporaryFolder();

        public JavaTemplateTest()
        {
        }

        @Test
        public void testMustacheTemplateEngine() throws Exception
        {
            
            ITemplateEngine templateEngine = new MustacheTemplateEngine();

            //mock the user input
            UserInput userInput = new UserInput();
            userInput.setFirstName("Chris");
            userInput.setLastName("Osborn");

            //create the output file
            String testFolderPath = this.outputFolder.getRoot().getCanonicalPath();
            File file = new File(testFolderPath.getCanonicalPath() + File.separatorChar + "mustachetest.txt");

            //create a FileWriter 
            try (Writer fileWriter = new FileWriter(file.getPath()))
            {

                //put the templateEngine to work
                templateEngine.init("templates");
                templateEngine.setTemplate("test"); //resources/templates/test.mustache
                templateEngine.process(fileWriter, userInput); //compile template
                templateEngine.flush(); //write to file
            }

            //Read from the file and assert
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Assert.assertEquals("Hello Chris Osborn!", buffer.readLine());
            buffer.close();

        }
        
        @Test
        public void testFreemarkerTemplateEngine() throws Exception
        {

            ITemplateEngine templateEngine = new FreemarkerTemplateEngine();
            
            //mock the user input
            UserInput userInput = new UserInput();
            userInput.setFirstName("Chris");
            userInput.setLastName("Osborn");

            //create the output file
            String testFolderPath = this.outputFolder.getRoot().getCanonicalPath();
            File file = new File(testFolderPath.getCanonicalPath() + File.separatorChar + "freemarkertest.txt");

            //create a FileWriter 
            try (Writer fileWriter = new FileWriter(file.getPath()))
            {

                //put the templateEngine to work
                templateEngine.init("templates");
                templateEngine.setTemplate("test"); //resources/templates/test.ftl
                templateEngine.process(fileWriter, userInput); //compile template
                templateEngine.flush(); //write to file
            }

            //Read from the file and assert
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Assert.assertEquals("Hello Chris Osborn!", buffer.readLine());
            buffer.close();

        }

    }
