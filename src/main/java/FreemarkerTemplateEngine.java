
    import freemarker.template.Configuration;
    import freemarker.template.Template;
    import freemarker.template.TemplateException;
    import java.io.File;
    import java.io.IOException;
    import java.io.Writer;
    import java.util.logging.Level;
    import java.util.logging.Logger;


    /**
     * 
     * Use ${obj.prop} in your template to replace a certain the token
     * Use ${obj.prop!} to replace with empty string if obj.prop is null or undefined
     * 
     * 
     */
    public class FreemarkerTemplateEngine implements ITemplateEngine
    {

        protected Configuration instance = null;

        protected String templatesFolder = "templates";

        protected Template templateCompiler = null;

        protected Writer writer = null;

        @Override
        public void init(String templatesResouceFolder)
        {

            if(instance == null){
                instance = new Configuration();
                instance.setClassForTemplateLoading(this.getClass(), "/");
                this.templatesFolder = templatesResouceFolder;
            }
        }

        @Override
        public void setTemplate(String template)
        {
            try
            {
                templateCompiler = instance.getTemplate(templatesFolder + File.separatorChar + template + getTemplateExtension());
            } catch (IOException ex)
            {
                Logger.getLogger(FreemarkerTemplateEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void process(Writer writer, Object data)
        {
            try
            {
                templateCompiler.process(data, writer);
                this.writer = writer;
            } catch (TemplateException | IOException ex)
            {
                Logger.getLogger(FreemarkerTemplateEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public String getTemplateExtension()
        {
            return ".ftl";
        }

        @Override
        public void flush()
        {
            try
            {
                this.writer.flush();
            } catch (IOException ex)
            {
                Logger.getLogger(FreemarkerTemplateEngine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
