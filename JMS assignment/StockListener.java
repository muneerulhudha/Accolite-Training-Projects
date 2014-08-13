import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.JMSException;

public class StockListener implements MessageListener {

    public void onMessage(Message message) {

        try{
         if (message instanceof TextMessage) {
                    TextMessage text = (TextMessage) message;
                    System.out.println("Stock price quoted by publisher: " + text.getText());
                } else if (message != null) {
                    System.out.println("Received non text message");
                }
        
        }
		catch(Exception e)
		{
		   e.printStackTrace();
		}
    }
}
