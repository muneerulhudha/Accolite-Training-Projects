import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Publisher {

    /**
     * Main line.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Context context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        String factoryName = "ConnectionFactory";

        //stockName could be ONLY 1 from the following company names : Accolite, Amazon, Dell, Flipkart, HP, IBM (names are case-senstive)
        // stockName will be provided through command line arguments

        String stockName = null;
        Destination dest = null;
        int count = 1;
        Session session = null;
        MessageProducer sender = null;
        String stockValue = "Message ";

        if (args.length < 1 || args.length > 2) {
            System.out.println("usage: Publisher <destination> message");
            System.exit(1);
        }

        stockName = args[0];
        if (args.length == 2) {
            count = Integer.parseInt(args[1]);
        }

        try {
            // create the JNDI initial context.
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            dest = (Destination) context.lookup(stockName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            // create the sender
            sender = session.createProducer(dest);

            // start the connection, to enable message sends
            connection.start();

            //your code to send message for a stock goes here
            TextMessage message = session.createTextMessage();
            message.setText(Integer.toString(count));
            sender.send(message);
            System.out.println("Stock price sent: " + message.getText());

        } catch (JMSException exception) {
            exception.printStackTrace();
        } catch (NamingException exception) {
            exception.printStackTrace();
        } finally {
            // close the context
            if (context != null) {
                try {
                    context.close();
                } catch (NamingException exception) {
                    exception.printStackTrace();
                }
            }

            // close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
