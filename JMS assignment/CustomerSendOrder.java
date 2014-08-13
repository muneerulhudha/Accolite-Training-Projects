import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;


public class CustomerSendOrder{

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
        String orderQueue = "OrderQueue";
        Destination orderQueueDest = null;
        int quantity = Integer.parseInt(args[0]);
        Session session = null;
        MessageProducer sender = null;

        if (args.length != 1) {
            System.out.println("usage: CustomerSendOrder message");
            System.exit(1);
        }


        try {
            // create the JNDI initial context
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Destination
            orderQueueDest = (Destination) context.lookup(orderQueue);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            //create the sender
            sender = session.createProducer(orderQueueDest);
            
            // start the connection, to enable message receipt
            connection.start();

            // your code to send the message to OrderQueue goes here
            TextMessage message = session.createTextMessage();
            message.setText(Integer.toString(quantity));
            sender.send(message);
            System.out.println("Quantity ordered by Consumer: " + message.getText());

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
