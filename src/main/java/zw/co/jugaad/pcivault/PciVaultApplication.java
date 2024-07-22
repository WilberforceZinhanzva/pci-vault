package zw.co.jugaad.pcivault;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zw.co.jugaad.pcivault.util.AESUtils;

@SpringBootApplication
public class PciVaultApplication implements CommandLineRunner {

	@Value("${security.key}")
	private String key;

	@Value("${security.iv}")
	private String iv;


	public static void main(String[] args) {
		SpringApplication.run(PciVaultApplication.class, args);
	}


	@Bean
	public Gson gson() {
		return new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	}

	@Override
	public void run(String... args) throws Exception {
		var encryptedWord = AESUtils.encrypt("venon", key,iv);

		System.out.println(encryptedWord);

		var clearWord = AESUtils.decrypt(encryptedWord,key,iv);

		System.out.println(clearWord);
	}
}
