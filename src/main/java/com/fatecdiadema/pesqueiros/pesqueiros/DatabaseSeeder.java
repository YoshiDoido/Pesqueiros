package com.fatecdiadema.pesqueiros.pesqueiros;

import com.fatecdiadema.pesqueiros.pesqueiros.model.Embarcacao;
import com.fatecdiadema.pesqueiros.pesqueiros.model.IEmbarcacaoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final IEmbarcacaoRepository embarcacaoRepository;

    public DatabaseSeeder(IEmbarcacaoRepository embarcacaoRepository) {
        this.embarcacaoRepository = embarcacaoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Embarcacao embarcacao1 = new Embarcacao("Antonio Nascimento", "Popeye", "123456789", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtED0Jd0873DJOnZWQQHuMHTtsGD4yylxn2Q&s", 10);
        Embarcacao embarcacao2 = new Embarcacao("Carlos Tavares", "Tornado", "A987654321", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsvfSsBEkz4oqbBmHJSkTg0kSAuzM6M1w0FQ&s", 2);

        embarcacaoRepository.save(embarcacao1);
        embarcacaoRepository.save(embarcacao2);
    }
}