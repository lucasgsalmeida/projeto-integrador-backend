package me.lucasgsalmeida.gestao10x.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class WebHookController {

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestBody String payload,
            @RequestHeader("X-GitHub-Event") String eventType
    ) {
        if ("push".equals(eventType)) {
            updateDockerContainer();
        }
    }

    private void updateDockerContainer() {
        try {
            // Define o comando para atualizar o repositório e reiniciar o Docker
            String[] command = {"/bin/sh", "-c", "cd /caminho/do/repo && git pull origin main && docker-compose down && docker-compose up -d"};

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
            System.out.println("Container atualizado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
