
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.JMSException;
import javax.jms.Destination;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Message;

public class Subscriber {

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
        String destName = null;
        Destination dest = null;
        Session session = null;
        MessageConsumer receiver = null;
        BufferedReader waiter = null;

        if (args.length != 1) {
            System.out.println("usage: Subscriber <destination>");
            System.exit(1);
        }

        destName = args[0];

        try {
            // create the JNDI initial context.
            context = new InitialContext();

            // look up the ConnectionFactory
            factory = (ConnectionFactory) context.lookup(factoryName);

            // look up the Desination
            dest = (Destination) context.lookup(destName);

            // create the connection
            connection = factory.createConnection();

            // create the session
            session = connection.createSession(
                false, Session.AUTO_ACKNOWLEDGE);

            // create the receiver
            receiver = session.createConsumer(dest);

            // register a listener
            receiver.setMessageListener(new StockListener());

            // start the connection, to enable message receipt
            connection.start();

            System.out.println("Waiting for stock update...");
            System.out.println("Press [return] to quit");

            waiter = new BufferedReader(new InputStreamReader(System.in));
            waiter.readLine();
            
            // Receive message( stock price )
            
            Message message = receiver.receive();
            StockListener sl = new StockListener();
            
            //Call StockListner onMessage to process the Message Recieved
            sl.onMessage(message);
                            
                            
        } catch (IOException exception) {
            exception.printStackTrace();
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
