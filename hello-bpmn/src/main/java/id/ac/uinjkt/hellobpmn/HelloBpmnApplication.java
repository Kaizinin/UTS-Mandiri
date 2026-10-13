package id.ac.uinjkt.hellobpmn;

import java.util.Map;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloBpmnApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloBpmnApplication.class, args);
    }

    /** Menjalankan proses "helloProcess" sekali saat aplikasi start. */
    @Bean
    CommandLineRunner jalankanProses(RuntimeService runtimeService) {
        return args -> {
            ProcessInstance pi = runtimeService.startProcessInstanceByKey(
                    "helloProcess", Map.of("nama", "Mahasiswa"));
            System.out.println(">>> Proses dimulai, id = " + pi.getId());
        };
    }
}
