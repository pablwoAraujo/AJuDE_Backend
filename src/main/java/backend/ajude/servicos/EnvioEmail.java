package backend.ajude.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import backend.ajude.entidades.Usuario;

@Service
public class EnvioEmail {

    private JavaMailSender javaMailSender;

    @Autowired
    public EnvioEmail(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendNotification(Usuario usuario) throws MailException{
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(usuario.getEmail());
        mail.setFrom("pablwopsoft@gmail.com");
        mail.setSubject("SEJA BEM VINDO AO AJUDE!");
        mail.setText("VOCÊ PODE ACESSAR NOSSA PAGINA PELO LINK https://eager-curran-593cc1.netlify.com/#/home");

        javaMailSender.send(mail);
    }
}