package backend.ajude.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;

/**
 * Servico de Email
 */
@Service
public class EnvioEmail {

    private JavaMailSender javaMailSender;

    @Autowired
    public EnvioEmail(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    /**
     * Envia o email de notificacao ao registrar um Usuario
     * @param usuario o Usuario Registrado
     * @throws MailException caso o Email nao seja valido
     */
    public void sendNotification(Usuario usuario) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(usuario.getEmail());
        mail.setFrom("pablwopsoft@gmail.com");
        mail.setSubject("Cadastro Confirmado!");
        mail.setText("Seja bem vindo(a) ao AjUdE! Seu cadastro foi realizado, você pode acessar nossa pagina a partir do link: https://eager-curran-593cc1.netlify.com/#/home");

        javaMailSender.send(mail);
    }
}