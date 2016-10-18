import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;
import java.util.logging.Logger;

public class Sender {
    static final Logger logger = Logger.getLogger("Sender");
    // Set up all the default values
    private static final String DEFAULT_MESSAGE = "Hello, World!";
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "jms/queue/PlayQueue";
    private static final String DEFAULT_MESSAGE_COUNT = "1";
    private static final String DEFAULT_USERNAME = "joao";
    private static final String DEFAULT_PASSWORD = "br1o+sa*";
    private static final String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
    private static final String PROVIDER_URL = "http-remoting://localhost:8080";

    public static void main(String[] args) throws Exception {

        Context namingContext = null;
        JMSContext context = null;

        try {

            // Set up the namingContext for the JNDI lookup
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, PROVIDER_URL);
            env.put(Context.SECURITY_PRINCIPAL, DEFAULT_USERNAME);
            env.put(Context.SECURITY_CREDENTIALS, DEFAULT_PASSWORD);
            namingContext = new InitialContext(env);

            // Perform the JNDI lookups
            String connectionFactoryString = DEFAULT_CONNECTION_FACTORY;
            logger.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            logger.info("Found connection factory \"" + connectionFactoryString + "\" in JNDI");

            String destinationString = DEFAULT_DESTINATION;
            logger.info("Attempting to acquire destination \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            logger.info("Found destination \"" + destinationString + "\" in JNDI");

            // Create the JMS context
            context = connectionFactory.createContext(DEFAULT_USERNAME, DEFAULT_PASSWORD);

            int count = Integer.parseInt(DEFAULT_MESSAGE_COUNT);
            String content = DEFAULT_MESSAGE;

            logger.info("Sending " + count + " messages with content: " + content);

            // Send the specified number of messages
            for (int i = 0; i < count; i++) {
                context.createProducer().send(destination, content);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (namingContext != null) {
                namingContext.close();
            }

            // closing the context takes care of consumer too
            if (context != null) {
                context.close();
            }
        }
    }
}
