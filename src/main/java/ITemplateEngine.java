
    import java.io.Writer;

    public interface ITemplateEngine
    {
        /**
         * creates the template engines instance and sets the root path to the templates in the resources folder
         * @param templatesResouceFolder 
         */
        public  void init(String templatesResouceFolder);

        /**
         * sets the current template to process with
         * @param template 
         */
        public  void setTemplate(String template);

        /**
         * compiles and writes the template output to a writer
         * @param writer
         * @param data 
         */
        public  void process(Writer writer, Object data);

        /**
         * returns the template file extension
         * @return 
         */
        public  String getTemplateExtension();

        /**
         * finishes the write process and closes the write buffer
         */
        public  void flush();

    } 
