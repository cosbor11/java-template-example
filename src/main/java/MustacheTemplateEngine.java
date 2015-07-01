


    import com.github.mustachejava.DefaultMustacheFactory;
    import com.github.mustachejava.Mustache;
    import com.github.mustachejava.MustacheFactory;
    import java.io.File;
    import java.io.IOException;
    import java.io.Writer;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    /**
     * 
     * Use {{obj.prop}} in your template to replace a certain the token
     * If obj.prop is null or undefined, it will automatically replace it with an empty string
     * If you want to exclude an entire section based on if a value is null, undefined, or false you can do this:
     *     {{#obj.prop}}
     *     Never shown
     *     {{/obj.prop}}  
     */
    public class MustacheTemplateEngine implements ITemplateEngine
    {

        protected MustacheFactory factory = null;

        protected Mustache instance = null;

        protected Writer writer = null;

        protected String templatesFolder = "templates";

        @Override
        public void init(String templatesResouceFolder)
        {
            if(factory == null){
                factory = new DefaultMustacheFactory();
                this.templatesFolder = templatesResouceFolder;
            }
        }

        @Override
        public void setTemplate(String template)
        {
            instance = factory.compile(templatesFolder + File.separatorChar + template + getTemplateExtension());
        }

        @Override
        public void process(Writer writer, Object data)
        {
            this.writer = instance.execute(writer, data);
        }

        @Override
        public String getTemplateExtension()
        {
            return ".mustache";
        }

        @Override
        public void flush()
        {
            try
            {
                this.writer.flush();
            } catch (IOException ex)
            {
                Logger.getLogger(MustacheTemplateEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
