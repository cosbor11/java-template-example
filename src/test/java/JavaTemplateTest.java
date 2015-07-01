
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

    public class JavaTemplateTest
    {

        ITemplateEngine templateEngine = new MustacheTemplateEngine();

        public File outputFolder = new File(System.getProperty("user.home") + "/JavaTemplateTest");

        @Before
        public void setUp()
        {
            outputFolder.mkdirs();
        }

        @After
        public void tearDown()
        {
            for (File file : outputFolder.listFiles())
            {
                file.delete();
            }
            outputFolder.delete();
        }

        public JavaTemplateTest()
        {
        }

        @Test
        public void testTemplateEngine() throws Exception
        {

            //mock the user input
            UserInput userInput = new UserInput();
            userInput.setFirstName("Chris");
            userInput.setLastName("Osborn");

            //create the output file
            File file = new File(outputFolder.getCanonicalPath() + File.separatorChar + "test.txt");

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

        }
        
        @Test
        public void testFreemarkerTemplateEngine() throws Exception
        {

            ITemplateEngine templateEngine = new FreemarkerTemplateEngine();
            
            //mock the user input
            UserInput userInput = new UserInput();
            userInput.setFirstName("Chris");
            userInput.setLastName("Osborn");

            //create the out put file
            File file = new File(outputFolder.getCanonicalPath() + File.separatorChar + "freemarkertest.txt");

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

        }
        
        

    }
