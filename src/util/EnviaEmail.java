package util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import model.Usuario;

public class EnviaEmail {
	
	private final String username = "rogercr10@gmail.com";
	private final String password = "senha";
	private Properties props;
	private Session session;

	public EnviaEmail(){
		this.props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		this.session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
	}
	
	public void recuperaSenhaEmail(Usuario u) throws AddressException, MessagingException{
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from-email@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(u.getEmail()));
		message.setSubject("iPonto - Recuperação de Senha");
		message.setText("Prezado "+u.getNome()+","
			+ "\n\n seguem os dados como solicitado: "+
				"\n\n Login: "+u.getLogin()+
				"\n Senha: "+u.getSenha());
		
		Transport.send(message);
	}
}
